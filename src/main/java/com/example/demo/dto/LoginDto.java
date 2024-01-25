package com.example.demo.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LoginDto {
    /**
     * 用户名
     */
    @NotNull(message = "用户名不允许为空")
    private String username;

    /**
     * 密码
     */
    @NotNull(message = "密码不允许为空")
    private String password;
}
