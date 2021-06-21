package com.lt.dailytest.othertest.current;

import com.lt.dailytest.exception.BusinessException;

import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @author tong.luo
 * @description handleQueueThread
 * @date 2021/3/12 14:16
 */
public class HandleQueueThread implements Runnable {
    protected String name;
    Random random = new Random();
    Queue<Object> queue;

    public HandleQueueThread() {
    }

    public HandleQueueThread(String name) {
        this.name = name;
    }

    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        try {
            for (int i = 0; i < 500; i++) {
                handleQueue(random.nextInt(1000));
                Thread.sleep(random.nextInt(50));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handleQueue(int nextInt) {
        if (queue == null) {
            throw new BusinessException("未初始化集合，请选择合适的集合进行初始化");
        }
        queue.add(nextInt);
        queue.poll();
    }

    public void initConcurrentLinkedQueue() {
        if (queue == null) {
            queue = new ConcurrentLinkedDeque<>();
        }
        for (int i = 0; i < 300; i++) {
            queue.add(i);
        }

    }

    public void initLinkedBlockingQueue() {
//        if (queue == null) {
        queue = new LinkedBlockingDeque<>();
//        }
        for (int i = 0; i < 300; i++) {
            queue.add(i);
        }

    }

    public static void main(String[] args) {
        HandleQueueThread handleQueueThread = new HandleQueueThread();
        long start = System.currentTimeMillis();
        handleQueueThread.initConcurrentLinkedQueue();
        handleQueueThread.run();
        long time1 = System.currentTimeMillis();
        handleQueueThread.initLinkedBlockingQueue();
        handleQueueThread.run();
        long endtime = System.currentTimeMillis();
        System.out.println("concurrentBlockingQUeue:" + (time1 - start));
        System.out.println("initLinkedBlockingQueue:" + (endtime - time1));
    }
}
