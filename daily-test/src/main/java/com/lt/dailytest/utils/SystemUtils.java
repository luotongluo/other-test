package com.lt.dailytest.utils;

/**
 * @author tong.luo
 * @description SystemUtils
 * @date 2021/3/17 16:14
 */
public class SystemUtils {
    public static void getSysEnum(){
        System.setProperty("asdfsdf","123123");
        String property = System.getProperty("os.name");
        System.out.println(property);
        String fileSeparator = System.getProperty("file.separator");
        System.out.println(fileSeparator);
        String webPath = System.getProperty("webroot.path");
        System.out.println(webPath);

        String lineSeparator = System.getProperty("line.separator");
        System.out.println(lineSeparator);
        String asdfsdf = System.getProperty("asdfsdf");
        System.out.println(asdfsdf);
        System.out.println(System.getProperty("java.version"));
        System.out.println(System.getProperty("java.vendor"));
        System.out.println(System.getProperty("java.vendor.url"));
        System.out.println(System.getProperty("java.home"));
        System.out.println(System.getProperty("java.vm.specification.version"));
        System.out.println(System.getProperty("java.vm.specification.vendor"));
        System.out.println(System.getProperty("java.vm.specification.name"));
        System.out.println(System.getProperty("java.vm.version"));
        System.out.println(System.getProperty("java.vm.vendor"));
        System.out.println(System.getProperty("java.vm.name"));
        System.out.println(System.getProperty("java.specification.version"));
        System.out.println(System.getProperty("java.specification.vendor"));
        System.out.println(System.getProperty("java.specification.name"));
        System.out.println(System.getProperty("java.class.version"));
        System.out.println(System.getProperty("java.class.path"));
        System.out.println(System.getProperty("java.library.path"));
        System.out.println(System.getProperty("java.io.tmpdir"));
        System.out.println(System.getProperty("java.compiler"));
        System.out.println(System.getProperty("java.ext.dirs"));
        System.out.println(System.getProperty("os.arch"));
        System.out.println(System.getProperty("os.version"));
        System.out.println(System.getProperty("file.separator"));
        System.out.println(System.getProperty("path.separator"));
        System.out.println(System.getProperty("line.separator"));
        System.out.println(System.getProperty("user.name"));
        System.out.println(System.getProperty("user.home"));
        System.out.println(System.getProperty("user.dir"));

    }

    public static void main(String[] args) {
        getSysEnum();
    }
}
