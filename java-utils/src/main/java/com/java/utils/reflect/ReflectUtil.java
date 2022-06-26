package com.java.utils.reflect;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @date : 2022/4/26 13:58
 * @author: linzhou
 * @description : ReflectUtil
 */
public class ReflectUtil {


  /**
   * 获取指定注解
   * @param parameterAnnotation
   * @param clazz
   * @param <T>
   * @return
   */
  public static  <T extends Annotation> T getAnnotation(Annotation[] parameterAnnotation, Class<T> clazz) {

    if (Objects.isNull(parameterAnnotation) || parameterAnnotation.length == 0) {
      return null;
    }
    for (Annotation annotation : parameterAnnotation) {
      if (clazz.isInstance(annotation)) {
        return (T) annotation;
      }
    }
    return null;
  }

  /**
   * 获取所有父类
   * @param c
   * @return
   */
    public static List<Class<?>> getAllSuperclass(Class<?> c) {
        List<Class<?>> superClassList = new ArrayList<>();
        while (c.getSuperclass() != Object.class) {
            superClassList.add(c.getSuperclass());
            c = c.getSuperclass();
        }
        return superClassList;
    }

    public static List<ReflectField> getReflectField(Object o) {
        return getReflectField(o, o.getClass());
    }

    public static List<ReflectField> getReflectField(Object o, Class<?> c) {
        Field[] declaredFields = c.getDeclaredFields();
        List<ReflectField> rlt = new ArrayList<>(declaredFields.length);
        for (Field declaredField : declaredFields) {
            declaredField.setAccessible(true);
            try {
                Object value = declaredField.get(o);
                ReflectField reflectField = new ReflectField();
                reflectField.setField(declaredField);
                reflectField.setaClass(c);
                reflectField.setValue(value);
                rlt.add(reflectField);
            } catch (IllegalAccessException e) {

            }
        }
        return rlt;
    }

    public static List<ReflectField> getAllFieldAndValue(Object o) {
        if (Objects.isNull(o)) {
            return new ArrayList<>();
        }
        List<Class<?>> allClass = getAllSuperclass(o.getClass());
        allClass.add(o.getClass());

        List<ReflectField> rlt = new ArrayList<>();
        for (Class<?> aClass : allClass) {
            rlt.addAll(getReflectField(o,aClass));
        }

        return rlt;
    }


    public static class ReflectField {
        private Class<?> aClass;
        private Object value;

        private Field field;

        public Class<?> getaClass() {
            return aClass;
        }

        public void setaClass(Class<?> aClass) {
            this.aClass = aClass;
        }

        public Object getValue() {
            return value;
        }

        public void setValue(Object value) {
            this.value = value;
        }

        public Field getField() {
            return field;
        }

        public void setField(Field field) {
            this.field = field;
        }
    }

}
