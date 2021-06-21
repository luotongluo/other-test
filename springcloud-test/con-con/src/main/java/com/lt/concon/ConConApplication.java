package com.lt.concon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class ConConApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConConApplication.class, args);
    }

}
