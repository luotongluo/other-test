package com.lt.dailytest.other.nio;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.util.Date;

/**
 * @author tong.luo
 * @description FileChannelDemo
 * @date 2021/11/18 19:57
 */
public class FileChannelDemo {
    private static final Logger LOGGER = LoggerFactory.getLogger(FileChannelDemo.class);

    @Test
    public void testFileChannel() throws Exception {
        // 创建filechannel  创建buffer 读取数据到buffer中
        RandomAccessFile randomAccessFile = new RandomAccessFile("D:\\test.txt", "r");
        FileChannel channel = randomAccessFile.getChannel();

        ByteBuffer allocate = ByteBuffer.allocate(1024);
        int read = channel.read(allocate);
        if (read != -1) {
            //表示存在数据
            LOGGER.info("duqu daole :{}", read);
            while (allocate.hasRemaining()) {
                LOGGER.info("get data :{}", (char) allocate.get());
            }
            allocate.clear();
            read = channel.read(allocate);
        }
        randomAccessFile.close();
        LOGGER.info("end ^^^^");
    }

    @Test
    public void fileDemoWrite() throws Exception {
        // 创建filechannel  创建buffer 读取数据到buffer中
        RandomAccessFile randomAccessFile = new RandomAccessFile("D:\\testfile\\test222.txt", "rw");
        FileChannel channel = randomAccessFile.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(1024);
        //int read = channel.read(buffer);

        String writconteng = "buffer.put(writconteng.getBytes(StandardCharsets.UTF_8));" + new Date().toString();
        buffer.clear();
        LOGGER.info("channel size:{}", channel.size());
        //将指定文件后面的长度删除
        channel.truncate(channel.size() - 10);
        buffer.put(writconteng.getBytes(StandardCharsets.UTF_8));
        buffer.flip();
        while (buffer.hasRemaining()) {
            channel.write(buffer);
        }
        channel.close();
    }

    /**
     * 根据特定位置进行文件的读和写
     *
     * @throws Exception
     */
    @Test
    public void fileDemoPosition() throws Exception {
        RandomAccessFile randomAccessFile = new RandomAccessFile("D:\\test.txt", "rw");
    }

    @Test
    public void fileDemoChannelDataTrans() throws Exception {
        //将randomAccessFile  --》 到randomAccessFile 文件中
        RandomAccessFile randomAccessFile = new RandomAccessFile("D:\\test.txt", "rw");
        FileChannel fromchannel = randomAccessFile.getChannel();

        RandomAccessFile randomAccessFileB = new RandomAccessFile("D:\\test1.txt", "rw");
        FileChannel tochannel = randomAccessFileB.getChannel();
        LOGGER.info("channel size:{}", tochannel.size());
//        tochannel.transferFrom(fromchannel,0,fromchannel.size());
        fromchannel.transferTo(0, tochannel.size(), tochannel);
        fromchannel.close();
        tochannel.close();
        System.out.println("over ");
    }
}
