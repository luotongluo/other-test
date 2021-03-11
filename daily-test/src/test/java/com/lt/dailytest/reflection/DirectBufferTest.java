package com.lt.dailytest.reflection;

import java.lang.reflect.Field;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author tong.luo
 * @description DirectBufferTest
 * @date 2021/3/10 13:47
 */
public class DirectBufferTest {
    public static void main(String[] args) throws Exception{
        Class<?> forName = Class.forName("java.nio.Bits");

        Field[] declaredFields = forName.getDeclaredFields();
        Field maxMemory = forName.getDeclaredField("maxMemory");
        maxMemory.setAccessible(true);
        Field reservedMemory = forName.getDeclaredField("reservedMemory");
        reservedMemory.setAccessible(true);
        synchronized (forName){
            Long longZVal = (Long)maxMemory.get(null);
            AtomicLong reVal =   (AtomicLong)reservedMemory.get(null);
            System.out.println("maxMemory :" + longZVal);
            System.out.println("reservedMemory :" + reVal.get());

        }
    }
}
