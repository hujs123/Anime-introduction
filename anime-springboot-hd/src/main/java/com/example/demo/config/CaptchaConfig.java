package com.example.demo.config;

import com.google.code.kaptcha.Producer;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * @author hujs
 * @date 2024/07/24
 * @description
 */
@Configuration
public class CaptchaConfig {
    @Bean
    public Producer captchaProducer() {
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        // 配置验证码属性
        Properties properties = new Properties();
        properties.put("kaptcha.border", "yes");
        properties.put("kaptcha.border.color", "105,179,90");
        // 更多属性...
        defaultKaptcha.setConfig(new Config(properties));
        return defaultKaptcha;
    }
}
