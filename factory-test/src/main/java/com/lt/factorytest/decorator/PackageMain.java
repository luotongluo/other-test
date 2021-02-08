package com.lt.factorytest.decorator;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FilterOutputStream;

/**
 * @author tong.luo
 * @description PackageMain
 * @date 2021/2/8 17:14
 */
public class PackageMain {
    public static void main(String[] args) {
        /*
         *作为核心组件的packetbodycreator 最先被构造
         * 其次是packeHtmlheader
         * 最后才是packethttpheadercreator
         *
         * outputstream和inputstream使用的是装饰着模式
         */
        PacketHttpHeaderCreator packetHttpHeaderCreator = new PacketHttpHeaderCreator(new PacketHtmlHeaderCreator(new PacketBodyCreator()));
//        BufferedOutputStream
        System.out.println(packetHttpHeaderCreator.handlerContent());
    }
}
