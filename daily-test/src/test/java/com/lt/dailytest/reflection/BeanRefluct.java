package com.lt.dailytest.reflection;

import com.alibaba.fastjson.JSON;
import com.lt.dailytest.bean.RetVO;

import java.util.Map;

/**
 * @author tong.luo
 * @description BeanRefluct
 * @date 2021/5/17 20:11
 */
public class BeanRefluct {
    public static void main(String[] args) {
        RetVO<Object> objectRetVO = new RetVO<>();
        System.out.println(JSON.toJSONString(objectRetVO.getClass()));

        Map<String, Object> map = (Map<String, Object>) JSON.parseObject(JSON.toJSONString(JSON.parseObject(JSON.toJSONString(objectRetVO), objectRetVO.getClass())), Map.class);
        System.out.println(JSON.toJSONString(map));

    }
}
