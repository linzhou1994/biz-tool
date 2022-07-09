package com.spring.dubbo.filter.impl;

import org.apache.dubbo.rpc.*;
import com.spring.dubbo.filter.DubboFilterHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * CopyRight : <company domain>
 * Project :  biz-tool
 * Comments : <对此类的描述，可以引用系统设计中的描述>
 * JDK version : JDK1.8
 * Create Date : 2022-07-09 18:07
 *
 * @author : linzhou
 * @version : 1.0
 * @since : 1.0
 */
@Component
@Order
@Slf4j
public class DefaultDubboFilterHandler implements DubboFilterHandler {


    @Override
    public Result consumerFilter(Invoker<?> invoker, Invocation invocation) throws RpcException {
        log.info("consumerFilter================");
        return invoker.invoke(invocation);
    }

    @Override
    public Result providerFilter(Invoker<?> invoker, Invocation invocation) throws RpcException {
        log.info("providerFilter================");
        return invoker.invoke(invocation);
    }
}
