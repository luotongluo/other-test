package com.lt.eureka3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class Eureka3Application {

    public static void main(String[] args) {
        SpringApplication.run(Eureka3Application.class, args);
    }

}
