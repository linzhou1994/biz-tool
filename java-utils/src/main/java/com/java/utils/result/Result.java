package com.java.utils.result;

import com.java.utils.exception.BizException;
import com.java.utils.exception.ErrorCode;

import java.io.Serializable;

/**
 * CopyRight : <company domain>
 * Project :  biz-tool
 * Comments : <对此类的描述，可以引用系统设计中的描述>
 * JDK version : JDK1.8
 * Create Date : 2022-06-25 00:45
 *
 * @author : linzhou
 * @version : 1.0
 * @since : 1.0
 */
public class Result<T> implements Serializable {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1475348231900998033L;
    /**
     * 请求结果
     */
    private boolean success;
    /**
     * 请求code
     */
    private String code;
    /**
     * 请求描述
     */
    private String message;
    /**
     * 返回内容
     */
    private T data;


    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static Result success(){
        Result result = new Result();
        result.setSuccess(true);
        return result;
    }
    public static <T> Result<T> success(T data){
        Result<T> result = new Result<T>();
        result.setSuccess(true);
        result.setData(data);
        return result;
    }

    public static Result error(BizException e){
        Result result = new Result();
        result.setSuccess(false);
        result.setCode(e.getCode());
        result.setMessage(e.getErrorMessage());
        return result;
    }



}
