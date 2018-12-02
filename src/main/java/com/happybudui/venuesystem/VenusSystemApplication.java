package com.happybudui.venuesystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

//CopyRight © 2018-2018 Happybudui All Rights Reserved
//Written by Happybudui

@SpringBootApplication
@EnableRedisHttpSession
@EnableCaching
@ServletComponentScan
public class VenusSystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(VenusSystemApplication.class, args);
    }
}

