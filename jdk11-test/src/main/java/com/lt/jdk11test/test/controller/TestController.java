package com.lt.jdk11test.test.controller;

import com.lt.jdk11test.test.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 12828
 * @description TestController
 * @date 2022/05/07 11:31
 */
@RestController
@RequestMapping("test")
public class TestController {
    @Autowired
    private TestService testService;

    @RequestMapping("testMethod")
    public String testMethod(){
        return this.testService.testMethod();
    }
}
