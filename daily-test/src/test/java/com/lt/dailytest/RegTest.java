package com.lt.dailytest;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author tong.luo
 * @description RegTest
 * @date 2021/3/19 14:59
 */
public class RegTest {
    public static void main(String[] args) {
//        String ss = "非必填，自动带出可编辑；1-40字节，字母数字中文及：~`!@#$%^*()_+[]\\;',./?:\"{}|~·！@#￥%……*（）——+{}|：“《》？【】、；‘，。/  组合";
//        String regEx = "[中]*";
//        Matcher matcher = Pattern.compile(regEx).matcher(ss);
//        System.out.println(matcher.replaceAll(""));
//
//        String namereg = "^[a-zA-Z0-9]{2,14}$";
//        Matcher a = Pattern.compile(namereg).matcher("hbadmin");
//        System.out.println(a.replaceAll(""));
//        boolean hbadmin = Pattern.matches(namereg, "hbadminhbadminhbadmin");
//        System.out.println(hbadmin);
//        String phoneNum = "13344444444";
//        boolean b = regexPhoneNum(phoneNum);
//        System.out.println(b);
//        String mail = "123@asd.sd";
//        boolean b1 = validatMail(mail);
//        System.out.println("b1:" + b1);

//        String namereg = "^([0-9-—]{1,})$";
//        boolean isMatch =  Pattern.compile(namereg).matcher("010-56189057").matches();
//        System.out.println(isMatch);
        String namereg = "^[一-龥\\w\\s~!@%#$^*+='?\\-\\\\/(){}\\[\\],.\\|《》、，。！{}·#￥……*（）——:：“”？【】；‘’`_;\"]{1,40}$";
        boolean isMatch =  Pattern.compile(namereg).matcher("  Φ123*22MM123*22MM").matches();
        System.out.println(isMatch);
    }

    /**
     * https://blog.csdn.net/make164492212/article/details/51656638
     * @param mail
     * @return
     */
    private static boolean validatMail(String mail) {
        String mailReg = "^[0-9a-zA-Z]{0,9}@[0-9a-zA-Z]{0,9}[\\.]+$";
        //只允许英文字母、数字、下划线、英文句号、以及中划线组成
         mailReg = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";
         //名称允许汉字、字母、数字，域名只允许英文域名
         mailReg = "^[A-Za-z0-9\\u4e00-\\u9fa5]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";
         //通用的邮箱校验
         mailReg = "^[a-z0-9A-Z]+[- | a-z0-9A-Z . _]+@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\\\.)+[a-z]{2,}$";
        boolean matches = Pattern.compile(mailReg).matcher(mail).matches();
        return matches;
    }

    private static boolean regexPhoneNum(String phoneNum) {
        String phonReg = "^[1][3,9][0-9]{9}$";
        boolean matches = Pattern.compile(phonReg).matcher(phoneNum).matches();
        return matches;
    }
}
