package com.lt.dailytest.service.impl;

import com.lt.dailytest.service.MailSenderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author tong.luo
 * @description MailSenderServiceImplTest
 * @date 2021/3/8 16:33
 */
@SpringBootTest
public class MailSenderToolServiceImplTest {

    @Autowired
    private MailSenderService mailSenderService;

    @Test
    public void tet1(){
//        String to = "1299391162@qq.com";
        String to = "luo12828@foxmail.com";
        for (int i = 0; i < 10; i++) {
            String subject = UUID.randomUUID().toString() + "_" + i;
            String content = "com.lt.dailytest.service.impl.MailSenderToolServiceImplTest.tet1 content"+ i;
            this.mailSenderService.sendSimpleMail(to,subject,content);

        }
    }
}