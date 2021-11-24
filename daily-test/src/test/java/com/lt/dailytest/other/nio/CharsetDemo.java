package com.lt.dailytest.other.nio;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Set;

/**
 * @author tong.luo
 * @description CharsetDemo
 * @date 2021/11/23 20:40
 */
public class CharsetDemo {
    private static final Logger LOGGER = LoggerFactory.getLogger(CharsetDemo.class);

    @Test
    public void charsetTest() throws Exception {
        Charset charset = Charset.forName(StandardCharsets.UTF_8.name());
        CharsetEncoder charsetEncoder = charset.newEncoder();
        CharsetDecoder charsetDecoder = charset.newDecoder();
        CharBuffer charBuffer = CharBuffer.allocate(128);
        charBuffer.put(" CharBuffer.allocate(128儿童儿童)");
        charBuffer.flip();

        ByteBuffer byteBuffer = charsetEncoder.encode(charBuffer);
        for (int i = 0; i < byteBuffer.limit(); i++) {
            LOGGER.info("encode : {}", byteBuffer.get());
        }
        byteBuffer.flip();
        CharBuffer decode = charsetDecoder.decode(byteBuffer);
        LOGGER.info("decode :{}", decode.toString());


        System.out.println("指定其他格式解码:");
        Charset charset1 = Charset.forName(StandardCharsets.ISO_8859_1.name());
        byteBuffer.flip();
        CharBuffer charBuffer2 = charset1.decode(byteBuffer);
        System.out.println(charBuffer2.toString());
        //6.获取 Charset 所支持的字符编码
        Map<String, Charset> map = Charset.availableCharsets();
        Set<Map.Entry<String, Charset>> set = map.entrySet();
        for (Map.Entry<String, Charset> entry : set) {
            System.out.println(entry.getKey() + "=" + entry.getValue().toString());
        }
    }
}
