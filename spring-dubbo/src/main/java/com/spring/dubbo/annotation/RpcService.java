package com.spring.dubbo.annotation;

import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * @author linzhou
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Service
@Component
public @interface RpcService {
}
