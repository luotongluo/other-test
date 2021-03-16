package com.lt.dubbospringtest.config;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.CountDownLatch;

/**
 * @author tong.luo
 * @description ZookeeperConfig
 * @date 2021/2/26 10:06
 */
@Configuration
public class ZookeeperConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(ZookeeperConfig.class);

    @Value("${zookeeper.address}")
    private String zkAddress;

    @Value("${zookeeper.timeout}")
    private String timeout;

    @Bean("zkClient")
    public ZooKeeper zkClient() {
        ZooKeeper zooKeeper = null;
        try {
            CountDownLatch countDownLatch = new CountDownLatch(1);

            zooKeeper = new ZooKeeper(zkAddress, Integer.valueOf(timeout), new Watcher() {
                @Override
                public void process(WatchedEvent event) {
                    if (Event.KeeperState.SyncConnected == event.getState()) {
                        //如果收到了服务端的响应事件，连接成功
                        countDownLatch.countDown();
                    }
                }
            });
            countDownLatch.await();
            LOGGER.info("[初始化zookeeper init ……] = {}", zooKeeper.getState());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return zooKeeper;
    }
}
