package com.example.demo.controller;

import com.example.demo.dto.LoginDto;
import com.example.demo.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "登录")
@Data
@Slf4j
@RequestMapping("/api")
public class LoginController {

    private final UserService userService;

    @ApiOperation(value = "用户登录")
    @PostMapping("/login")
    public String login(@ApiParam(name = "用户信息", required = true) @Validated @RequestBody LoginDto loginDto) {
        return userService.login(loginDto.getUsername(), loginDto.getPassword());
    }
}
