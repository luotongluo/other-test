package com.lt.dailytest.utils;

import org.apache.commons.lang3.StringUtils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author tong.luo
 * @description NIOUtiles
 * @date 2021/3/3 10:22
 */
public class NIOUtiles {
    /**
     * 使用nio复制文件
     * @param resourceFilePath
     * @param destinationFilePath
     * @param capacity
     * @throws Exception
     */
    public static void nioCopyFile(String resourceFilePath, String destinationFilePath, int capacity) throws Exception {
        FileInputStream fileInputStream = new FileInputStream(resourceFilePath);
        FileOutputStream fileOutputStream = new FileOutputStream(destinationFilePath);

        //获取文件通道
        FileChannel inputStreamChannel = fileInputStream.getChannel();
        FileChannel outputStreamChannel = fileOutputStream.getChannel();

        //读取数据进入缓存
        if(capacity < 100){
            capacity = 512;
        }
        ByteBuffer buffer = ByteBuffer.allocate(capacity);
        try {
            while (true) {
                buffer.clear();
                //读入数据
                int read = inputStreamChannel.read(buffer);
                if (read < 1) {
                    break;
                }
                buffer.flip();
                //写入文件
                outputStreamChannel.write(buffer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            inputStreamChannel.close();
            outputStreamChannel.close();
        }

    }

    public static void main(String[] args) throws Exception{
        String ss = "F:\\Download\\Postman-win64-7.36.1-Setup.exe";
        String res = "F:\\Download\\a1b2c3.iso";
        long start = System.currentTimeMillis();
        nioCopyFile(ss,res,1024 * 1000);

        System.out.println(res + "----"+ (System.currentTimeMillis() - start));
    }
}
