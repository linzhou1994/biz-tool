package com.spring.utils.controller.advice;


import com.java.utils.exception.BizException;
import com.java.utils.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常处理
 *
 * @author xuanhongjian
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({BizException.class})
    @ResponseBody
    public Result handleIllegalArgumentException(BizException e, HttpServletRequest request) {
        log.error("BizException url:{} message:{}", request.getRequestURI(), e.getErrorMessage(), e);
        return Result.error(e);
    }

    @ExceptionHandler({Exception.class})
    @ResponseBody
    public Result handleException(Exception e, HttpServletRequest request) {
        log.error("Exception url:{} message:{}", request.getRequestURI(), e.getMessage(), e);
        Result result =new Result();
        result.setSuccess(false);
        result.setMessage("网络环境不稳定，请稍后重试！");
        return result;
    }
}
