package com.lt.factorytest.observer;

import javafx.event.Event;

/**
 * @author tong.luo
 * @description ConcreteObserverImpl
 * @date 2021/2/9 10:44
 */
public class ConcreteObserverImpl implements IObserver {
    @Override
    public void update(Event event) {
        System.out.println("observer receives informations!");
    }
}
