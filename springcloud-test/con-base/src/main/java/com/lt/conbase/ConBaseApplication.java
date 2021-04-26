package com.lt.conbase;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class ConBaseApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConBaseApplication.class, args);
    }

}
