package com.lt.dailytest.othertest.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author tong.luo
 * @description ReentrantLockTest
 * @date 2021/3/12 17:33
 */
public class ReentrantLockTest {

    ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) throws Exception {
        ReentrantLockTest reentrantLockTest = new ReentrantLockTest();
        Thread fiest = new Thread(reentrantLockTest.createTask(), "first-thead");
        Thread second = new Thread(reentrantLockTest.createTask(), "second-thead");
        fiest.start();
        second.start();
        Thread.sleep(600);
        second.interrupt();
    }

    private Runnable createTask() {

        return new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        if (lock.tryLock(500, TimeUnit.MILLISECONDS)) {
                            try {
                                System.out.println("lock : " + Thread.currentThread().getName());
                                Thread.sleep(1000);
                            } finally {
                                lock.unlock();
                                System.out.println("unlock : " + Thread.currentThread().getName());
                            }
                            break;
                        } else {
                            System.out.println("unable to lock : " + Thread.currentThread().getName());

                        }

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        System.out.println("InterruptedException:" + Thread.currentThread().getName());
                    }
                }
            }
        };
    }
}
