package com.lt.dailytest.other;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author tong.luo
 * @description MapTest
 * @date 2021/5/6 10:09
 */
public class MapTest {
    public static void main(String[] args) {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("test", "test");
        print(hashMap);
        hashMap.put("test1", "test1");
        print(hashMap);
        hashMap.put("test2", "test2");
        print(hashMap);
        hashMap.put("test3", "test3");
        print(hashMap);
    }

    private static void print(HashMap<String, Object> hashMap) {
        try {
            Class<?> aClass = hashMap.getClass();
//            Method[] declaredMethods = aClass.getDeclaredMethods();
//            for (Method declaredMethod : declaredMethods) {
//                declaredMethod.setAccessible(true);
//                System.out.println("capa:" + declaredMethod.invoke(hashMap) + "\t size:" + hashMap.size());
//            }
            Method capacity = aClass.getDeclaredMethod("capacity");
            capacity.setAccessible(true);
            System.out.println("capacity : " + capacity.invoke(hashMap) + "    size : " + hashMap.size());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
