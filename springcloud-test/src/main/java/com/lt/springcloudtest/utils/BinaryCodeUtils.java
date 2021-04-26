package com.lt.springcloudtest.utils;

import com.swetake.util.Qrcode;
import jp.sourceforge.qrcode.QRCodeDecoder;
import jp.sourceforge.qrcode.data.QRCodeImage;
import org.apache.commons.lang3.StringUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author tong.luo
 * @description BinaryCodeUtils
 * 代码参考地址
 * https://www.cnblogs.com/bigroc/p/7496995.html
 * https://www.cnblogs.com/liuyun-10/p/6169938.html
 * 将jar包打包在本地
 * mvn install:install-file -Dfile=QRCode.jar -DgroupId=com.lt -DartifactId=QRCode -Dversion=1.0 -Dpackaging=jar
 * @date 2021/1/27 16:46
 */
public class BinaryCodeUtils {
    /**
     * 生成二维码
     *
     * @param data
     * @param path
     * @return
     */
    public static boolean encode(String data, String path) throws Exception {
        //计算二维码图片的高宽比
        // API文档规定计算图片宽高的方式 ，v是本次测试的版本号
        int v = 6;
        int width = 67 + 12 * (v - 1);
        int height = 67 + 12 * (v - 1);


        Qrcode x = new Qrcode();
        /**
         * 纠错等级分为
         * level L : 最大 7% 的错误能够被纠正；
         * level M : 最大 15% 的错误能够被纠正；
         * level Q : 最大 25% 的错误能够被纠正；
         * level H : 最大 30% 的错误能够被纠正；
         */
        x.setQrcodeErrorCorrect('L');
        x.setQrcodeEncodeMode('B');//注意版本信息 N代表数字 、A代表 a-z,A-Z、B代表 其他)
        x.setQrcodeVersion(v);//版本号  1-40

        String qrData = "https://www.onlybigroc.cn";//内容信息
        if (StringUtils.isNotEmpty(data)) {
            qrData = data;
        }

        byte[] d = qrData.getBytes("utf-8");//汉字转格式需要抛出异常

        //缓冲区
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);

        //绘图
        Graphics2D gs = bufferedImage.createGraphics();

        gs.setBackground(Color.WHITE);
        gs.setColor(Color.BLACK);
        gs.clearRect(0, 0, width, height);

        //偏移量
        int pixoff = 2;


        /**
         * 容易踩坑的地方
         * 1.注意for循环里面的i，j的顺序，
         *   s[j][i]二维数组的j，i的顺序要与这个方法中的 gs.fillRect(j*3+pixoff,i*3+pixoff, 3, 3);
         *   顺序匹配，否则会出现解析图片是一串数字
         * 2.注意此判断if (d.length > 0 && d.length < 120)
         *   是否会引起字符串长度大于120导致生成代码不执行，二维码空白
         *   根据自己的字符串大小来设置此配置
         */
        if (d.length > 0 && d.length < 135) {
            boolean[][] s = x.calQrcode(d);

            for (int i = 0; i < s.length; i++) {
                for (int j = 0; j < s.length; j++) {
                    if (s[j][i]) {
                        gs.fillRect(j * 3 + pixoff, i * 3 + pixoff, 3, 3);
                    }
                }
            }
        }
        gs.dispose();
        bufferedImage.flush();
        //设置图片格式，与输出的路径
        ImageIO.write(bufferedImage, "png", new File(path));
        System.out.println("二维码生成完毕");
        return true;
    }

    public static String decode(String path) {

        System.out.println("开始解析二维码！");
        /*读取二维码图像数据*
         *
         */
        File imageFile = new File(path);
        BufferedImage image = null;
        try {
            image = ImageIO.read(imageFile);

        } catch (IOException e) {
            System.out.print("读取失败！" + e.getMessage());
            return null;
        }
        /*
         * 解二维码
         */
        QRCodeDecoder decoder = new QRCodeDecoder();
        J2SEImageGucas j2SEImageGucas = new J2SEImageGucas(image);
        String decodeData = new String(decoder.decode(j2SEImageGucas));
        System.out.println("解析内容如下：" + decodeData);
        return decodeData;
    }


    public static class J2SEImageGucas implements QRCodeImage {

        BufferedImage image;

        public J2SEImageGucas(BufferedImage image) {
            this.image = image;
        }

        @Override
        public int getHeight() {
            return image.getHeight();
        }

        @Override
        public int getPixel(int x, int y) {
            return image.getRGB(x, y);
        }

        @Override
        public int getWidth() {
            return image.getWidth();
        }
    }

    public static void main(String[] args) throws Exception{
        String data = "ConcurrentMarkSweepConcurrentMarkSweepConcurrentMarkSweepConcurrentMarkSweepConcurrentMarkSweepConcurrentMarkConcurrentMark1234567";
        System.out.println("data-->length:" + data.length());
        String fileName = "long-part.png";
        /**
         * 生成二维码
         */
        BinaryCodeUtils.encode(data, fileName);

        String decode = BinaryCodeUtils.decode(fileName);
        System.out.println(decode);
    }
}
