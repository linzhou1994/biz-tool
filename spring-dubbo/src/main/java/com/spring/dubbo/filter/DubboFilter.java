package com.spring.dubbo.filter;


import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.*;
import lombok.extern.slf4j.Slf4j;

/**
 * @author linzhou
 * @ClassName DubboTraceInfoAttachmentFilter.java
 * @createTime 2022年04月01日 16:02:00
 * @Description 服务消费方：附加trace_id的过滤器
 */

@Activate(group = {Constants.CONSUMER, Constants.PROVIDER})
@Slf4j
public class DubboFilter implements Filter {


    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        long startTime = System.currentTimeMillis();

        Result result = DubboFilterHandlerManager.filter(invoker, invocation);

        Object[] params = invocation.getArguments();
        Class<?> anInterface = invoker.getInterface();
        String methodName = invocation.getMethodName();
        String url = anInterface.getName() + "." + methodName;
        Object resultValue = result.getValue();
        long endTime = System.currentTimeMillis();
        long runTime = endTime - startTime;
        if (result.hasException()) {
            log.info("dubbo url:{},runTime:{}\n params:{}\nresult:{}", url, runTime, params, resultValue, result.getException());
        } else {
            log.info("dubbo url:{},runTime:{}\n params:{}\nresult:{}", url, runTime, params, resultValue);
        }
        return result;
    }
}
