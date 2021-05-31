package com.lt.dailytest.othertest.current;

/**
 * @author tong.luo
 * @description PlusWorker
 * @date 2021/3/11 16:05
 */
public class PlusWorker extends Woker {
    /**
     * 求立方和
     *
     * @param obj
     * @return
     */
    public Object handle(Object obj) {
        Integer i = (Integer) obj;
        return i * i * i;

    }
}
