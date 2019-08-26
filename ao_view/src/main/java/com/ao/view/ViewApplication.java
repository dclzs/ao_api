package com.ao.view;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.ao.common.util.IdWorker;
import com.ao.common.util.JwtUtil;

@SpringBootApplication
public class ViewApplication {

    public static void main(String[] args) {
        SpringApplication.run(ViewApplication.class, args);
    }

    @Bean
    public JwtUtil jwtUtil(){
        return new com.ao.common.util.JwtUtil();
    }

    @Bean
    public IdWorker idWorker(){
        return new IdWorker(1,2);
    }

}
