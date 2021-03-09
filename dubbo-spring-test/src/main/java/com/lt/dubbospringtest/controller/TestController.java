package com.lt.dubbospringtest.controller;

import com.alibaba.fastjson.JSON;
import com.lt.dubbospringtest.IpUtils.IpUtils;
import com.lt.dubbospringtest.bean.TestDTO;
import com.lt.dubbospringtest.config.ZkApi;
import com.lt.dubbospringtest.config.ZookeeperConfig;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author tong.luo
 * @description TestController
 * @date 2021/3/1 17:20
 */
@RestController
public class TestController {
    @Autowired
    private ZkApi zkApi;

    @RequestMapping(value = "testMethos1",method= RequestMethod.GET)
    public String testMethos1(TestDTO testDTO, HttpServletRequest httpRequest){
        return JSON.toJSONString(testDTO);
    }

    @RequestMapping(value = "testMethos2",method= RequestMethod.GET)
    public String testMethos2(@RequestParam("id")String id,@RequestParam("name")String name,
                              @RequestParam("time")String time, HttpServletRequest httpRequest) throws Exception{
        TestDTO testDTO = new TestDTO();
        testDTO.setId(Integer.valueOf(id));
        testDTO.setName(name);
        String s[] = {"yyyy-MM-dd"};
        testDTO.setTime(DateUtils.parseDate(time,s));
        System.out.println(JSON.toJSONString(testDTO));;
        return JSON.toJSONString(testDTO);
    }

    /**
     * 获取ip
     * @return
     */
    @RequestMapping("getIp")
    public String getIp(HttpServletRequest request){
//        System.out.println(JSON.toJSONString(request));
        String ipAddr = IpUtils.getIpAddr(request);
        return JSON.toJSONString(ipAddr);
    }
}
