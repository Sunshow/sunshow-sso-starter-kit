package com.example;

import net.sunshow.sso.config.InitConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@Import(InitConfig.class)
@SpringBootApplication
public class SSOServerApp {
    public static void main(String[] args) {
        SpringApplication.run(SSOServerApp.class, args);
    }
}
