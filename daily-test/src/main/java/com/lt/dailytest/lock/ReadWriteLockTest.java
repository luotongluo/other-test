package com.lt.dailytest.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author tong.luo
 * @description ReadWriteLockTest
 * 读写锁在这段代码中的效率比重入锁搞一个数量级左右，同事，
 * 读写锁的高性能的原因是读的绝对并行性，如果读操作战友绝对多数，那么读操作本身消耗的时间越多，读写锁与重入锁
 * 的性能差距也就越大
 * 在读多写少的场合，使用读写锁可以分离读操作和写操作，是所有的读操作键真正并行。
 * 因此，能有有效提高系统的并发性能
 * @date 2021/3/12 17:52
 */
public class ReadWriteLockTest {
    private static Lock lock = new ReentrantLock();
    private static ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
    private static Lock readLock = reentrantReadWriteLock.readLock();
    private static Lock writeLock = reentrantReadWriteLock.writeLock();
    private int value;

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        ReadWriteLockTest readWriteLockTest = new ReadWriteLockTest();
        int loop = 5000;
        for (int i = 0; i < loop; i++) {
            if (i % 3 == 0) {
                readWriteLockTest.handleWrite(i);
            }
            Object o = readWriteLockTest.handleRead();
//            System.out.println(o);
        }
        long time1 = System.currentTimeMillis();
        for (int i = 0; i < loop; i++) {
            if (i % 2 == 0) {
                readWriteLockTest.handleWrite1(i);
            }
            Object o = readWriteLockTest.handleRead1();
//            System.out.println(o);
        }
        System.out.println("cost:" + (time1 - start));
        System.out.println("cost1:" + (System.currentTimeMillis() - time1));

    }

    public Object handleRead() {
        try {
            lock.lock();
            Thread.sleep(1);
            return value;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();

        }
        return null;
    }

    public void handleWrite(int index) {
        try {
            lock.lock();
            Thread.sleep(1);
            value = index;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public Object handleRead1() {
        try {
            readLock.lock();
            Thread.sleep(1);
            return value;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readLock.unlock();

        }
        return null;
    }

    public void handleWrite1(int index) {
        try {
            writeLock.lock();
            Thread.sleep(1);
            value = index;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            writeLock.unlock();
        }
    }
}
