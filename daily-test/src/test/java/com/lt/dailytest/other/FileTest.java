package com.lt.dailytest.other;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;

/**
 * @author tong.luo
 * @description FileTest
 * @date 2021/9/6 10:37
 */
public class FileTest {
    @Test
    public void test1() throws Exception{
        String filePath = "H:\\";
        String oldName = "打印.doc";
        String newname = "打印-copy.doc";
        File file = new File(filePath + oldName);
        String name = file.getName();
        file.createNewFile();
        FileInputStream fileInputStream = new FileInputStream(file);
        boolean b = file.renameTo(new File(filePath + newname));
        System.out.println(b);
    }
}
