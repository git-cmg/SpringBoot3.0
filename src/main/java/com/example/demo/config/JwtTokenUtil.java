package com.example.demo.config;

import cn.hutool.jwt.JWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Jwt 生成/校验token工具
 */
@Component
public class JwtTokenUtil {
    /**
     * 密钥
     */
    @Value("${jwt.secret}")
    private String secretKey;

    /**
     * 过期时间
     */
    @Value("${jwt.expiration}")
    private long expiration;

    /**
     * 生成 token
     *
     * @param username 用户名
     * @return token
     */
    public String generateToken(String username) {
        return JWT.create()
//                存储Payload信息，username 用户名
                .setPayload("username", username)
//                设置token过期时间
                .setExpiresAt(new Date(System.currentTimeMillis() + expiration))
//                设置密钥
                .setKey(secretKey.getBytes())
//                签名（hutool的JWT默认使用 HS265(HmacSHA256) 算法进行签名）
                .sign();
    }

    /**
     * 获取用户名
     *
     * @param token token
     * @return username
     */
    public String getUsernameFromToken(String token) {
        return (String) JWT.of(token).getPayload("username");
    }

    /**
     * 校验 token
     *
     * @param token token
     * @return boolean
     */
    public boolean validateToken(String token) {
        return JWT.of(token).setKey(secretKey.getBytes()).verify();
    }
}