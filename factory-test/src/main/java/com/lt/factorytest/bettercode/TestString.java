package com.lt.factorytest.bettercode;

import java.util.ArrayList;

/**
 * @author tong.luo
 * @description TestString
 * @date 2021/2/9 17:28
 */
public class TestString {
    public static void main(String[] args) {
        String s = "123";
        s.substring(0, 1);

        ArrayList<String> arrayList = new ArrayList<>();
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            HugeStr hugeStr = new HugeStr();
            ImproveHugeStr improveHugeStr = new ImproveHugeStr();
//            arrayList.add(hugeStr.getSubString(1, 5));
            arrayList.add(improveHugeStr.getSubString(1, 5));

        }
        System.out.println("cost:" + (System.currentTimeMillis() - start));
    }

    static class HugeStr {
        private String str = new String(new char[100000]);

        public String getSubString(int begin, int end) {
            return str.substring(begin, end);
        }
    }

    static class ImproveHugeStr {
        private String str = new String(new char[100000]);

        public String getSubString(int begin, int end) {
            return new String(str.substring(begin, end));
        }
    }
}

