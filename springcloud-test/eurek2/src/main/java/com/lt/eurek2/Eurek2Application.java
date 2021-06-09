package com.lt.eurek2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class Eurek2Application {

    public static void main(String[] args) {
        SpringApplication.run(Eurek2Application.class, args);
    }

}