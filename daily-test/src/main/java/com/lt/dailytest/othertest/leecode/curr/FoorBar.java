package com.lt.dailytest.othertest.leecode.curr;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.LockSupport;

/**
 * @author tong.luo
 * @description FoorBar
 * @date 2021/5/6 16:13
 */
public class FoorBar {
    private int n;
    private final AtomicBoolean atomicBoolean = new AtomicBoolean(true);
    private final Map<String, Thread> map = new ConcurrentHashMap<>();
    private String barkey = "barkey";
    private String foobar = "foobar";

    public FoorBar(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {
        map.put(foobar, Thread.currentThread());
        for (int i = 0; i < n; i++) {
            while (!atomicBoolean.get()) {
                LockSupport.park();
            }

            // printFoo.run() outputs "foo". Do not change or remove this line.
            printFoo.run();
            atomicBoolean.compareAndSet(true, false);
            LockSupport.unpark(map.get(barkey));

        }
    }

    public void bar(Runnable printBar) throws InterruptedException {
        map.put(barkey, Thread.currentThread());
        for (int i = 0; i < n; i++) {
            while (!atomicBoolean.get()) {
                LockSupport.park();
            }
            // printBar.run() outputs "bar". Do not change or remove this line.
            printBar.run();
            atomicBoolean.compareAndSet(false, true);
            LockSupport.unpark(map.get(foobar));
        }
    }

    public static void main(String[] args) {

    }
}
