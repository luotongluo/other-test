package com.lt.factorytest.observer;

/**
 * @author tong.luo
 * @description ObserveMain
 * <p>
 * 参考中具体实现的观察模式可以参考  jbutton 创建的时候
 * @date 2021/2/9 13:50
 */
public class ObserveMain {
    public static void main(String[] args) {
        ConcreteSubject concreteSubject = new ConcreteSubject();
        concreteSubject.attach(new ConcreteObserverImpl());
        concreteSubject.detach(new ConcreteObserverImpl());
        concreteSubject.inform();
    }
}
