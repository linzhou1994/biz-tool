package com.java.utils.exception;

/**
 * @date : 2021/12/12 23:52
 * @author: linzhou
 * @description : ErrorCode
 */
public class SysErrorCode  implements ErrorCode{
    public static final SysErrorCode DEFAULT_ERROR= new SysErrorCode("000000","系统未知异常");
    private String code;
    private String msg;

    public SysErrorCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
