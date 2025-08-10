package com.example.demo.config;

import com.example.demo.security.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * 安全配置类
 * 配置Spring Security相关的安全策略
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private CustomUserDetailsService userDetailsService;
    
    /**
     * 配置认证管理器构建器
     * 
     * @param auth 认证管理器构建器
     * @throws Exception 异常信息
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
            .passwordEncoder(passwordEncoder());
    }

    /**
     * 配置安全过滤链
     * 
     * @param http HttpSecurity对象
     * @return SecurityFilterChain对象
     * @throws Exception 异常信息
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // 为了简化开发，暂时禁用CSRF保护，并允许所有请求
        // 在生产环境中应该根据实际需求配置更严格的安全策略
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/", "/health").permitAll()
                .requestMatchers("/api/auth/**").permitAll() // 允许认证相关API无需认证
                .requestMatchers("/api/users/**").authenticated() // 用户相关API需要认证
                .anyRequest().authenticated()
            )
            .httpBasic(httpBasic -> {})
            .formLogin(formLogin -> formLogin.disable()); // 禁用表单登录，使用自定义认证
        
        return http.build();
    }

    /**
     * 密码编码器
     * 用于对密码进行加密和验证
     * 
     * @return PasswordEncoder对象
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    /**
     * 配置认证管理器
     * 
     * @param authenticationConfiguration 认证配置
     * @return AuthenticationManager对象
     * @throws Exception 异常信息
     */
    @Bean
    public AuthenticationManager authenticationManager(
            org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}