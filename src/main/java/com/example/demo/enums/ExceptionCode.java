package com.example.demo.enums;

import com.example.demo.common.ErrorCode;
import lombok.Getter;

/**
 * 异常状态码（1000系列，范围：1000-1200）
 */
@Getter
public enum ExceptionCode implements ErrorCode {
    EXCEPTION_ERROR(1000, "异常错误"),
    //    @Validated 校验异常
    BINDEXCEPTION_ERROR(1001, "参数校验失败");

    /**
     * 错误类型代码
     */
    private final int errorCode;
    /**
     * 显示给用户的错误信息
     */
    private final String errorMessage;

    ExceptionCode(int errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}
