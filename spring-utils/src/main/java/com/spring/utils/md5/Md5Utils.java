package com.spring.utils.md5;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;

/**
 * CopyRight : <company domain>
 * Project :  biz-tool
 * Comments : <对此类的描述，可以引用系统设计中的描述>
 * JDK version : JDK1.8
 * Create Date : 2022-06-24 17:37
 *
 * @author : linzhou
 * @version : 1.0
 * @since : 1.0
 */
@Slf4j
public class Md5Utils {

    /**
     * md5 加密
     *
     * @param str
     * @return
     */
    public static String encryptToMd5(String str) {
        String md5 = DigestUtils.md5DigestAsHex(str.getBytes(StandardCharsets.UTF_8));
        log.debug("MD5加密结果str:{},md5:{}", str, md5);
        return md5;
    }
}
