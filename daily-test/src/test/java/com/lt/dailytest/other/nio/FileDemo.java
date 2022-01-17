package com.lt.dailytest.other.nio;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.concurrent.Future;

/**
 * @author tong.luo
 * @description FileDemo
 * @date 2021/11/23 19:24
 */
public class FileDemo {
    private static final Logger LOGGER = LoggerFactory.getLogger(FileDemo.class);

    @Test
    public void fileTest() throws Exception {
        Paths.get("D:\\testfile\\test.txt");
        Path rootPath = Paths.get("D:\\testfile");
        String fileToFind = File.separator + "test.txt";
        try {
            Files.walkFileTree(rootPath, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws
                        IOException {
                    String fileString = file.toAbsolutePath().toString();
                    //System.out.println("pathString = " + fileString);
                    if (fileString.endsWith(fileToFind)) {
                        System.out.println("file found at path: " + file.toAbsolutePath());
                        return FileVisitResult.TERMINATE;
                    }
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void ansyFileTest() throws Exception {
        Path path = Paths.get("D:\\testfile\\test.txt");
        AsynchronousFileChannel fileChannel = AsynchronousFileChannel.open(path, StandardOpenOption.READ);

        ByteBuffer byteBuffer = ByteBuffer.allocate(128);
        //调用channel的read方法得到future
        Future<Integer> read = fileChannel.read(byteBuffer, 0);

        while (!read.isDone()) ;

        byteBuffer.flip();
       /* while (byteBuffer.hasRemaining()){
            System.out.println(byteBuffer.get());
        }*/
        //使用数组获取其中的数据
        byte[] data = new byte[byteBuffer.limit()];
        byteBuffer.get(data);
        System.out.println(new String(data));
        byteBuffer.clear();
    }

    @Test
    public void complateHandlerTest() throws Exception {
        Path path = Paths.get("D:\\testfile\\test.txt");
        AsynchronousFileChannel fileChannel = AsynchronousFileChannel.open(path, StandardOpenOption.READ);
        ByteBuffer byteBuffer = ByteBuffer.allocate(128);
        fileChannel.read(byteBuffer, 0, byteBuffer, new CompletionHandler<Integer, ByteBuffer>() {

            /**
             * Invoked when an operation has completed.
             *
             * @param result     The result of the I/O operation.
             * @param attachment
             */
            @Override
            public void completed(Integer result, ByteBuffer attachment) {
                System.out.println("result = " + result);
                attachment.flip();
                byte[] data = new byte[attachment.limit()];
                attachment.get(data);
                System.out.println(new String(data));
                attachment.clear();
            }

            /**
             * Invoked when an operation fails.
             *
             * @param exc        The exception to indicate why the I/O operation failed
             * @param attachment
             */
            @Override
            public void failed(Throwable exc, ByteBuffer attachment) {

            }
        });
    }

    @Test
    public void synWrite1Test() throws Exception {
        Path path = Paths.get("D:\\testfile\\test.txt");
        AsynchronousFileChannel fileChannel = AsynchronousFileChannel.open(path, StandardOpenOption.WRITE);
        ByteBuffer byteBuffer = ByteBuffer.allocate(128);
        byteBuffer.put("撒服务尔恰额团促销中阿光环台湾全球".getBytes(StandardCharsets.UTF_8));
        byteBuffer.flip();
        Future<Integer> future = fileChannel.write(byteBuffer, 0);
        byteBuffer.clear();
        while (!future.isDone()) ;
        fileChannel.close();
        LOGGER.info("write over");
    }

    @Test
    public void synWriteTest() throws Exception {
        Path path = Paths.get("D:\\testfile\\test.txt");
        AsynchronousFileChannel fileChannel = AsynchronousFileChannel.open(path, StandardOpenOption.WRITE);
        ByteBuffer byteBuffer = ByteBuffer.allocate(128);
        byteBuffer.put("asdfasdfwerwras扥as扥as扥as扥".getBytes(StandardCharsets.UTF_8));
        byteBuffer.flip();
        fileChannel.write(byteBuffer, 0, byteBuffer, new CompletionHandler<Integer, ByteBuffer>() {
            @Override
            public void completed(Integer result, ByteBuffer attachment) {
                byteBuffer.clear();
                try {
                    fileChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                LOGGER.info("write over!");
            }

            @Override
            public void failed(Throwable exc, ByteBuffer attachment) {
                LOGGER.info("write faild");
                exc.printStackTrace();
            }
        });
    }
}
