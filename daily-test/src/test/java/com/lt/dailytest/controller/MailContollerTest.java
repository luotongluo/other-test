package com.lt.dailytest.controller;

import com.lt.dailytest.bean.RetVO;
import com.lt.dailytest.vo.ReqVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author tong.luo
 * @description MailContollerTest
 * @date 2021/3/8 17:45
 */
public class MailContollerTest {
    @Autowired
    private MailContoller mailContoller;

    @Test
    void sendHtmlMail() {
        ReqVO reqVO = new ReqVO();
        reqVO.setTo("1299391162@qq.com;1282875540@qq.com");
        reqVO.setContent("setContent");
        reqVO.setSubject("test");
        RetVO<Object> retVO = this.mailContoller.sendHtmlMail(reqVO);
        System.out.println(reqVO);
    }
}