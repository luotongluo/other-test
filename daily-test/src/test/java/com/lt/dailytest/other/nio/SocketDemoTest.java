package com.lt.dailytest.other.nio;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

/**
 * @author tong.luo
 * @description SocketDemoTest
 * @date 2021/11/18 20:24
 */
public class SocketDemoTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(SocketDemoTest.class);

    @Test
    public void serverSocketTest() throws Exception {
        int port = 1001;
        ByteBuffer wrap = ByteBuffer.wrap("SocketDemoTest".getBytes(StandardCharsets.UTF_8));

        ServerSocketChannel open = ServerSocketChannel.open();
        //绑定
        open.socket().bind(new InetSocketAddress(port));
        //设置非阻塞模式
        open.configureBlocking(false);

        while (true) {
            LOGGER.info("waiting connection~~~");
            SocketChannel accept = open.accept();
            if (accept == null) {
                LOGGER.info("null");
                Thread.sleep(2000);
            } else {
                LOGGER.info("incoming connection from :{}", accept.getRemoteAddress());
                //指针0
                wrap.rewind();
                accept.write(wrap);
                accept.close();
            }
        }

    }

    @Test
    public void datagramChannelSend() throws Exception {
        DatagramChannel datagramChannel = DatagramChannel.open();
        InetSocketAddress socketAddress = new InetSocketAddress("localhost", 2001);
        while (true) {
            datagramChannel.send(ByteBuffer.wrap("datagramChannelSend".getBytes(StandardCharsets.UTF_8)), socketAddress);
            LOGGER.info("send ok ~");
            TimeUnit.SECONDS.sleep(2);
        }
    }

    @Test
    public void datagramChannelReciveConn() throws Exception {
        DatagramChannel datagramChannel = DatagramChannel.open();
        datagramChannel.bind(new InetSocketAddress("localhost",3001));

        datagramChannel.connect(new InetSocketAddress("localhost",3001));

        ByteBuffer byteBuffer = ByteBuffer.wrap("datagramChannelReciveConn".getBytes(StandardCharsets.UTF_8));
        datagramChannel.write(byteBuffer);

        ByteBuffer buffer = ByteBuffer.allocate(1024);
        while (true) {
            buffer.clear();
            datagramChannel.receive(buffer);
            buffer.flip();
            LOGGER.info("address ,content:{}", Charset.forName(StandardCharsets.UTF_8.toString()).decode(buffer));

        }
    }

    @Test
    public void datagramChannelRecive() throws Exception {
        DatagramChannel datagramChannel = DatagramChannel.open();
        InetSocketAddress socketAddress = new InetSocketAddress("localhost", 2001);
        //绑定地址
        datagramChannel.bind(socketAddress);
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        while (true) {
            byteBuffer.clear();
            SocketAddress address = datagramChannel.receive(byteBuffer);
            //模式转换
            byteBuffer.flip();
            LOGGER.info("address info :{},content:{}", address.toString(), Charset.forName(StandardCharsets.UTF_8.toString()).decode(byteBuffer));
            TimeUnit.SECONDS.sleep(2);
        }
    }

    @Test
    public void serverSocketTest1() throws Exception {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("www.baidu.com", 80));
    }
}
