package com.spring.dubbo.filter;

import com.alibaba.dubbo.rpc.*;
import com.alibaba.dubbo.rpc.protocol.dubbo.DecodeableRpcInvocation;
import com.spring.dubbo.filter.impl.DefaultDubboFilterHandler;
import com.spring.utils.bean.SpringUtil;

import java.util.List;
import java.util.Objects;

/**
 * CopyRight : <company domain>
 * Project :  biz-tool
 * Comments :dubbo拦截器管理者
 * JDK version : JDK1.8
 * Create Date : 2022-07-09 17:58
 *
 * @author : linzhou
 * @version : 1.0
 * @since : 1.0
 */
public class DubboFilterHandlerManager {


    private static List<DubboFilterHandler> handlers;


    private static List<DubboFilterHandler> getDubboFilterHandlers() {
        if (Objects.isNull(handlers)) {
            handlers = SpringUtil.getBeanList(DubboFilterHandler.class);
        }
        return handlers;
    }


    public static Result filter(Invoker<?> invoker, Invocation invocation) throws RpcException {
        List<DubboFilterHandler> dubboFilterHandlers = getDubboFilterHandlers();

        DefaultDubboFilterHandler defaultDubboFilterHandler = null;
        for (DubboFilterHandler dubboFilterHandler : dubboFilterHandlers) {
            if (dubboFilterHandler instanceof DefaultDubboFilterHandler) {
                //默认的拦截器最后执行
                defaultDubboFilterHandler = (DefaultDubboFilterHandler) dubboFilterHandler;
                continue;
            }
            Result result = getResult(invoker, invocation, dubboFilterHandler);
            if (Objects.nonNull(result)) {
                return result;
            }
        }
        return getResult(invoker, invocation, defaultDubboFilterHandler);
    }

    private static Result getResult(Invoker<?> invoker, Invocation invocation, DubboFilterHandler dubboFilterHandler) {
        if (Objects.isNull(dubboFilterHandler)) {
            return null;
        }
        Result result;
        if (invocation instanceof DecodeableRpcInvocation) {
            result = dubboFilterHandler.consumerFilter(invoker, invocation);
        } else {
            result = dubboFilterHandler.providerFilter(invoker, invocation);
        }
        return result;
    }


}
