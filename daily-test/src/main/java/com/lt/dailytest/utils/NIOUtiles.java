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
     *
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
        if (capacity < 100) {
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

    /**
     * nio 中 buffer中的limit capacity 与 position 之间的关系
     */
    public static void buffertest() {
        //15个自己大小的缓冲区
        ByteBuffer allocate = ByteBuffer.allocate(15);
        System.out.println("limit = " + allocate.limit() + " capacity=" + allocate.capacity() + " position=" + allocate.position());
        for (int i = 0; i < 15; i++) {
            //存入10个字节
            allocate.put((byte) i);
        }
        System.out.println("limit = " + allocate.limit() + " capacity=" + allocate.capacity() + " position=" + allocate.position());
        //重置position  同事将limit设置到position位置
        allocate.flip();
        System.out.println("limit = " + allocate.limit() + " capacity=" + allocate.capacity() + " position=" + allocate.position());

        for (int i = 0; i < 15; i++) {
            System.out.println(allocate.get());
        }
        System.out.println();
        System.out.println("limit = " + allocate.limit() + " capacity=" + allocate.capacity() + " position=" + allocate.position());
        allocate.flip();
        System.out.println("limit = " + allocate.limit() + " capacity=" + allocate.capacity() + " position=" + allocate.position());

    }

    /**
     * mark 与reset的理解
     * 方法运行输出：
     * 01234mark at i 4
     * 567891011121314
     * reset to mark
     * 567891011121314
     */
    public static void mardAndReset() {
        int cap = 15;
        ByteBuffer allocate = ByteBuffer.allocate(cap);
        for (int i = 0; i < cap; i++) {
            allocate.put((byte) i);
        }
        //准备读
        allocate.flip();
        for (int i = 0; i < allocate.limit(); i++) {
            System.out.print(allocate.get());
            if (i == 4) {
                allocate.mark();
                System.out.println("mark at i " + i);
            }

        }
        //回到mark位置，并处理后续数据
        allocate.reset();
        System.out.println("\nreset to mark");
        while (allocate.hasRemaining()) {
            //后序所有的数据都去除出来21
            System.out.print(allocate.get());

        }
    }

    /**
     * testduplicate 方法测试
     * 方法运行结果如下
     */
    public static void testduplicate() {
        ByteBuffer allocate = ByteBuffer.allocate(15);
        for (int i = 0; i < 15; i++) {
            allocate.put((byte) i);
        }
        /*
        复制当前的缓冲区 ，新生的缓冲区与元缓冲区共享相同的数据结构
        并且对任意一方的改动都是相互可见的，但两者又维护了格子的position，limit和mark
        大大增加了程序的灵活性，为多方同事处理数据提供了可能
         */
        ByteBuffer duplicate = allocate.duplicate();
        System.out.println("After allocate .duplicate");
        System.out.println(allocate);
        System.out.println(duplicate);
        duplicate.flip();
        System.out.println("After duplicate . flip");
        System.out.println(allocate);
        System.out.println(duplicate);
        duplicate.put((byte) 100);
        System.out.println("After duplicate put 100");
        System.out.println("allocate.get(0)" + allocate.get(0));
        System.out.println("duplicate.get(0)" + duplicate.get(0));
    }

    /**
     * 缓冲区中测试
     * 遍历父级缓冲的时候才能查看到缓冲中的内容发生了变化
     */
    public static void sliceTest() {
        ByteBuffer allocate = ByteBuffer.allocate(15);
        for (int i = 0; i < 10; i++) {
            allocate.put((byte)i);
        }
        allocate.position(2);
        allocate.limit(6);
        ByteBuffer slice = allocate.slice();
        for (int i = 0; i < slice.capacity(); i++) {
            byte b = slice.get(i);
            b *= 10;
            slice.put(i,b);
        }
        //重置父缓冲区，并查看父缓冲区中的数据
        allocate.position(0);
        allocate.limit(allocate.capacity());
        while (allocate.hasRemaining()){
            System.out.print(allocate.get()+ "   ");
        }
    }

    public  static  void testReadOnly(){
        ByteBuffer allocate = ByteBuffer.allocate(15);
        for (int i = 0; i < 10; i++) {
            allocate.put((byte)i);
        }
        //创建只读缓冲区
        ByteBuffer byteBuffer = allocate.asReadOnlyBuffer();
        byteBuffer.flip();
        while (byteBuffer.hasRemaining()){
            System.out.print(byteBuffer.get() + " ");
        }
        System.out.println();
        allocate.put(2,(byte) 20);
        byteBuffer.flip();
        while (byteBuffer.hasRemaining()){
            System.out.print(byteBuffer.get() + " ");
        }
    }

    public static void main(String[] args) throws Exception {
//        String ss = "F:\\Download\\Postman-win64-7.36.1-Setup.exe";
//        String res = "F:\\Download\\a1b2c3.iso";
//        long start = System.currentTimeMillis();
//        nioCopyFile(ss, res, 1024 * 1000);
//
//        System.out.println(res + "----" + (System.currentTimeMillis() - start));
//        buffertest();
//        mardAndReset();
//        testduplicate();
//        sliceTest();
        testReadOnly();
    }
}
