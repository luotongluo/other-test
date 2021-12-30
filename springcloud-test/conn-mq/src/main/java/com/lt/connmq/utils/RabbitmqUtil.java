package com.lt.connmq.utils;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.connection.Connection;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author admin
 * @description RabbitmqUtil
 * @date 2021/12/30 11:18
 */
@Component
public class RabbitmqUtil {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    private static RabbitTemplate rabbitTemplatestatic;

    @PostConstruct
    public void init() {
        rabbitTemplatestatic = rabbitTemplate;
    }

    /**
     * 获取连接对象
     *
     * @return
     */
    public static Channel getChannel(boolean transaction) {
        ConnectionFactory connectionFactory = rabbitTemplatestatic.getConnectionFactory();
        Connection connection = connectionFactory.createConnection();
        Channel channel = connection.createChannel(transaction);
        return channel;
    }
}
