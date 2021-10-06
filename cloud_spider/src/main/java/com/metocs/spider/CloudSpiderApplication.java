package com.metocs.spider;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;


@SpringBootApplication
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
@MapperScan(value ="com.metocs.spider.mapper")
@ComponentScan(value = "com.metocs")
public class CloudSpiderApplication {
    public static void main(String[] args) {
        SpringApplication.run(CloudSpiderApplication.class, args);
    }
}
