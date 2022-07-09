package com.spring.dubbo.annotation;

import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * @author linzhou
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@DubboService
@Component
public @interface RpcService {
}
