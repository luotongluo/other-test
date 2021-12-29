package com.lt.connmq.mqconfig;

import com.lt.springcloudcommon.mqfile.MqConts;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author admin
 * @description DemoMqConfig
 * @date 2021/12/29 16:15
 */
@Configuration
public class DemoMqConfig {
    @Bean
    public Queue directQueue1() {
        return new Queue(MqConts.testQueue);
    }

    @Bean
    public DirectExchange directExchangeDemo() {
        // 三个构造参数：name durable autoDelete
        return new DirectExchange(MqConts.testExchange, false, false);
    }

    @Bean
    public Binding directBinding1() {
        return BindingBuilder.bind(directQueue1()).to(directExchangeDemo()).with(MqConts.testRoutKey);
    }
}
