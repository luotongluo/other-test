package com.lt.factorytest.single;

import com.alibaba.fastjson.JSON;

/**
 * @author tong.luo
 * @description SingletonPatternDemo
 * @date 2021/2/7 16:02
 */
public class SingletonPatternDemo {
    public static void main(String[] args) {
        SingleObject instrence = SingleObject.getInstrence();
        System.out.println(JSON.toJSONString(instrence));
        instrence.showMsg();
    }
}

/**
 * @author tong.luo
 * @description SingleObject
 * @date 2021/2/7 16:02
 */
class SingleObject {
    private static SingleObject singleObject = null;

    /**
     * 私有化构造方法，这样类不会被初始化
     */
    private SingleObject() {
    }

    /**
     * 获取唯一可用的对象
     *
     * @return
     */
    public static SingleObject getInstrence() {
        if (singleObject == null) {
            synchronized (SingleObject.class) {
                if (null == singleObject) {
                    return new SingleObject();
                }
            }
        }
        return singleObject;
    }

    /**
     * showMsg
     */
    public void showMsg() {
        System.out.println("showMsg");
    }
}
