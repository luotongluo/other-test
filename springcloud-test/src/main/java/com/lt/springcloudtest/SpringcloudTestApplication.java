package com.lt.springcloudtest;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author tong.luo
 */
@EnableTransactionManagement
@MapperScan(basePackages = {"com.lt.springcloudtest.mapper"})
@SpringBootApplication
public class SpringcloudTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringcloudTestApplication.class, args);
    }

}
