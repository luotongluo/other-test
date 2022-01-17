package com.lt.dailytest.currontest;

import org.junit.jupiter.api.Test;

import java.util.concurrent.Executor;

/**
 * @author tong.luo
 * @description ThreadTest
 * @date 2021/10/27 15:10
 */
public class ThreadTest {
    public static void main(String[] args) {
        System.out.println("main thread");
        Thread new_thread_ = new Thread(":") {
            @Override
            public void run() {
                while (true) {
                    try {
                        System.out.println("new thread ");
                    } catch (Exception e) {

                    }
                }
            }
        };
        new_thread_.setDaemon(true);
        new_thread_.start();
        System.out.println("main exit");
    }

    @Test
    public void testThreadPool() throws Exception {
        Executor test = com.lt.dailytest.utils.project.ThreadPoolUtils.getThreadPool(1, "test");
        for (int i = 0; i < Runtime.getRuntime().availableProcessors(); i++) {
            test.execute(() -> {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + ":" + System.currentTimeMillis());
            });
        }

        while (true) {
            Thread.sleep(10000);
        }
    }
}
