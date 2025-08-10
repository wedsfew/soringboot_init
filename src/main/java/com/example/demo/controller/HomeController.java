package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 首页控制器
 * 提供应用的欢迎页面和基本信息
 */
@RestController
public class HomeController {

    /**
     * 首页接口
     * 返回应用的欢迎信息
     * 
     * @return 欢迎信息
     */
    @GetMapping("/")
    public Map<String, Object> home() {
        Map<String, Object> response = new HashMap<>();
        response.put("message", "欢迎使用Spring Boot Demo应用");
        response.put("status", "running");
        response.put("version", "1.0.0");
        return response;
    }

    /**
     * 健康检查接口
     * 用于监控系统检查应用是否正常运行
     * 
     * @return 健康状态信息
     */
    @GetMapping("/health")
    public Map<String, String> health() {
        Map<String, String> health = new HashMap<>();
        health.put("status", "UP");
        return health;
    }
}