
package com.lt.springcloudtest.controller;

import com.alibaba.fastjson.JSON;
import com.lt.springcloudtest.bean.TestBean;
import com.lt.springcloudtest.service.TestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
        List<TestBean> testBeanList = new ArrayList<>();
        TestBean testBean = new TestBean("12", 12, "22", new Date());
        testBeanList.add(testBean);
        testBeanList.add(new TestBean("22", 33, "44", new Date()));
        List<TestBean> beans = testBeanList.stream().filter(a -> a.getAge() > 20).collect(Collectors.toList());
        System.out.println(JSON.toJSONString(beans));
        return null;
    }
}
