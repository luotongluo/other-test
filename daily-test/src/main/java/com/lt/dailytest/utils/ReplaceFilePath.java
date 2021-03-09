package com.lt.dailytest.utils;

import java.io.File;

/**
 * @author tong.luo
 * @description ReplaceFilePath
 * 替换文件名称
 * @date 2021/3/3 10:55
 */
public class ReplaceFilePath {
    private static String newString = "null";
    private static String oldString = " ";

    public static void main(String[] args) {
        String path = "E:\\work-space\\git-file\\other-test\\out";
        recursiveTraversalFolder(path);
    }

    /**
     * 递归遍历文件夹获取文件
     */
    public static void recursiveTraversalFolder(String path) {
        File folder = new File(path);
        if (folder.exists()) {
            File[] fileArr = folder.listFiles();
            if (null == fileArr || fileArr.length == 0) {
                System.out.println("文件夹是空的!");
                return;
            } else {
                File newDir = null;//文件所在文件夹路径+新文件名
                String newName = "";//新文件名
                String fileName = null;//旧文件名
                File parentPath = new File("");//文件所在父级路径
                for (File file : fileArr) {
                    if (file.isDirectory()) {//是文件夹，继续递归，如果需要重命名文件夹，这里可以做处理
                        System.out.println("文件夹:" + file.getAbsolutePath() + "，继续递归！");
                        recursiveTraversalFolder(file.getAbsolutePath());
                    } else {//是文件，判断是否需要重命名
                        fileName = file.getName();
                        parentPath = file.getParentFile();

                        if (fileName.contains(oldString)) {//文件名包含需要被替换的字符串

                            newName = fileName.replaceAll(oldString, newString);//新名字
                            newDir = new File(parentPath + "/" + newName);//文件所在文件夹路径+新文件名
                            file.renameTo(newDir);//重命名
                            System.out.println("修改后：" + newDir);
                        }
                    }
                }
            }
        } else {
            System.out.println("文件不存在!");
        }
    }
}
