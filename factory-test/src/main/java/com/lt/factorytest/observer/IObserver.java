package com.lt.factorytest.observer;

import javafx.event.Event;

/**
 * @author tong.luo
 * @description IOBserver
 * @date 2021/2/9 10:34
 */
public interface IObserver {
    //更新观察者
    void update(Event event);
}
