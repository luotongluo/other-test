package com.lt.dailytest.othertest.leecode.curr;

/**
 * @author tong.luo
 * @description Foo
 * @date 2021/5/6 16:25
 */
public class Foo {
    private volatile int flag = 1;
    public Foo() {

    }

    public void first(Runnable printFirst) throws InterruptedException {

        // printFirst.run() outputs "first". Do not change or remove this line.

        printFirst.run();
        flag = 2;
    }

    public void second(Runnable printSecond) throws InterruptedException {

        // printSecond.run() outputs "second". Do not change or remove this line.
        while (flag !=2){
        printSecond.run();
            flag =3;
        }
    }

    public void third(Runnable printThird) throws InterruptedException {

        // printThird.run() outputs "third". Do not change or remove this line.
        while (flag !=3){
            printThird.run();
        }
    }
}
