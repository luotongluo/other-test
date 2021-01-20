package com.lt.springcloudtest.atomic;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author tong.luo
 * @description BackCardTest
 * @date 2021/1/20 16:45
 */
public class BackCardTest {
    private static volatile BackCard backCard = new BackCard("test", "123");

    //使用atomic 保证线程安全性
    private static AtomicReference<BackCard> atomicReference = new AtomicReference<>(new BackCard("test1", "100"));

    private static int loop = 10;
    private static volatile int a = 0;

    public static void main(String[] args) {

        test3();
    }

    private static void test3() {
        for (a = 0; a < loop; a++) {
            new Thread(() -> {
                while (true) {
                    //获取数据
                    BackCard card = atomicReference.get();
                    String mon = new BigDecimal(card.getMoney()).add(new BigDecimal("100")).toString();
                    BackCard newcard = new BackCard(card.getAccountName(), mon);

                    //使用cas乐观锁进行非阻塞更新
                    if (atomicReference.compareAndSet(card, newcard)) {
                        System.out.println(System.currentTimeMillis() + "=====" + newcard);
                    }
                    try {
                        TimeUnit.MICROSECONDS.sleep(1);
//                        Thread.sleep(1L);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }

    private static void test1() {
        for (int a = 0; a < 100; a++) {
            new Thread(() -> {
                final BackCard card = backCard;
                String money = new BigDecimal(card.getMoney()).add(new BigDecimal("100")).toString();
                BackCard newcard = new BackCard(card.getAccountName(), money);
                System.out.println(System.currentTimeMillis() + "^&&&^" + newcard.toString());
                backCard = newcard;
                try {
                    TimeUnit.MICROSECONDS.sleep(1000L);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

    private static void test2() {
        for (int a = 0; a < 100; a++) {
            new Thread(() -> {
                synchronized (BackCardTest.class) {
                    final BackCard card = backCard;
                    String money = new BigDecimal(card.getMoney()).add(new BigDecimal("100")).toString();
                    BackCard newcard = new BackCard(card.getAccountName(), money);
                    System.out.println(System.currentTimeMillis() + "^&&&^" + newcard.toString());
                    backCard = newcard;
                    try {
                        TimeUnit.MICROSECONDS.sleep(1000L);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }).start();
        }
    }
}
