package com.lt.dailytest.utils.common;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.zip.Deflater;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author tong.luo
 * @description ZipUtils
 * 压缩相关的utils
 * @date 2021/9/6 13:50
 */
public class ZipUtils {

    public static final int BUFFER = 1024;
    private static final int BUFFER_SIZE = 2 * 1024;

    public static final String EXT = ".gz";
    private static final Logger LOGGER = LoggerFactory.getLogger(ZipUtils.class);

    /**
     * 数据压缩
     *
     * @param data
     * @return
     * @throws java.io.IOException
     */
    public static byte[] compress(byte[] data) throws IOException {
        byte[] output = null;
        ByteArrayInputStream bais = new ByteArrayInputStream(data);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            // 压缩
            compress(bais, baos);
            output = baos.toByteArray();
            baos.flush();
        } finally {
            baos.close();
            bais.close();
        }
        return output;
    }

    /**
     * 文件压缩
     *
     * @param file
     * @throws Exception
     */
    public static void compress(File file) throws Exception {
        compress(file, true);
    }

    /**
     * 文件压缩
     *
     * @param file
     * @param delete 是否删除原始文件
     * @throws Exception
     */
    public static void compress(File file, boolean delete) throws Exception {
        FileInputStream fis = new FileInputStream(file);
        FileOutputStream fos = new FileOutputStream(file.getPath() + EXT);
        compress(fis, fos);
        fis.close();
        fos.flush();
        fos.close();
        if (delete) {
            // boolean fal = file.delete();
            // if (log.isDebugEnabled())
            // log.debug("文件删除" + fal);
        }
    }

    /**
     * 数据压缩
     *
     * @param is
     * @param os
     * @throws java.io.IOException
     */
    public static void compress(InputStream is, OutputStream os) throws IOException {
        GZIPOutputStream gos = new GZIPOutputStream(os);
        int count;
        byte data[] = new byte[BUFFER];
        while ((count = is.read(data, 0, BUFFER)) != -1) {
            gos.write(data, 0, count);
        }
        gos.finish();
        gos.flush();
        gos.close();
    }

    /**
     * 文件压缩
     *
     * @param path
     * @throws Exception
     */
    public static void compress(String path) throws Exception {
        compress(path, true);
    }

    /**
     * 文件压缩
     *
     * @param path
     * @param delete 是否删除原始文件
     * @throws Exception
     */
    public static void compress(String path, boolean delete) throws Exception {
        File file = new File(path);
        compress(file, delete);
    }

    /**
     * 数据解压缩
     *
     * @param data
     * @return
     * @throws java.io.IOException
     * @throws Exception
     */
    public static byte[] decompress(byte[] data) throws IOException {
        ByteArrayInputStream bais = new ByteArrayInputStream(data);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        // 解压缩
        decompress(bais, baos);
        data = baos.toByteArray();
        baos.flush();
        baos.close();
        bais.close();
        return data;
    }

    /**
     * 数据解压缩
     *
     * @param is
     * @param os
     * @throws java.io.IOException
     * @throws Exception
     */
    public static void decompress(InputStream is, OutputStream os) throws IOException {
        GZIPInputStream gis = new GZIPInputStream(is);
        int count;
        byte data[] = new byte[BUFFER];
        while ((count = gis.read(data, 0, BUFFER)) != -1) {
            os.write(data, 0, count);
        }
        gis.close();
    }

    public static void doCompress(File inFile, ZipOutputStream out, String dir) throws IOException {
        String entryName = null;
        if (!"".equals(dir)) {
            entryName = dir + "/" + inFile.getName();
        } else {
            entryName = inFile.getName();
        }
        // 默认值为 ZipOutputStream.DEFLATED（表示进行压缩存储）
        out.setMethod(ZipOutputStream.DEFLATED);
        // 压缩级别值为0-9共10个级别(值越大，表示压缩越厉害)，默认为Deflater.DEFAULT_COMPRESSION=-1
        out.setLevel(Deflater.BEST_COMPRESSION);
        // 设置ZipEntry对象，并对需要压缩的文件命名
        out.putNextEntry(new ZipEntry(entryName));
        int len = 0;
        byte[] buffer = new byte[1024];
        FileInputStream fis = new FileInputStream(inFile);
        while ((len = fis.read(buffer)) > 0) {
            out.write(buffer, 0, len);
            out.flush();
        }
        out.closeEntry();
        fis.close();
    }

    public static void doCompress(byte[] inBytes, ZipOutputStream zipOut, String fileName) throws IOException {
        ByteArrayInputStream bais = new ByteArrayInputStream(inBytes);
        // 默认值为 ZipOutputStream.DEFLATED（表示进行压缩存储）
        zipOut.setMethod(ZipOutputStream.DEFLATED);
        // 压缩级别值为0-9共10个级别(值越大，表示压缩越厉害)
        zipOut.setLevel(Deflater.BEST_COMPRESSION);
        //设置ZipEntry对象，并对需要压缩的文件命名
        zipOut.putNextEntry(new ZipEntry(fileName));
        // 读取要压缩的字节输出流，进行压缩
        int temp = 0;
        while ((temp = bais.read()) != -1) {
            // 压缩输出
            zipOut.write(temp);
        }
        zipOut.closeEntry();
        bais.close();
    }

    public static void main(String[] args) throws IOException {
        System.out.println(compress(FileUtils.readFileToByteArray(new File("C:\\Users\\liuyong\\Desktop\\84503200911003620155.pdf"))).length);
        System.out.println(decompress(compress(FileUtils.readFileToByteArray(new File("C:\\Users\\liuyong\\Desktop\\84503200911003620155.pdf")))).length);

		/*ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ZipOutputStream zipOutputStream = new ZipOutputStream(baos);
		doCompress(new File("C:\\Users\\liuyong\\Desktop\\ofd\\11100001001156747658.ofd"), zipOutputStream, "");
		zipOutputStream.close();
		byte[] byteArray = baos.toByteArray();
		System.out.println(byteArray.length);
		FileUtils.writeByteArrayToFile(new File("C:\\Users\\liuyong\\Desktop\\11111.zip"), byteArray);
		baos.close();*/

    /*    // 2.创建字节数组输出流，用于返回压缩后的输出流字节数组
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        // 3.创建压缩输出流
        ZipOutputStream zipOut = new ZipOutputStream(baos);
     // 1.将需要压缩的字节输出流，转为字节数组输入流，
		byte[] byteArray = FileUtils
				.readFileToByteArray(new File("C:\\Users\\liuyong\\Desktop\\ofd\\11100001001156747658.ofd"));
		byte[] byteArray1 = FileUtils
				.readFileToByteArray(new File("C:\\Users\\liuyong\\Desktop\\ofd\\11100001001156747659.ofd"));
		doCompress(byteArray, zipOut, "11100001001156747658.ofd");
		doCompress(byteArray1, zipOut, "11100001001156747659.ofd");
        // 进行压缩存储
        zipOut.close();
        FileUtils.writeByteArrayToFile(new File("C:\\Users\\liuyong\\Desktop\\11111.zip"), baos.toByteArray());*/
    }

    /**
     * 压缩成ZIP 方法1
     *
     * @param srcDir           压缩文件夹路径
     * @param out              压缩文件输出流
     * @param KeepDirStructure 是否保留原来的目录结构,true:保留目录结构;
     *                         false:所有文件跑到压缩包根目录下(注意：不保留目录结构可能会出现同名文件,会压缩失败)
     * @throws RuntimeException 压缩失败会抛出运行时异常
     */
    public static boolean toZip(String srcDir, OutputStream out, boolean KeepDirStructure)
            throws RuntimeException {
        boolean success = true;
        long start = System.currentTimeMillis();
        ZipOutputStream zos = null;
        try {
            zos = new ZipOutputStream(out);
            File sourceFile = new File(srcDir);
            compress(sourceFile, zos, sourceFile.getName(), KeepDirStructure);
            long end = System.currentTimeMillis();
            LOGGER.debug("压缩完成，耗时：" + (end - start) + " ms");
            //删除临时文件夹
            boolean b = delFiles(new File(srcDir));
        } catch (Exception e) {
            success = false;
            throw new RuntimeException("ZipUtils压缩异常", e);
        } finally {
            if (zos != null) {
                try {
                    zos.close();
                } catch (IOException e) {
                    success = false;
                    LOGGER.error("[压缩异常]", e);
                }
            }
        }
        return success;
    }

    /**
     * 压缩成ZIP 方法2
     *
     * @param srcFiles 需要压缩的文件列表
     * @param out      压缩文件输出流
     * @throws RuntimeException 压缩失败会抛出运行时异常
     */
    public static void toZip(List<File> srcFiles, OutputStream out) throws RuntimeException {
        long start = System.currentTimeMillis();
        ZipOutputStream zos = null;
        try {
            zos = new ZipOutputStream(out);
            for (File srcFile : srcFiles) {
                byte[] buf = new byte[BUFFER_SIZE];
                zos.putNextEntry(new ZipEntry(srcFile.getName()));
                int len;
                FileInputStream in = new FileInputStream(srcFile);
                while ((len = in.read(buf)) != -1) {
                    zos.write(buf, 0, len);
                }
                zos.closeEntry();
                in.close();
            }
            long end = System.currentTimeMillis();
            LOGGER.debug("压缩完成，耗时：" + (end - start) + " ms");
        } catch (Exception e) {
            throw new RuntimeException("zip error from ZipUtils", e);
        } finally {
            if (zos != null) {
                try {
                    zos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * @param fileEntry key 为修改后的文件的名称  value为原文件相关信息
     * @param out
     * @throws IOException
     */
    public static void doCompress(Map.Entry<String, File> fileEntry, ZipOutputStream out) throws IOException {
        String entryName = fileEntry.getKey();
        // 默认值为 ZipOutputStream.DEFLATED（表示进行压缩存储）
        out.setMethod(ZipOutputStream.DEFLATED);
        // 压缩级别值为0-9共10个级别(值越大，表示压缩越厉害)，默认为Deflater.DEFAULT_COMPRESSION=-1
        out.setLevel(Deflater.BEST_COMPRESSION);
        // 设置ZipEntry对象，并对需要压缩的文件命名
        out.putNextEntry(new ZipEntry(entryName));
        int len = 0;
        byte[] buffer = new byte[1024];
        FileInputStream fis = new FileInputStream(fileEntry.getValue());
        while ((len = fis.read(buffer)) > 0) {
            out.write(buffer, 0, len);
            out.flush();
        }
        out.closeEntry();
        fis.close();
    }

    /**
     * 递归压缩方法
     *
     * @param sourceFile       源文件
     * @param zos              zip输出流
     * @param name             压缩后的名称
     * @param KeepDirStructure 是否保留原来的目录结构,true:保留目录结构;
     *                         false:所有文件跑到压缩包根目录下(注意：不保留目录结构可能会出现同名文件,会压缩失败)
     * @throws Exception
     */
    private static void compress(File sourceFile, ZipOutputStream zos, String name,
                                 boolean KeepDirStructure) throws Exception {
        byte[] buf = new byte[BUFFER_SIZE];
        if (sourceFile.isFile()) {
            // 向zip输出流中添加一个zip实体，构造器中name为zip实体的文件的名字
            zos.putNextEntry(new ZipEntry(name));
            // copy文件到zip输出流中
            int len;
            FileInputStream in = new FileInputStream(sourceFile);
            while ((len = in.read(buf)) != -1) {
                zos.write(buf, 0, len);
            }
            // Complete the entry
            zos.closeEntry();
            in.close();
        } else {
            File[] listFiles = sourceFile.listFiles();
            if (listFiles == null || listFiles.length == 0) {
                // 需要保留原来的文件结构时,需要对空文件夹进行处理
                if (KeepDirStructure) {
                    // 空文件夹的处理
                    zos.putNextEntry(new ZipEntry(name + "/"));
                    // 没有文件，不需要文件的copy
                    zos.closeEntry();
                }
            } else {
                for (File file : listFiles) {
                    // 判断是否需要保留原来的文件结构
                    if (KeepDirStructure) {
                        // 注意：file.getName()前面需要带上父文件夹的名字加一斜杠,
                        // 不然最后压缩包中就不能保留原来的文件结构,即：所有文件都跑到压缩包根目录下了
                        compress(file, zos, name + "/" + file.getName(), KeepDirStructure);
                    } else {
                        compress(file, zos, file.getName(), KeepDirStructure);
                    }
                }
            }
        }
    }

    public static boolean delFiles(File file) {
        boolean result = false;
        //目录
        if (file.isDirectory()) {
            File[] childrenFiles = file.listFiles();
            for (File childFile : childrenFiles) {
                result = delFiles(childFile);
                if (!result) {
                    return result;
                }
            }
        }
        //删除 文件、空目录
        result = file.delete();
        return result;
    }

    // 批量下载文件
    public static void batchFileOut(List<String> urls, List<String> fileNames, HttpServletRequest request, HttpServletResponse response) {
        // 对文件流进行压缩操作
        ZipOutputStream zos = null;
        // 读取文件流
        BufferedInputStream br = null;
        try {
            // 文件的名称
            String downloadFilename = "批量下载.zip";

        /*    String userAgent = request.getHeader("User-Agent");//获取浏览器名（IE/Chome/firefox）
            if (userAgent.contains("firefox")) {
                downloadFilename = new String(downloadFilename.getBytes("UTF-8"), "ISO8859-1"); // firefox浏览器
            } else if (userAgent.contains("MSIE")) {
                downloadFilename = URLEncoder.encode(downloadFilename, "UTF-8");// IE浏览器
            } else if (userAgent.contains("CHROME")) {
                downloadFilename = new String(downloadFilename.getBytes("UTF-8"), "ISO8859-1");// 谷歌
            }*/
            //headers.setContentDispositionFormData("attachment", fileName);// 默认下载文件名为原始文件名

            response.reset();
            // 设置格式
            response.setContentType("application/x-msdownload");
            response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(downloadFilename, "UTF-8"));
            // 初始化压缩流
            zos = new ZipOutputStream(response.getOutputStream());
            // 循环下载文件并进行压缩
            for (int i = 0; i < fileNames.size(); i++) {
                // 设置压缩文件名称
                zos.putNextEntry(new ZipEntry(fileNames.get(i)));
                try {
                    // 获取原文件流，不存在则继续操作
                    br = new BufferedInputStream(new FileInputStream(urls.get(i)));
                } catch (FileNotFoundException e1) {
                    LOGGER.error("文件下载错误！文件路径：" + urls.get(i));
                    // 当前文件下载错误则跳过继续下载下一个文件
                    continue;
                }
                // 按字节依次读取文件并写入压缩流
                byte[] buffer = new byte[1024];
                int r = 0;
                while ((r = br.read(buffer)) != -1) {
                    zos.write(buffer, 0, r);
                }
            }
            // 输出压缩文件流
            zos.flush();
        } catch (IOException e) {
            LOGGER.error("[下载发票压缩文件异常]", e);
        } finally {
            try {
                // 关闭压缩流和文件流
                if (zos != null) {
                    zos.close();
                }
                if (br != null) {
                    br.close();
                }
            } catch (Exception e) {
                LOGGER.error("[下载发票压缩文件异常]", e);
            }
        }
    }

    // 批量下载文件
    public static void batchFileOutOne(List<String> urls, List<String> fileNames, HttpServletRequest request, HttpServletResponse response) {
        // 对文件流进行压缩操作
        ServletOutputStream zos = null;
        // 读取文件流
        BufferedInputStream br = null;
        try {
            response.reset();
            response.setContentType("application/x-msdownload");
            zos = response.getOutputStream();
            for (int i = 0; i < fileNames.size(); i++) {
                response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileNames.get(i), "UTF-8"));
                try {
                    // 获取原文件流，不存在则继续操作
                    br = new BufferedInputStream(new FileInputStream(urls.get(i)));
                } catch (FileNotFoundException e1) {
                    LOGGER.error("文件下载错误！文件路径：" + urls.get(i));
                    // 当前文件下载错误则跳过继续下载下一个文件
                    continue;
                }
                // 按字节依次读取文件并写入压缩流
                byte[] buffer = new byte[1024];
                int r = 0;
                while ((r = br.read(buffer)) != -1) {
                    zos.write(buffer, 0, r);
                }
            }
            // 输出压缩文件流
            zos.flush();
        } catch (IOException e) {
            LOGGER.error("[下载发票压缩文件异常]", e);
        } finally {
            try {
                // 关闭压缩流和文件流
                if (zos != null) {
                    zos.close();
                }
                if (br != null) {
                    br.close();
                }
            } catch (Exception e) {
                LOGGER.error("[下载发票压缩文件异常]", e);
            }
        }
    }

    public static boolean writeDir(SXSSFWorkbook workbook, String tempDir, String fileName) {
        boolean success = true;

        FileOutputStream fileOutputStream = null;
        try {
            String filePath = tempDir + File.separator + fileName + ".xlsx";
            createFile(filePath);
            fileOutputStream = new FileOutputStream(filePath);
            //BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath), "UTF-8"));
            workbook.write(fileOutputStream);
        } catch (IOException e) {
            LOGGER.error("[发票导出写入异常]", e);
            success = false;
        } finally {
            try {
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                if (workbook != null) {
                    workbook.close();
                }
            } catch (IOException e) {
                LOGGER.error("[发票导出写入异常]", e);
                success = false;
            }
        }
        return success;
    }

    public static void createFile(String path) throws IOException {
        if (StringUtils.isNotEmpty(path)) {
            File file = new File(path);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            file.createNewFile();
        }
    }

    /*public static void main(String[] args) throws Exception {
     *//** 测试压缩方法1  *//*
        FileOutputStream fos1 = new FileOutputStream(new File("c:/mytest01.zip"));
        ZipUtils.toZip("D:/log", fos1, true);

        *//** 测试压缩方法2  *//*
        List<File> fileList = new ArrayList<>();
        fileList.add(new File("D:/Java/jdk1.7.0_45_64bit/bin/jar.exe"));
        fileList.add(new File("D:/Java/jdk1.7.0_45_64bit/bin/java.exe"));
        FileOutputStream fos2 = new FileOutputStream(new File("c:/mytest02.zip"));
        ZipUtils.toZip(fileList, fos2);
    }*/

}
