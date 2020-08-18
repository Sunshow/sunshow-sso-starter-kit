package com.example;

import net.sunshow.sso.config.InitConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

@Import(InitConfig.class)
@EnableAuthorizationServer
@SpringBootApplication
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
