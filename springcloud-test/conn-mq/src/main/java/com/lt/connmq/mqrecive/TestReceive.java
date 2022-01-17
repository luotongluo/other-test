package com.lt.connmq.mqrecive;

import com.lt.springcloudcommon.mqfile.MqConts;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

/**
 * @author admin
 * @description TestReceive
 * @date 2021/12/29 17:07
 */
@Component("TestReceive")
public class TestReceive {
    private static Logger logger = LoggerFactory.getLogger(TestReceive.class);

    @RabbitHandler
    @RabbitListener(queues = MqConts.testQueue)
    public void receiveMessage(@Payload String message, @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag,
                               Channel channel/*, Message message*/) {
        logger.info("direct1:{}", message);
        try {
            //channel.basicAck(deliveryTag, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
