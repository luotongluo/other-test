package com.lt.dailytest.service;

import com.alibaba.fastjson.JSON;
import com.lt.dailytest.utils.JedisUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.lang.reflect.Constructor;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author tong.luo
 * @description redisUtilsTestServiceTest
 * @date 2021/4/1 17:43
 */
@SpringBootTest
public class RedisUtilsTestServiceTest {

    @Autowired
    private JedisUtils jedisUtils;

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private TestService testService;

    private RedisProperties redisProperties;

@Test
public void testnum(){
    Object num = this.testService.getNum();
    System.out.println(JSON.toJSONString(num));
}
    @Test
    public void test1() throws Exception {
        String key = "test1";
        jedisUtils.set(key, "value", 100);
        Object o = jedisUtils.get(key);
        System.out.println(JSON.toJSONString(o));
        Thread.sleep(1000);
         o = jedisUtils.get(key);
        System.out.println(JSON.toJSONString(o));
        Constructor<?>[] declaredConstructors = RedisProperties.Jedis.class.getDeclaredConstructors();

    }
}