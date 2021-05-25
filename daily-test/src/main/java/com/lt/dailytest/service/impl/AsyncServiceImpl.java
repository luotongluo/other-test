package com.lt.dailytest.service.impl;

import com.lt.dailytest.service.AsyncService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author tong.luo
 * @description AsyncServiceImpl
 * @date 2021/5/18 21:18
 */
@Service
public class AsyncServiceImpl implements AsyncService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 执行异步任务
     * 可以根据需求，自己加参数拟定，我这里就做个测试演示
     */
    @Override
    @Async("getExecutor")
    public void executeAsync() {
        logger.info("start executeAsync");
        String pre = Thread.currentThread().getName() + " ^ " + Thread.currentThread().getId();
        System.out.println(pre + " ^异步线程要做的事情");
//        try {
//            Thread.sleep(500);
//        } catch (InterruptedException e) {
//            logger.error("sleep error", e);
//        }
        try {
            TimeUnit.SECONDS.sleep(100);
        } catch (InterruptedException e) {
            logger.error("sleep error", e);
        }
        System.out.println(pre + " ^可以在这里执行批量插入等耗时的事情");

        logger.info("end executeAsync");
    }
}
