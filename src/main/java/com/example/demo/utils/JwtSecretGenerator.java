package com.example.demo.utils;

import java.security.SecureRandom;
import java.util.Base64;

/**
 * 随机生成JWT密钥工具
 */
public class JwtSecretGenerator {
    public static void main(String[] args) {
//        生成一个256位的随机字节数组
        byte[] secretBytes = new byte[32];
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(secretBytes);

//        将字节数组转换为Base64编码字符串
        String secretKey = Base64.getEncoder().encodeToString(secretBytes);

        System.out.println("Generated JWT Secret Key: " + secretKey);
    }
}
