package com.metocs.main;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@EnableFeignClients
@EnableResourceServer
@SpringBootApplication
@MapperScan(value = "com.metocs.main.mapper")
@ComponentScan(value = "com.metocs")
@EnableGlobalMethodSecurity(prePostEnabled = true,securedEnabled = true)
public class CloudMainApplication {
    public static void main(String[] args) {
        SpringApplication.run(CloudMainApplication.class, args);
    }
}


