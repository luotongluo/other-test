package com.lt.conbase.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tong.luo http://conbase:pwd@localhost:8010/TestController/testController
 * @description TestController
 * @date 2021/11/15 19:20
 */
@RestController
@RequestMapping("test")
public class TestController {
    @RequestMapping("testController")
    public String testController() {
        return "Hello";
    }
}
