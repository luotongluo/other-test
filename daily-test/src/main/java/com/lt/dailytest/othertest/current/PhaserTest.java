package com.lt.dailytest.othertest.current;

import sun.misc.Unsafe;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Phaser;
import java.util.concurrent.SynchronousQueue;

/**
 * @author tong.luo
 * @description PhaserTest
 * @date 2021/11/3 20:27
 */
public class PhaserTest {
    public PhaserTest() {
        Phaser phaser = new Phaser(10);
        phaser.awaitAdvance(phaser.getPhase());
        phaser.arrive();
        BlockingQueue blockingQueue = new SynchronousQueue<Runnable>();
        BlockingQueue blockingQueue1 = new ArrayBlockingQueue<>(12);
    }
}
