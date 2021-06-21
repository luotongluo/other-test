package com.lt.dailytest.othertest.observe;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

/**
 * @author tong.luo
 * @description connSubject
 * @date 2021/6/10 17:48
 */
public class ConnSubject implements Subject {
    private List<Observer> observerList = new ArrayList<>();

    @Override
    public void attach(Observer observer) {
        observerList.add(observer);
    }

    @Override
    public void remove(Observer observer) {
        observerList.remove(observer);
    }

    @Override
    public void notifyObservers(String msg) {

    }
}
