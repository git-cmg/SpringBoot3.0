/*spring-security*/
package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
//                配置需要认证的路径
                .authorizeHttpRequests(
                        authorizationManagerRequestMatcherRegistry -> authorizationManagerRequestMatcherRegistry
//                        `/api/**`下的所有请求都需要认证
                                .requestMatchers("/api/**").authenticated()
//                        其他请求允许公开访问
                                .anyRequest().permitAll())
//                配置登录和登出路径
                .formLogin(httpSecurityFormLoginConfigurer -> httpSecurityFormLoginConfigurer
//                        设置登录页面URL，允许所有用户访问登录页面
                        .loginPage("/index.html").permitAll())
                .logout(httpSecurityLogoutConfigurer -> httpSecurityLogoutConfigurer
//                        设置注销后重定向的URL
                        .logoutSuccessUrl("/index.html").permitAll());

        return http.build();
    }
}
