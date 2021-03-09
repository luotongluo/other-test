package com.lt.dailytest.service.impl;

import com.lt.dailytest.service.MailSenderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
        String to = "1299391162@qq.com;1282875540@qq.com";
        String subject = "test";
        String content = "content";
        this.mailSenderService.sendSimpleMail(to,subject,content);
    }
}