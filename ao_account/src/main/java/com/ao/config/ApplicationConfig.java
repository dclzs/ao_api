package com.ao.config;

import com.ao.filter.JwtFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import javax.annotation.Resource;

@Configuration
public class ApplicationConfig extends WebMvcConfigurationSupport {
    @Resource
    private JwtFilter jwtFilter;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtFilter).
                addPathPatterns("/**").
                excludePathPatterns("/**/login");
    }
}