package com.java.utils.exception;

/**
 * @author linzhou
 */
public interface ErrorCode {
    /**
     * 获取错误码
     *
     * @return
     */
    String getCode();

    /**
     * 获取错误消息
     *
     * @return
     */
    String getMsg();
}
