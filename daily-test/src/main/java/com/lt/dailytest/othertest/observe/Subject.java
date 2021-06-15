package com.lt.dailytest.othertest.observe;

import java.util.Observer;

/**
 * @author tong.luo
 * @description Subject
 * @date 2021/6/10 17:46
 */
public interface Subject {
    /**
     * 添加订阅关系
     */
    void attach(Observer observer);

    void remove(Observer observer);

    void notifyObservers(String msg);

}
