package com.lt.factorytest.observer;

/**
 * @author tong.luo
 * @description ISubject
 * @date 2021/2/9 10:33
 */
public interface ISubject {
    /**
     * 添加观察者
     *
     * @param ioBserver
     */
    void attach(IObserver ioBserver);

    /**
     * 删除观察者
     *
     * @param ioBserver
     */
    void detach(IObserver ioBserver);

    /**
     * 通知所有观察者
     */
    void inform();
}
