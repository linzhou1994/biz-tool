package com.spring.utils.http;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * CopyRight : <company domain>
 * Project :  biz-tool
 * Comments : <对此类的描述，可以引用系统设计中的描述>
 * JDK version : JDK1.8
 * Create Date : 2022-06-25 15:41
 *
 * @author : linzhou
 * @version : 1.0
 * @since : 1.0
 */
public class RequestUtil {

    public static HttpServletRequest getRequest(){
        return  ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest();
    }

    public static Cookie getCookie(String cookieName){
        Cookie[] cookies = getRequest().getCookies();
        if (Objects.nonNull(cookies)){
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(cookieName)){
                    return cookie;
                }
            }
        }
        return null;
    }

}
