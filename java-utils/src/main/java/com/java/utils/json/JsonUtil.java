package com.java.utils.json;

import com.alibaba.fastjson.JSON;

/**
 * CopyRight : <company domain>
 * Project :  biz-tool
 * Comments : <对此类的描述，可以引用系统设计中的描述>
 * JDK version : JDK1.8
 * Create Date : 2022-06-24 21:15
 *
 * @author : linzhou
 * @version : 1.0
 * @since : 1.0
 */
public class JsonUtil {


    public static String toString(Object o){
        return JSON.toJSONString(o);
    }

    public static <T> T getObject(String json,Class<T> tClass){
        return JSON.parseObject(json,tClass);
    }
}
