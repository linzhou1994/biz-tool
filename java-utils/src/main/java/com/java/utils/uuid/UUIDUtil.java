package com.java.utils.uuid;

import java.util.UUID;

/**
 * UUID工具类
 *
 * @author : Galvin
 */
public class UUIDUtil {

    /**
     * 获取UUID
     *
     * @return String UUID
     * @author Galvin
     * @date 2022/9/6 9:42
     **/
    public static String getUUID() {
        return UUID.randomUUID().toString();
    }

    /**
     * 获取无符号UUID
     *
     * @return String 无符号UUID
     * @author Galvin
     * @date 2022/9/6 9:42
     **/
    public static String getNoSymbolUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

}
