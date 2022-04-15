package com.lt.dailytest.file;

import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class FileTest {
    private static final String FILE_AFTER_NAME = ".mp4";

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        String path1 = "E:\\Using\\2021-01-30";
        String path2 = "E:\\Using\\2021-02-27";
        String path3 = "E:\\Using\\2021-03-26";
        String path4 = "E:\\Using\\2021-05-04";
        String path5 = "E:\\Using\\2021-06-20";
        String path6 = "E:\\Using\\2021-08-29";
        String path7 = "E:\\Using\\2021-10-29";
        String path8 = "E:\\Using\\2021-11-10";
        String path9 = "E:\\Using\\2021-12-04";
        String path10 = "E:\\Using\\2022-01-07";
        String path11 = "E:\\Using\\2022-01-27";
        String path12 = "E:\\Using\\2022-02-18";
        String path13 = "E:\\Using\\2022-03-10";
        String path14 = "E:\\Using\\2022-03-21";

        List<String> pathList = Arrays.asList(path1, path2, path3, path4, path5,
                path6, path7, path8, path9, path10, path11, path12,path13,path14);
        String pathJoin = pathList.stream().collect(Collectors.joining(","));
        System.out.println("pathJoin：" + pathJoin);

        getReplaceFileName(pathJoin, null);
//        getFiles(pathJoin, null, FILE_AFTER_NAME);
//        rennameFileName(path3, null);
        System.out.println("cost time:" + (System.currentTimeMillis() - startTime));
    }

    /**
     * 获取 文件中重复的文件名
     *
     * @param filepath
     * @param filepathSplite
     * @return
     */
    public static ArrayList<String> getReplaceFileName(String filepath, String filepathSplite) {
        ArrayList<String> files = new ArrayList<>();
        if (StringUtils.isEmpty(filepath)) {
            return null;
        }
        if (StringUtils.isEmpty(filepathSplite)) {
            filepathSplite = ",";
        }
        StringBuilder builder = new StringBuilder();
        String[] split = filepath.split(filepathSplite);
        HashMap<String, String> hashMap = new HashMap<>();
        for (String filename : split) {
            File file = new File(filename);
            File[] tempLists = file.listFiles();
            for (int i = 0; i < tempLists.length; i++) {
                if (tempLists[i].isFile()) {
                    String name = tempLists[i].getName();
                    boolean contains = hashMap.keySet().contains(name);
                    //判断文件名是否开头或者结尾开始
                    for (String beforeName : hashMap.keySet()) {
                        if (beforeName.startsWith(name) || name.startsWith(beforeName)) {
                            System.out.println(name + "=======startsWith======" + filename);
                            System.out.println(hashMap.get(name) + "======before======startsWith=======" + beforeName + "\n========");
                        }
                    }
                    //判断文件是否包含别的文件名
                    if (contains) {
                        System.out.println(name + "=============" + filename);
                        System.out.println(hashMap.get(name) + "======before=======\n========");
                        //tempLists[i].delete();
                    } else {
                        hashMap.put(name, filename);
                    }

                }
            }
        }
        System.out.println("size : " + hashMap.keySet().size());
        System.out.println(builder);
        return files;
    }

    /**
     * @param filepath eg file,file2
     * @return
     */
    public static ArrayList<String> getFiles(String filepath, String filepathSplite, String fileNameReplace) {
        ArrayList<String> files = new ArrayList<String>();
        if (StringUtils.isEmpty(filepath)) {
            return null;
        }
        if (StringUtils.isEmpty(filepathSplite)) {
            filepathSplite = ",";
        }
        if (StringUtils.isEmpty(fileNameReplace)) {
            fileNameReplace = FILE_AFTER_NAME;
        }
        StringBuilder builder = new StringBuilder();
        String[] split = filepath.split(filepathSplite);
        HashMap<String, String> hashMap = new HashMap<>();
        for (String filename : split) {
            File file = new File(filename);
            File[] tempLists = file.listFiles();
            for (int i = 0; i < tempLists.length; i++) {
                if (tempLists[i].isFile()) {
                    String name = tempLists[i].getName();
                    /*if (!name.contains(fileNameReplace)) {
                        builder.append("path:" + filename + "\t  " + name + FILE_AFTER_NAME + " \n");
                    }*/
                    boolean contains = hashMap.keySet().contains(name);
                    if (contains) {
                        System.out.println(name + "=============" + filename);
                        System.out.println(hashMap.get(name) + "======before=======");
                        //tempLists[i].delete();
                    }
//                    boolean exists = false;
//                    for (String fileExistsName : hashMap.keySet()) {
//                        exists = fileExistsName.contains(name);
//                    }
                    /*for (String fileExistsName : hashMap.keySet()) {
                        if (contains || fileExistsName.contains(name)) {
                            System.out.println(name + "=============" + filename);
                            System.out.println(hashMap.get(name) + "======before=======");
                            //tempLists[i].delete();
                        }
                    }*/

                    hashMap.put(name, filename);

                }
            }
        }
        System.out.println("size : " + hashMap.keySet().size());
        System.out.println(builder);
//        for (int i = 0; i < files.size(); i++) {
//            System.out.println(files.get(i));
//        }
        return files;
    }

    /**
     * 更换文件名称
     *
     * @param path           文件地址
     * @param fileNameSplite 文件分隔符默认为 _
     */
    public static void rennameFileName(String path, String fileNameSplite) {
        if (StringUtils.isEmpty(fileNameSplite)) {
            fileNameSplite = "_";
        }

        String[] split = path.split(",");
        for (String filePath : split) {
            File folder = new File(filePath);
            File[] files = folder.listFiles();
            for (File file : files) {
                String name = file.getName();
                String[] s = name.split(fileNameSplite);
                if (name.contains(FILE_AFTER_NAME)) {
                    continue;
                }
                File newfile = new File(path + "/" + s[0] + FILE_AFTER_NAME);
                System.out.println(newfile);
                file.renameTo(newfile);
            }
        }

    }
}
