package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 认证控制器
 * 提供用户登录和认证相关的API接口
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 用户登录
     * 验证用户名和密码，成功后返回用户信息
     * 
     * @param loginRequest 登录请求，包含用户名和密码
     * @return 登录结果
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> loginRequest) {
        String username = loginRequest.get("username");
        String password = loginRequest.get("password");
        
        // 参数验证
        if (username == null || password == null) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "用户名和密码不能为空");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        
        // 查询用户
        User user = userService.getUserByUsername(username);
        if (user == null) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "用户不存在");
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
        
        // 验证密码
        if (!passwordEncoder.matches(password, user.getPassword())) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "密码错误");
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
        
        // 登录成功，返回用户信息（不包含密码）
        Map<String, Object> response = new HashMap<>();
        response.put("id", user.getId());
        response.put("username", user.getUsername());
        response.put("email", user.getEmail());
        response.put("phone", user.getPhone());
        response.put("status", user.getStatus());
        response.put("message", "登录成功");
        
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    /**
     * 注册新用户
     * 
     * @param registerRequest 注册请求，包含用户信息
     * @return 注册结果
     */
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User registerRequest) {
        // 参数验证
        if (registerRequest.getUsername() == null || registerRequest.getPassword() == null) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "用户名和密码不能为空");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        
        // 检查用户名是否已存在
        if (userService.getUserByUsername(registerRequest.getUsername()) != null) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "用户名已存在");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        
        // 创建新用户
        try {
            User createdUser = userService.createUser(registerRequest);
            
            // 返回用户信息（不包含密码）
            Map<String, Object> response = new HashMap<>();
            response.put("id", createdUser.getId());
            response.put("username", createdUser.getUsername());
            response.put("email", createdUser.getEmail());
            response.put("phone", createdUser.getPhone());
            response.put("status", createdUser.getStatus());
            response.put("message", "注册成功");
            
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "注册失败: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}