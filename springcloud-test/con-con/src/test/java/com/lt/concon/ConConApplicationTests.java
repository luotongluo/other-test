package com.lt.concon;

import com.lt.concon.utils.RedisUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ConConApplicationTests {
    @Autowired
    private RedisUtils redisUtils;

    @Test
    public void contextLoads() {
        this.redisUtils.set("test", "test");
        Object test = this.redisUtils.get("test");
        System.out.println(test);
    }

}
