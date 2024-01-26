package com.example.demo.controller;

import com.example.demo.dto.LoginDto;
import com.example.demo.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "登录")
@Data
@Slf4j
@RequestMapping("/api")
public class LoginController {

    private final UserService userService;

    @Operation(summary = "用户登录")
    @ApiResponse(description = "token")
    @PostMapping("/login")
    public String login(
            @Parameter(name = "loginDto", description = "用户信息", required = true) @Validated @RequestBody LoginDto loginDto) {
        return userService.login(loginDto.getUsername(), loginDto.getPassword());
    }
}
