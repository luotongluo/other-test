
package com.lt.springcloudtest.controller;

import com.alibaba.fastjson.JSON;
import com.google.gson.GsonBuilder;
import com.lt.springcloudtest.bean.TestBean;
import com.lt.springcloudtest.enums.TimeEnums;
import com.lt.springcloudtest.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Map;

/**
 * @author tong.luo
 * @description TestController
 * @date 2021/1/4 15:28
 */
@RestController
public class TestController {
    @Autowired
    private TestService testService;

    @RequestMapping("testJson")
    public Map testJson(){
        TestBean testBean = new TestBean("123", 22, "33",new Date());
        String jsonString = JSON.toJSONString(testBean);
        String jsonGson = new GsonBuilder().setDateFormat(TimeEnums.YYYYMMDDHHMMSS).create().toJson(testBean);

        System.out.println(jsonGson);
        System.out.println(jsonString);
        return null;
    }
}
