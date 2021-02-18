package com.lt.factorytest.string;

import java.util.StringTokenizer;

/**
 * @author tong.luo
 * @description SpliteTest
 * @date 2021/2/10 13:46
 */
public class SpliteTest {
    public static void main(String[] args) {
        long timeMillis = System.currentTimeMillis();
        String orgstr = null;
        StringBuffer stringBuffer = new StringBuffer();
        String strspl = ";";
        for (int i = 0; i < 1000; i++) {
            stringBuffer.append(i);
            stringBuffer.append(strspl);
        }

        orgstr = stringBuffer.toString();

//        for (int i = 0; i < 10000; i++) {
//            orgstr.split(strspl);
//        }
        orgstr.charAt('a');
        //第一个参数为要分隔的字符， 第二个是分割的字符的参数
        StringTokenizer stringTokenizer = new StringTokenizer(orgstr, strspl);
        for (int i = 0; i < 10000; i++) {
            while (stringTokenizer.hasMoreTokens()){
                stringTokenizer.nextToken();
            }
            stringTokenizer = new StringTokenizer(orgstr, strspl);
        }
        System.out.println("cose:" + (System.currentTimeMillis() - timeMillis));
    }
}
