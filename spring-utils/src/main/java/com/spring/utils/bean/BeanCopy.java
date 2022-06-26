package com.spring.utils.bean;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * CopyRight : <company domain>
 * Project :  biz-tool
 * Comments : <对此类的描述，可以引用系统设计中的描述>
 * JDK version : JDK1.8
 * Create Date : 2022-06-24 20:47
 *
 * @author : linzhou
 * @version : 1.0
 * @since : 1.0
 */
@Slf4j
public class BeanCopy {
    public static  <T> T copy(Object source,Class<T> target){
        try {
            T t = target.newInstance();
            BeanUtils.copyProperties(source,t);
            return t;
        } catch (Exception e) {
            log.error("bean copy error,source class:{},target class:{}",source.getClass(),target,e);
           return null;
        }
    }

    public static <T> List<T> copyList(List sources ,Class<T> target){
        if (CollectionUtils.isEmpty(sources)){
            return Collections.emptyList();
        }
        List<T> rlt = new ArrayList<>(sources.size());
        for (Object source : sources) {
            rlt.add(copy(source,target));
        }
        return rlt;
    }
}
