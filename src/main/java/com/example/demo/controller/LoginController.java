package com.example.demo.controller;

import com.example.demo.config.JwtTokenUtil;
import com.example.demo.dto.LoginDto;
import com.example.demo.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "登录")
@Data
@Slf4j
@RequestMapping("/api")
public class LoginController {

    private final UserService userService;
    private final JwtTokenUtil jwtTokenUtil;

    @Operation(summary = "用户登录")
    @ApiResponse(description = "token")
    @PostMapping("/login")
    public String login(
            @Validated @RequestBody LoginDto loginDto) {
        return userService.login(loginDto.getUsername(), loginDto.getPassword());
    }

    @Operation(summary = "用户登出")
    @ApiResponse(description = "message")
    @GetMapping("/logout")
    public String logout(
            @Parameter(name = "Authorization", description = "token", required = true) @RequestHeader("Authorization") String header) {
        String token = header.replace("Bearer ", "");
        String username = jwtTokenUtil.getUsernameFromToken(token);
        return userService.logout(username);
    }
}
