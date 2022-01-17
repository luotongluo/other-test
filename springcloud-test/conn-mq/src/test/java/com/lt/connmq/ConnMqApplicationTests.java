package com.lt.connmq;

import com.lt.springcloudcommon.mqfile.MqConts;
import com.rabbitmq.client.Channel;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.connection.Connection;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
class ConnMqApplicationTests {
    Logger logger = LoggerFactory.getLogger(ConnMqApplicationTests.class);
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    void contextLoads() {
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        rabbitTemplate.convertAndSend(MqConts.testExchange, MqConts.testRoutKey, "message", correlationData);
        logger.info("SendMessageServiceImpl() >>> 发送消息到RabbitMQ, 消息内容: " + "");
    }

    @Test
    void testMqConnection() throws Exception {
        ConnectionFactory connectionFactory = this.rabbitTemplate.getConnectionFactory();
        ConnectionFactory publisherConnectionFactory = connectionFactory.getPublisherConnectionFactory();
        Connection connection = connectionFactory.createConnection();
        Channel channel = connection.createChannel(true);
        channel.exchangeDeclare("", "");
    }
}
