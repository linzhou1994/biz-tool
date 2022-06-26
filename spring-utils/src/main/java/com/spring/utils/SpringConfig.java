package com.spring.utils;

import com.spring.utils.bean.SpringUtil;
import com.spring.utils.controller.advice.GlobalExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author linzhou
 * @ClassName SpringConfig.java
 * @createTime 2021年12月13日 10:53:00
 * @Description
 */
@Configuration
public class SpringConfig {

    @Bean
    public SpringUtil getSpringUtil(){
        return new SpringUtil();
    }

    @Bean
    public GlobalExceptionHandler getGlobalExceptionHandler(){
        return new GlobalExceptionHandler();
    }
}
