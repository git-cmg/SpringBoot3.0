package com.example.demo.common;

/**
 * 统一返回错误接口
 */
public interface ErrorCode {
    /**
     * 错误类型代码
     */
    public int getErrorCode();

    /**
     * 显示给用户的错误信息
     */
    public String getErrorMessage();
}
