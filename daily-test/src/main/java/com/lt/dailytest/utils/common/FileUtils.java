package com.lt.dailytest.utils.common;

import com.lt.dailytest.utils.major.MajorKeyFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.UUID;

/**
 * @author tong.luo
 * @description FileUtils
 * @date 2021/9/8 11:08
 */
public class FileUtils {
    /**
     * 转换MultipartFile对象为java.io.File类型
     *
     * @param multipartFile
     * @return
     */
    public static File convertMultipartFileToFile(MultipartFile multipartFile) {
        File result = null;
        try {
            result = File.createTempFile(MajorKeyFactory.generatePrimaryKey(), null);
            multipartFile.transferTo(result);
            result.deleteOnExit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
