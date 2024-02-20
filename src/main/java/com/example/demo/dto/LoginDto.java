package com.example.demo.dto;

import io.swagger.v3.oas.annotations.dedia.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LoginDto {
    /**
     * 用户名
     */
    @NotNull(message = "用户名不允许为空")
    @Schema(description = "用户名")
    private String username;

    /**
     * 密码
     */
    @NotNull(message = "密码不允许为空")
    @Schema(description = "密码")
    private String password;
}
