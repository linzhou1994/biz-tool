package com.spring.lock.aspect;

import com.java.utils.reflect.ReflectUtil;
import com.spring.lock.DistributedLock;
import com.spring.lock.annotations.LockParam;
import com.spring.lock.result.BaseLockResult;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.util.StringUtils;
import com.spring.lock.annotations.Lock;

import javax.annotation.Resource;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @date : 2022/5/20 13:51
 * @author: linzhou
 * @description : LockAspect
 */
@Aspect
@Order(2)
public class LockAspect {
  /**
   * 读取redisKey中的变量名称的正则表达式
   * "asdfsd{name}dsfds{value}" =[{"name"},{"value"}]
   */
  private static final Pattern FIELD_NAME_PATTERN = Pattern.compile("\\{.*?}");

  @Resource
  private DistributedLock distributedLock;

  @Around("@annotation(lock)")
  public Object lock(ProceedingJoinPoint joinPoint, Lock lock) throws Throwable {
    if (Objects.isNull(lock)) {
      return joinPoint.proceed();
    }

    String key = lock.key();
    if (StringUtils.isEmpty(key)) {
      return joinPoint.proceed();
    }
    //获取分布式锁的key
    String redisKey = getRedisKey(joinPoint, key);
    com.spring.lock.param.LockParam lockParam = new com.spring.lock.param.LockParam(redisKey, lock.isReentrant(), lock.expireSeconds());
    BaseLockResult lockResultBo = distributedLock.lock(lockParam);
    try {
      if (Objects.isNull(lockResultBo)) {
        throw new IllegalAccessException("分布式锁获取失败，key：" + key);
      }
      return joinPoint.proceed();
    } finally {
      if (Objects.nonNull(lockResultBo)) {
        distributedLock.unLock(lockResultBo);
      }
    }
  }

  /**
   * 获取key中变量对应的值的键值对
   *
   * @param joinPoint
   * @param key
   * @return
   */
  private String getRedisKey(ProceedingJoinPoint joinPoint, String key) throws IllegalAccessException {
    //获取key中变量
    Set<String> fieldNames = getFieldNames(key);
    if (fieldNames.isEmpty()) {
      return key;
    }

    List<ParamInfo> fieldList = getParamInfos(joinPoint);

    Map<String, ParamInfo> paramMap = fieldList.stream().filter(o -> Objects.nonNull(o.getParam())).collect(Collectors.toMap(ParamInfo::getFieldName, o -> o, (o1, o2) -> o1));

    Map<String, ParamInfo> map = fieldList.stream().filter(o -> Objects.isNull(o.getParam())).collect(Collectors.toMap(ParamInfo::getFieldName, o -> o, (o1, o2) -> o1));

    String distributedLockKey = key;
    Iterator<String> it = fieldNames.iterator();
    while (it.hasNext()) {

      String fieldNameAndValue = it.next();
      String[] split = fieldNameAndValue.split(":");
      String fieldName = split[0];
      ParamInfo reflectField = paramMap.get(fieldName);
      if (Objects.isNull(reflectField)) {
        reflectField = map.get(fieldName);
      }

      if (Objects.nonNull(reflectField)) {
        distributedLockKey = distributedLockKey.replace("{" + fieldName + "}", "{" + reflectField.getValue() + "}");
        it.remove();
      } else if (split.length >= 2) {
        String value = split[1];
        distributedLockKey = distributedLockKey.replace("{" + fieldName + "}", "{" + value + "}");
        it.remove();
      }
    }
    if (fieldNames.size() > 0) {
      throw new IllegalAccessException("分布式锁key获取失败，找不到参数：" + String.join(",", fieldNames));
    }
    return distributedLockKey;
  }

  /**
   * 解析所有变量
   *
   * @param joinPoint
   * @return
   */
  private List<ParamInfo> getParamInfos(ProceedingJoinPoint joinPoint) {

    Object[] args = joinPoint.getArgs();
    if (Objects.isNull(args) || args.length == 0) {
      return new ArrayList<>();
    }

    //获取方法，此处可将signature强转为MethodSignature
    MethodSignature signature = (MethodSignature) joinPoint.getSignature();
    Method method = signature.getMethod();

    Annotation[][] parameterAnnotations = method.getParameterAnnotations();

    List<ParamInfo> rlt = new ArrayList<>();
    for (int i = 0; i < args.length; i++) {
      Object arg = args[i];
      if (isBasicClass(arg)) {
        LockParam param = ReflectUtil.getAnnotation(parameterAnnotations[i], LockParam.class);
        if (Objects.nonNull(param)) {
          rlt.add(new ParamInfo(arg, param.value(), param));
        }
      }else {
        List<ReflectUtil.ReflectField> allFieldAndValue = ReflectUtil.getAllFieldAndValue(arg);
        for (ReflectUtil.ReflectField reflectField : allFieldAndValue) {
          Object value = reflectField.getValue();
          Field field = reflectField.getField();
          LockParam param = field.getAnnotation(LockParam.class);

          if (Objects.nonNull(param)){
            rlt.add(new ParamInfo(value, param.value(), param));
          }else {
            rlt.add(new ParamInfo(value, field.getName(), null));
          }
        }
      }
    }
    return rlt;
  }

  /**
   * 获取key中变量
   *
   * @param key
   * @return
   */
  private Set<String> getFieldNames(String key) {
    Set<String> fieldNames = new HashSet<>();

    //生成匹配器，输入待匹配字符序列
    Matcher m = FIELD_NAME_PATTERN.matcher(key);
    //注意！find()一次，就按顺序扫描到了一个匹配的字符串，此时group()返回的就是该串。
    while (m.find()) {
      //打印匹配的子串
      String group = m.group();

      if (!StringUtils.isEmpty(group) && group.length() > 2) {
        group = group.substring(1, group.length() - 1);
        fieldNames.add(group);
      }
    }

    return fieldNames;
  }


  /**
   * 是否是基础类型
   *
   * @param o
   * @return
   */
  protected boolean isBasicClass(Object o) {
    if (o instanceof Integer
      || o instanceof String
      || o instanceof Double
      || o instanceof Float
      || o instanceof Long
      || o instanceof BigDecimal) {
      return true;
    }
    return false;
  }

  /**
   * 参数详情类
   */
  private static class ParamInfo {
    private Object value;
    private String fieldName;
    private LockParam param;

    public ParamInfo(Object value, String fieldName, LockParam param) {
      this.value = value;
      this.fieldName = fieldName;
      this.param = param;
    }

    public Object getValue() {
      return value;
    }

    public void setValue(Object value) {
      this.value = value;
    }

    public String getFieldName() {
      return fieldName;
    }

    public void setFieldName(String fieldName) {
      this.fieldName = fieldName;
    }

    public LockParam getParam() {
      return param;
    }

    public void setParam(LockParam param) {
      this.param = param;
    }
  }
}
