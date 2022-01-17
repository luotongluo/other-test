package com.lt.connmq.mqtest;

import com.lt.connmq.utils.RabbitmqUtil;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.UUID;

/**
 * @author tong.luo
 * @description tong.luo
 * 队列达到最大长度的情况下
 * @date 2021/12/30 15:18
 */
@SpringBootTest
class MqTest2 {
    Logger logger = LoggerFactory.getLogger(MqTest2.class);
    public static final String DEAD_EXCHANGE = "dead_exchange_1";
    public static final String DEAD_QUEUE = "dead_queue_1";
    public static final String DEAD_ROUT_KEY = "dead_route_1";

    public static final String NORMAL_EXCHANGE = "normal_exchange_1";
    public static final String NORMAL_QUEUE = "normal_queue_1";
    public static final String NORMAL_ROUT_KEY = "normal_route_1";

    @Test
    void initQueueAndExchange() throws Exception {
        Channel channel = RabbitmqUtil.getChannel(false);
        // 死信交换机
        channel.exchangeDeclare(DEAD_EXCHANGE, BuiltinExchangeType.DIRECT);
        // 普通交换机
        channel.exchangeDeclare(NORMAL_EXCHANGE, BuiltinExchangeType.DIRECT);

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("x-dead-letter-exchange", DEAD_EXCHANGE);
        hashMap.put("x-dead-letter-routing-key", DEAD_ROUT_KEY);
        hashMap.put("x-max-length", 6);

        channel.queueDeclare(NORMAL_QUEUE, false, false, false, hashMap);
        channel.queueBind(NORMAL_QUEUE, NORMAL_EXCHANGE, NORMAL_ROUT_KEY);
        channel.queueDeclare(DEAD_QUEUE, false, false, false, null);
        channel.queueBind(DEAD_QUEUE, DEAD_EXCHANGE, DEAD_ROUT_KEY);
        logger.info("init ok ");
    }

    /**
     * 消费者01
     * 死信交换机绑定死信队列、普通交换机绑定普通队列、普通队列绑定死信交换机。
     *
     * @throws Exception
     */
    @Test
    void deadConsumer01Test() throws Exception {
        Channel channel = RabbitmqUtil.getChannel(false);
        logger.info("等待接收消息~~~");
        DeliverCallback deliverCallback = (v1, v2) -> {
            logger.info("vonsumer 1 接收到的消息为：[{}]", new String(v2.getBody(), Charset.defaultCharset()));
        };
        channel.basicConsume(NORMAL_QUEUE, true, deliverCallback, var1 -> {
        });
        for (; ; ) {

        }
    }


    /**
     * @throws Exception
     */
    @Test
    void deadConsumer02Test() throws Exception {
        Channel channel = RabbitmqUtil.getChannel(false);
        logger.info("等待接收消息");
        DeliverCallback deliverCallback = (v1, v2) -> {
            logger.info("vonsumer 2 接收到的消息为：[{}]", new String(v2.getBody(), Charset.defaultCharset()));

        };
        //关系绑定已经有consumer1 完成
        channel.basicConsume(DEAD_QUEUE, true, deliverCallback, var1 -> {
        });
        for (; ; ) {

        }
    }

    @Test
    void deadProduceTest() throws Exception {
        Channel channel = RabbitmqUtil.getChannel(false);

        AMQP.BasicProperties build = new AMQP.BasicProperties().builder().expiration("1000").build();
        for (int i = 0; i < 11; i++) {
            String msg = "info ：" + UUID.randomUUID();
            channel.basicPublish(NORMAL_EXCHANGE, NORMAL_ROUT_KEY, build, msg.getBytes());
        }
        logger.info("send ok ~");
    }
}
