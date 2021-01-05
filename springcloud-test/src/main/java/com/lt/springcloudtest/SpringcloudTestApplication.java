package com.lt.springcloudtest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author tong.luo
 */
@SpringBootApplication(scanBasePackages  = {"com.lt.springcloudtest"})
public class SpringcloudTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringcloudTestApplication.class, args);
    }

}
