package com.lt.dailytest.other.nio;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.channels.FileChannel;

/**
 * @author tong.luo
 * @description BufferDemo
 * @date 2021/11/22 19:09
 */
public class BufferDemo {
    private static final Logger LOGGER = LoggerFactory.getLogger(BufferDemo.class);

    @Test
    public void bufferTest() throws Exception {
        RandomAccessFile accessFile = new RandomAccessFile("", "rw");
        FileChannel channel = accessFile.getChannel();
        ByteBuffer allocate = ByteBuffer.allocate(1024);

    }

    @Test
    public void intbufferTest() throws Exception {
        IntBuffer allocate = IntBuffer.allocate(8);
        for (int i = 0; i < allocate.capacity(); i++) {
            allocate.put(2 * (i + 1));
        }
        //将写模式切换到读模式
        allocate.flip();
        while (allocate.hasRemaining()) {
            int i = allocate.get();
            LOGGER.info("i:" + i);
        }
    }
}
