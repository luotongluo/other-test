package com.lt.dailytest.service.impl;

import com.alibaba.fastjson.JSON;
import com.lt.dailytest.service.MailSenderService;
import com.lt.dailytest.vo.MailVo;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private MailSenderService mailSenderService;
    @Autowired
    private MailSenderToolService mailSenderToolService;
    private String to = "luo12828@foxmail.com";
    private String content = "com.lt.dailytest.service.MailSenderService \r\n [test] \n content";
    private String defaultSubject = "test-connect-sendmail";

    @Test
    public void tet1() {
//        String to = "1299391162@qq.com";
//        for (int i = 0; i < 10; i++) {
        String subject = UUID.randomUUID().toString() + "_" /*+ i*/;

        this.mailSenderService.sendSimpleMail(to, subject, content);

//        }
    }

    @Test
    public void testMailOfTools(){
        MailVo mailVo = new MailVo();
        mailVo.setTo(to);
        mailVo.setSubject(defaultSubject);
        mailVo.setText(content);
        long start = System.currentTimeMillis();
        logger.info("send mail info:[{}]", JSON.toJSONString(mailVo));
        this.mailSenderToolService.sendMail(mailVo);
        logger.info("cost:[{}]",(System.currentTimeMillis() - start));
    }
}