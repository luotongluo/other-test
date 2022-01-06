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

/**
 * @author admin
 * @description tong.luo
 * 死信消息的来源  消息ttl过期
 * 队列达到了最大长度，无法添加到队列中
 * 消息被拒绝 requeue =false
 * 文档参考地址：https://blog.csdn.net/weixin_56727438/article/details/122081742?utm_medium=distribute.pc_category.none-task-blog-hot-21.nonecase&depth_1-utm_source=distribute.pc_category.none-task-blog-hot-21.nonecase
 * @date 2021/12/30 15:18
 */
@SpringBootTest
class MqTest {
    Logger logger = LoggerFactory.getLogger(MqTest.class);
    public static final String DEAD_EXCHANGE = "dead_exchange";
    public static final String DEAD_QUEUE = "dead_queue";
    public static final String DEAD_ROUT_KEY = "dead_route";

    public static final String NORMAL_EXCHANGE = "normal_exchange";
    public static final String NORMAL_QUEUE = "normal_queue";
    public static final String NORMAL_ROUT_KEY = "normal_route";

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
        //过期时间的设置单位为毫秒 设置的过期时间为1秒钟
        AMQP.BasicProperties build = new AMQP.BasicProperties().builder().expiration("1000").build();
        for (int i = 0; i < 11; i++) {
            String msg = "info " + i;
            channel.basicPublish(NORMAL_EXCHANGE, NORMAL_ROUT_KEY, build, msg.getBytes());
        }
    }
}
