package com.lt.dailytest.othertest.current;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @author tong.luo
 * @description SelfThreadTest
 * @date 2021/6/2 10:44
 */
public class SelfThreadTest {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    //存放任务的阻塞队列
    BlockingQueue<Runnable> blockingQueue;
    //线程列表
    List<SelfThread> threadTests;
//    ExecutorService

    public SelfThreadTest(BlockingQueue<Runnable> blockingQueue, int coreSize) {
        this.blockingQueue = blockingQueue;
        this.threadTests = new ArrayList<>(coreSize);
        IntStream.rangeClosed(1, coreSize).forEach(a -> {
            SelfThread selfThread = new SelfThread("self-creat-thread" + a);
            selfThread.start();
            threadTests.add(selfThread);
        });
    }

    public void execute(Runnable tasks) throws InterruptedException {
        this.blockingQueue.put(tasks);
    }

    public static void main(String[] args) {
        SelfThreadTest selfThreadTest = new SelfThreadTest(new LinkedBlockingDeque<>(10), Runtime.getRuntime().availableProcessors());
        IntStream.rangeClosed(1, 5).forEach(a -> {
            try {
                selfThreadTest.execute(() ->{
                    System.out.println(Thread.currentThread().getName() + "");
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    class SelfThread extends Thread {
        public SelfThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(5);
                    //从任务队列中获取数据
                    Runnable task = blockingQueue.take();
                    task.run();
                } catch (Exception e) {
                    logger.error("thread -- run error", e);
                }
            }

        }
    }
}


