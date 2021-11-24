package com.lt.dailytest.other.nio;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.net.StandardSocketOptions;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @author tong.luo
 * @description SocketChannelDemo
 * @date 2021/11/23 16:44
 */
public class SocketChannelDemo {
    private static final Logger LOGGER = LoggerFactory.getLogger(SocketDemoTest.class);

    @Test
    public void socketChannelTest() throws Exception {
        //way 1 直接使用有参 open api 或者使用无参 open api，但是在无参 open 只是创建了一个
        //SocketChannel 对象，并没有进行实质的 tcp 连接
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("www.baidu.com", 80));
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        int read = socketChannel.read(byteBuffer);
        socketChannel.close();
        System.out.println("read over");

    }

    @Test
    public void socketChannelTest1() throws Exception {
        //way 1 直接使用有参 open api 或者使用无参 open api，但是在无参 open 只是创建了一个
        //SocketChannel 对象，并没有进行实质的 tcp 连接
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("www.baidu.com", 80));
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        socketChannel.configureBlocking(false);
        socketChannel.setOption(StandardSocketOptions.SO_KEEPALIVE,Boolean.TRUE);
        socketChannel.setOption(StandardSocketOptions.TCP_NODELAY,Boolean.TRUE);
        int read = socketChannel.read(byteBuffer);
        socketChannel.close();
        System.out.println("read over");

    }
}
