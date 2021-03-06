package com.spring.dubbo.config;

import com.spring.dubbo.filter.impl.DefaultDubboFilterHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author linzhou
 * @ClassName SpringConfig.java
 * @createTime 2021年12月13日 10:53:00
 * @Description
 */
@Configuration
public class DubboConfig {

    @Bean
    public DefaultDubboFilterHandler getDubboFilterHandler() {
        return new DefaultDubboFilterHandler();
    }

}
