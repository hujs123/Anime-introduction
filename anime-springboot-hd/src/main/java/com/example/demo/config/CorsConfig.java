package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // 允许所有路径的跨域请求
                .allowedOrigins("http://localhost:8082") // 允许指定的源
                .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS") // 允许的HTTP方法
                .allowedHeaders("*") // 允许的头信息
                .maxAge(3600) // 预检请求的缓存时间（秒）
                .allowCredentials(true) // 是否允许发送Cookie
                .allowedHeaders("*"); // 允许哪些HTTP头
    }
}
