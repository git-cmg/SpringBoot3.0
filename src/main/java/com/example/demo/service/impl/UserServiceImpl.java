package com.example.demo.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.crypto.digest.BCrypt;
import com.example.demo.config.JwtTokenUtil;
import com.example.demo.entity.UserEntity;
import com.example.demo.enums.APIExceptionCode;
import com.example.demo.exception.APIException;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@Data
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final JwtTokenUtil jwtTokenUtil;

    @Override
    public String login(String username, String password) {
        APIExceptionCode loginError = APIExceptionCode.LOGIN_ERROR;

        UserEntity userEntity = userRepository.findByUsername(username);

//        未找到用户
        if (ObjectUtil.isNull(userEntity)) {
            throw new APIException(loginError.getErrorCode(), loginError.getErrorMessage());
        } else {
            boolean checkpw = BCrypt.checkpw(password, userEntity.getPassword());

//        密码错误
            if (!checkpw) {
                throw new APIException(loginError.getErrorCode(), loginError.getErrorMessage());
            }

//            生成token
            String token = jwtTokenUtil.generateToken(username);
//            生成 Spring Security身份令牌，用于存储用户信息（用户名、密码、权限列表）
            Authentication authentication = new UsernamePasswordAuthenticationToken(username, null, null);
//            将创建的身份令牌设置到 Spring Security上下文中
            SecurityContextHolder.getContext().setAuthentication(authentication);

//            日志记录登录信息
            log.info("{} login", username);
            return token;
        }

    }
}
