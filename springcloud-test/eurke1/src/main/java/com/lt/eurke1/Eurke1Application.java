package com.lt.eurke1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class Eurke1Application {

    public static void main(String[] args) {
        SpringApplication.run(Eurke1Application.class, args);
    }

}
