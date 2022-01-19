package com.lt.concon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tong.luo
 * @description
 * @date 2022/1/18 15:13
 */
@RestController
public class TestController {
    @Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping("dc")
    public String dc() {
        //获取 注册中心的注册的实例信息
        String services = "Services: " + discoveryClient.getServices();
        return services;
    }
}
