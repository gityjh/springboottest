package com.example.springboot.demo.config;

import com.example.springboot.demo.service.HelloService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyAppConfig {
    @Bean
    public HelloService helloService(){
        return new HelloService();
    }
}
