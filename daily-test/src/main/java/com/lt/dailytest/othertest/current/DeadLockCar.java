package com.lt.dailytest.othertest.current;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author tong.luo
 * @description DeadLockCar
 * @date 2021/3/15 15:55
 */
public class DeadLockCar extends Thread {
    protected Object myDirect;
    static ReentrantLock south = new ReentrantLock();
    static ReentrantLock north = new ReentrantLock();
    static ReentrantLock west = new ReentrantLock();
    static ReentrantLock east = new ReentrantLock();

    public DeadLockCar(Object myDirect) {
        this.myDirect = myDirect;
        if (myDirect == south) {
            this.setName("south");
        }
        if (myDirect == north) {
            this.setName("north");
        }
        if (myDirect == east) {
            this.setName("east");
        }
        if (myDirect == west) {
            this.setName("west");
        }
    }

    @Override
    public void run() {
        //占据想向东的路
        if (myDirect == north) {
            try {
                east.lockInterruptibly();
                try {
                    Thread.sleep(500);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                north.lockInterruptibly();
                System.out.println("north is pass !");
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("car to north is killer");
            } finally {
                if (east.isHeldByCurrentThread()) {
                    east.unlock();
                }
                if (north.isHeldByCurrentThread()) {
                    north.unlock();
                }
            }
        }
        if (myDirect == east) {
            try {
                //占据想向东的路
                south.lockInterruptibly();
                try {
                    Thread.sleep(500);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                east.lockInterruptibly();
                System.out.println("east is pass !");
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("car to east is kill es");
            } finally {
                if (south.isHeldByCurrentThread()) {
                    south.unlock();
                }
                if (east.isHeldByCurrentThread()) {
                    east.unlock();
                }
            }
        }
        if (myDirect == south) {
            try {
                //占据想向东的路
                west.lockInterruptibly();
                try {
                    Thread.sleep(500);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                south.lockInterruptibly();
                System.out.println("south is pass !");
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("car to south is kill es");
            } finally {
                if (west.isHeldByCurrentThread()) {
                    west.unlock();
                }
                if (south.isHeldByCurrentThread()) {
                    south.unlock();
                }
            }
        }
        if (myDirect == west) {
            try {
                //占据想向东的路
                north.lockInterruptibly();
                try {
                    Thread.sleep(500);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                west.lockInterruptibly();
                System.out.println("west is pass !");
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("car to west is kill es");
            } finally {
                if (west.isHeldByCurrentThread()) {
                    west.unlock();
                }
                if (north.isHeldByCurrentThread()) {
                    north.unlock();
                }
            }
        }


    }

    public static void main(String[] args){
        DeadLockCar southernCar = new DeadLockCar(south);
        DeadLockCar northCar = new DeadLockCar(north);
        DeadLockCar eastCar = new DeadLockCar(east);
        DeadLockCar westCar = new DeadLockCar(west);
        northCar.start();
        westCar.start();
        southernCar.start();
        eastCar.start();
//        northCar.interrupt();
        long start = System.currentTimeMillis();
        while (!northCar.isInterrupted()) {
            northCar.interrupt();
        }
        System.out.println("cost:" + (System.currentTimeMillis() - start));

    }
}
