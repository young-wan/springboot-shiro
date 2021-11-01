package com.young.shiro.sprngbootshiro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SprngbootShiroApplication {

    public static void main(String[] args) {
        SpringApplication.run(SprngbootShiroApplication.class, args);
    }

}
