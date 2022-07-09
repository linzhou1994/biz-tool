package com.spring.dubbo.filter;

import org.apache.dubbo.rpc.*;

/**
 * dubbo拦截器
 *
 * @author linzhou
 */
public interface DubboFilterHandler {


    /**
     * 消费者拦截器
     *
     * @param invoker
     * @param invocation
     * @return
     * @throws RpcException
     */
    default Result consumerFilter(Invoker<?> invoker, Invocation invocation) throws RpcException{
        return null;
    }

    /**
     * 生产者拦截器
     *
     * @param invoker
     * @param invocation
     * @return
     * @throws RpcException
     */
    default Result providerFilter(Invoker<?> invoker, Invocation invocation) throws RpcException{
        return null;
    }
}
