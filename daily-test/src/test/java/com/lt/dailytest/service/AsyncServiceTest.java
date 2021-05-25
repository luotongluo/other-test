package com.lt.dailytest.service;

import com.alibaba.fastjson.JSON;
import com.lt.dailytest.config.ExecutorConfig;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author tong.luo
 * @description AsyncServiceTest
 * @date 2021/5/19 20:38
 */
@SpringBootTest
public class AsyncServiceTest {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private AsyncService asyncService;

    @Test
    void executeAsync() throws Exception{
        logger.info("init ^");
        CountDownLatch countDownLatch = new CountDownLatch(10);
        for (int i = 0; i < 20; i++) {
            this.asyncService.executeAsync();
        }
        Thread.State state = Thread.currentThread().getState();
        logger.info("thread--stat[{}]",JSON.toJSONString(state));
        //Thread.sleep(1000);
    }
    @Test
    void executeAsyncSam() throws Exception{
        logger.info("init ^");
        final Semaphore semaphore = new Semaphore(10);

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(ExecutorConfig.corePoolSize, ExecutorConfig.maxPoolSize,
                0L, TimeUnit.SECONDS, new LinkedBlockingDeque(), new ThreadPoolExecutor.CallerRunsPolicy());
        for (int i = 0; i < 20; i++) {

            threadPoolExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    asyncService.executeAsync();
                }
            });

            semaphore.acquire();
            semaphore.release();
        }
        threadPoolExecutor.shutdown();
        while(true){
            if(threadPoolExecutor.isTerminated()){
                System.out.println("所有的子线程都结束了！");
                break;
            }

        Thread.State state = Thread.currentThread().getState();
        logger.info("thread--stat[{}]",JSON.toJSONString(state));
        //Thread.sleep(1000);
    }}
}