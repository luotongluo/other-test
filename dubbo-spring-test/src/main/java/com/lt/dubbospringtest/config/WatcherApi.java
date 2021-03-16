package com.lt.dubbospringtest.config;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author tong.luo
 * @description WatcherApi
 * @date 2021/2/26 10:20
 */
public class WatcherApi implements Watcher {
    private static final Logger LOGGER = LoggerFactory.getLogger(WatcherApi.class);

    @Override
    public void process(WatchedEvent event) {
        LOGGER.info("【Watcher监听事件】={}", event.getState());
        LOGGER.info("【监听路径为】={}", event.getPath());
        LOGGER.info("【监听的类型为】={}", event.getType()); //  三种监听类型： 创建，删除，更新
    }
}
