package com.lt.dailytest.currontest;

import java.util.concurrent.TimeUnit;

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
}
