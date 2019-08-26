package com.ao.account;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.ao.common.util.IdWorker;
import com.ao.common.util.JwtUtil;

@SpringBootApplication
@MapperScan("com.ao.account.mapper")
public class AccountApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountApplication.class, args);
    }

    @Bean
    public BCryptPasswordEncoder bcryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtUtil jwtUtil(){
        return new com.ao.common.util.JwtUtil();
    }

    @Bean
    public IdWorker idWorker(){
        return new IdWorker(1,1);
    }

}
