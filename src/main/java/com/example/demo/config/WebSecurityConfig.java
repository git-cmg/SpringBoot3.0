package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;

/**
 * Spring Security 配置
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
//                禁用CSRF
                .csrf(AbstractHttpConfigurer::disable)
//                配置跨域
                .cors(httpSecurityCorsConfigurer -> httpSecurityCorsConfigurer.configurationSource(request -> {
                    CorsConfiguration corsConfiguration = new CorsConfiguration();
//                    允许跨域
                    corsConfiguration.setAllowedOrigins(List.of("*"));
//                    允许所有请求方法
                    corsConfiguration.setAllowedMethods(List.of("*"));
//                    允许所有请求header
                    corsConfiguration.setAllowedHeaders(List.of("*"));
                    return corsConfiguration;
                }))
//                配置需要认证的路径
                .authorizeHttpRequests(
                        authorizationManagerRequestMatcherRegistry -> authorizationManagerRequestMatcherRegistry
//                        允许公开访问登录接口
                                .requestMatchers("/api/login").permitAll()
//                        `/api/**`下的所有请求都需要认证
                                .requestMatchers("/api/**").authenticated()
//                        其他请求允许公开访问
                                .anyRequest().permitAll())
//                配置会话策略为STATELESS，每个请求都是独立的，不依赖于之前的请求
                .sessionManagement(httpSecuritySessionManagementConfigurer -> httpSecuritySessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                添加过滤器，校验token
                .addFilterAfter(new JwtTokenFilter(jwtTokenUtil), UsernamePasswordAuthenticationFilter.class)
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
