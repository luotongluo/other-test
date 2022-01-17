package com.lt.dailytest.othertest.leecode.curr;

/**
 * @author tong.luo
 * @description TwoStringMatch
 * 686. 重复叠加字符串匹配
 * 给定两个字符串 a 和 b，寻找重复叠加字符串 a 的最小次数，使得字符串 b 成为叠加后的字符串 a 的子串，如果不存在则返回 -1。
 * <p>
 * 注意：字符串 "abc" 重复叠加 0 次是 ""，重复叠加 1 次是 "abc"，重复叠加 2 次是 "abcabc"。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/repeated-string-match
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2021/12/22 15:01
 */
public class TwoStringMatch {
    public static void main(String[] args) {
        TwoStringMatch twoStringMatch = new TwoStringMatch();
        String a = "abc", b = "cabcabca";
        int i = twoStringMatch.repeatedStringMatch1(a, b);
        System.out.println(i);
    }
    public int repeatedStringMatch2(String a, String b) {
        int index = b.indexOf(a);
        // b 是 a 的字串直接返回即可，不是字串也可能是a+a的字串
        if (index == -1 ) return a.contains(b) ? 1 : (a + a).contains(b) ? 2 : -1;
        // (abc) - bc(abc)ab 中abc前需要重复一次 res=1
        // (abc) - (abc)ab 则不需要重复 因此res=0
        int res = index == 0 ? 0 : 1;
        while (index < b.length()) { //循环比较即可
            for (int i = 0; i < a.length() && index < b.length(); i++) {
                if (a.charAt(i) != b.charAt(index++)) return -1;
            }
            res++;
        }
        return res;
    }

    public int repeatedStringMatch(String a, String b) {

        if (a.length() > b.length()) {
            if (a.contains(b)) {
                return 1;
            } else if ((a + a).contains(b)) {
                return 2;
            }
            return -1;
        }
        if (b.contains(a)) {
            int retNum = 0;
            return this.extractedNum(a, b, retNum);
        } else {
            return -1;
        }
    }

    private Integer extractedNum(String a, String b, int num) {
        //在b包含a的情况下在去判断回文的字段
        String[] split = b.split(a);
        //说明 b包含a
        if (split.length == 0) {
            return b.length() / a.length() + num;
        }
        //取出 截取后的头和尾 判断头和尾是否在a中 如果在其中 则返回包含的数字，如果不包含 则返回 -1
        for (int i = 0; i < 2; i++) {
            String str = split[0];
            String endStr = split[split.length - 1];
            if (str.length() > 0) {
                //拆分后的前段大于并且包含a 判断剩余的位数 是否与a 中相对应的位数相同
                Boolean contail = (str.length() > a.length()) && str.contains(a);
                Boolean contailpre = (str.length() < a.length()) && a.contains(str);
                if (contail) {
                    num++;
                    extractedNum(str, a, num);
                }
                if (contailpre) {
                    //num 数+ 1
                    num++;
                }

            } else {
                num++;
            }
        }
        return num;
    }

    public int repeatedStringMatch1(String a, String b) {
        int l = (b.length() + a.length() - 1) / a.length();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < l; i++)
            sb.append(a);
        for (int i = 0; i <= sb.length() - b.length(); i++) {
            if (sb.substring(i, i + b.length()).equals(b))
                return l;
        }
        sb.append(a);
        for (int i = a.length() * l - b.length() + 1; i <= sb.length() - b.length(); i++)
            if (sb.substring(i, i + b.length()).equals(b))
                return l + 1;
        return -1;
    }

}
