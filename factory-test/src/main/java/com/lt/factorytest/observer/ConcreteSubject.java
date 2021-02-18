package com.lt.factorytest.observer;

import javafx.event.Event;

import java.util.Vector;

/**
 * @author tong.luo
 * @description ConcreteSubject
 * @date 2021/2/9 10:38
 */
public class ConcreteSubject implements ISubject {
    private Vector<IObserver> observerVector = new Vector<>();

    /**
     * 添加观察者
     *
     * @param ioBserver
     */
    @Override
    public void attach(IObserver ioBserver) {
        observerVector.addElement(ioBserver);
    }

    /**
     * 删除观察者
     *
     * @param ioBserver
     */
    @Override
    public void detach(IObserver ioBserver) {
        observerVector.remove(ioBserver);
    }

    /**
     * 通知所有观察者
     */
    @Override
    public void inform() {
        Event event = null;
        for (IObserver iObserver : observerVector) {
            //在这里通知观察者
            iObserver.update(event);
        }
    }
}
