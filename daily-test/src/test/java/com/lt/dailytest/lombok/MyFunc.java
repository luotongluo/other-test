package com.lt.dailytest.lombok;

/**
 * @author tong.luo
 * @description MyFunc
 * @date 2021/8/12 17:13
 */
@FunctionalInterface
public interface MyFunc<T, R> {
    R getValue(T t1, T t2);

}
