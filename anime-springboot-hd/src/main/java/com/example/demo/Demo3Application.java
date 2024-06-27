package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.web.client.RestTemplate;

import java.net.InetAddress;

@Slf4j
@SpringBootApplication
public class Demo3Application implements CommandLineRunner {

    @Autowired
    private RestTemplateBuilder builder;

    @Value("${springdoc.swagger-ui.path:/swagger-ui.html}") // 默认路径，也可以从配置文件中获取
    private String swaggerUiPath;

    @Value("${server.servlet.context-path:/}") // 应用上下文路径，如果有的话
    private String contextPath;

    @Bean
    public RestTemplate restTemplate() {
        return builder.build();
    }

    public static void main(String[] args) {
        SpringApplication.run(Demo3Application.class, args);
    }

    public void run(String... args) throws Exception {
        // 假设你使用的是默认的服务器端口8080
        String baseUrl = "http://localhost:8080";
        // 如果你的应用有上下文路径，则拼接上
        if (!contextPath.isEmpty() && !"/".equals(contextPath)) {
            baseUrl += contextPath;
        }
        // 完整的Swagger UI地址
        String swaggerUiUrl = baseUrl + swaggerUiPath;
        // 打印到日志
        log.info("\n----------------------------------------------------------\n\t" +
                "Application is running! Access URLs:\n\t" +
                "Local: "+baseUrl+"\n\t" +
                "Swagger文档:" + swaggerUiUrl + "\n\t" +
                "----------------------------------------------------------");
    }
}
