package com.springboot.rocketmq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * CopyRight : <company domain>
 * Project :  biz-tool
 * Comments : <对此类的描述，可以引用系统设计中的描述>
 * JDK version : JDK1.8
 * Create Date : 2022-08-09 15:17
 *
 * @author : linzhou
 * @version : 1.0
 * @since : 1.0
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com"})
public class SpringBootDemoMqRocketmqApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootDemoMqRocketmqApplication.class, args);
    }

}
