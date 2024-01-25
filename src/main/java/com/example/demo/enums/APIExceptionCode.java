package com.example.demo.enums;

import com.example.demo.common.ErrorCode;
import lombok.Getter;

/**
 * 业务异常状态码（1000系列，范围：1201-1999）
 */
@Getter
public enum APIExceptionCode implements ErrorCode {
    RESPONSE_PACK_ERROR(1201, "response返回包装失败"),
    //    登录
    LOGIN_ERROR(1202, "用户名或密码错误");

    /**
     * 错误类型代码
     */
    private final int errorCode;
    /**
     * 显示给用户的错误信息
     */
    private final String errorMessage;

    APIExceptionCode(int errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}
