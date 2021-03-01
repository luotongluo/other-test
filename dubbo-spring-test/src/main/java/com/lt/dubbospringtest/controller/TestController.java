package com.lt.dubbospringtest.controller;

import com.alibaba.fastjson.JSON;
import com.lt.dubbospringtest.bean.TestDTO;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tong.luo
 * @description TestController
 * @date 2021/3/1 17:20
 */
@RestController
public class TestController {

    @RequestMapping(value = "testMethos1",method= RequestMethod.GET)
    public String testMethos1( TestDTO testDTO){
        return JSON.toJSONString(testDTO);
    }

    @RequestMapping(value = "testMethos2",method= RequestMethod.GET)
    public String testMethos2(@RequestParam("id")String id,@RequestParam("name")String name,@RequestParam("time")String time) throws Exception{
        TestDTO testDTO = new TestDTO();
        testDTO.setId(Integer.valueOf(id));
        testDTO.setName(name);
        String s[] = {"yyyy-MM-dd"};
        testDTO.setTime(DateUtils.parseDate(time,s));
        System.out.println(JSON.toJSONString(testDTO));;
        return JSON.toJSONString(testDTO);
    }
}
