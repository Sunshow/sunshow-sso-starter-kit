package com.example;

import net.sunshow.sso.config.InitConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@Import(InitConfig.class)
@SpringBootApplication
public class SSOServerApp {
    public static void main(String[] args) {
        SpringApplication.run(SSOServerApp.class, args);
    }
}
