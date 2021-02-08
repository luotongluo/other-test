package com.lt.factorytest.modifi;

/**
 * @author tong.luo
 * @description ModiFact
 * 动态代理
 * @date 2021/2/8 11:02
 */
public class ModiFact {
    public static void main(String[] args) {
        IdbQuery d = null;
        long l = System.currentTimeMillis();
        d.request();
    }
}
