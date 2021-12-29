package com.lt.connmq.controller;

import com.lt.springcloudcommon.mqfile.MqConts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author admin
 * @description MqController
 * @date 2021/12/29 17:04
 */
@RequestMapping("mq")
@RestController
public class MqController {
    Logger logger = LoggerFactory.getLogger(MqController.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;


    @RequestMapping("test")
    public String test() {
        return "success::" ;
    }

    @RequestMapping("sendMessage")
    public String sendMessage(@RequestParam(name = "msg") String msg) {
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        String conten = msg + "---" + correlationData;
        rabbitTemplate.convertAndSend(MqConts.testExchange, MqConts.testRoutKey, conten, correlationData);
        logger.info("SendMessageServiceImpl() >>> 发送消息到RabbitMQ, 消息内容: [{}]", conten);
        return "success";
    }
}
