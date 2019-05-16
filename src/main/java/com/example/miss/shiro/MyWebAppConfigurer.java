package com.example.miss.shiro;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 跨域请求的后台配置
 */
@Configuration
public class MyWebAppConfigurer implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")//允许所有url进行跨域请求
                .allowedOrigins("*")
                .allowedMethods("GET", "POST")//
                .allowCredentials(true).maxAge(3600);
    }

}
