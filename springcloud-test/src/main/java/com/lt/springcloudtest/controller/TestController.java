
package com.lt.springcloudtest.controller;

import com.lt.springcloudtest.service.TestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author tong.luo
 * @description TestController
 * @date 2021/1/4 15:28
 */
@RestController
public class TestController {
    private static Logger logger = LoggerFactory.getLogger(TestController.class);
    @Autowired
    private TestService testService;

    @RequestMapping("testJson")
    public Map testJson() {
        this.testService.testJson("123");

        return null;
    }
}
