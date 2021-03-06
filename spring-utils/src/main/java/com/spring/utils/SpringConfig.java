package com.spring.utils;

import com.spring.utils.bean.SpringUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author linzhou
 * @ClassName SpringConfig.java
 * @createTime 2021年12月13日 10:53:00
 * @Description
 */
@Configuration
@ComponentScan(basePackages = {"com.spring.utils.controller.advice"})
public class SpringConfig {

    @Bean
    public SpringUtil getSpringUtil(){
        return new SpringUtil();
    }

}
