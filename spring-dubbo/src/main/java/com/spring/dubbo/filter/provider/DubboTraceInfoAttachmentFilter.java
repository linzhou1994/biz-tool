package com.spring.dubbo.filter.provider;


import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.*;
import lombok.extern.slf4j.Slf4j;

/**
 * @author linzhou
 * @ClassName DubboTraceInfoAttachmentFilter.java
 * @createTime 2022年04月01日 16:02:00
 * @Description 服务消费方：附加trace_id的过滤器
 */

@Activate(group = {"provider"})
@Slf4j
public class DubboTraceInfoAttachmentFilter implements Filter {


    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        long startTime = System.currentTimeMillis();
        Result result = invoker.invoke(invocation);
        Object[] params = invocation.getArguments();
        Class<?> anInterface = invoker.getInterface();
        String methodName = invocation.getMethodName();
        String url = anInterface.getName()+"."+methodName;
        Object resultValue = result.getValue();
        long endTime = System.currentTimeMillis();
        long runTime = endTime - startTime;
        log.info("dubbo url:{},runTime:{}\n params:{}",url,runTime);
        return result;
    }
}
