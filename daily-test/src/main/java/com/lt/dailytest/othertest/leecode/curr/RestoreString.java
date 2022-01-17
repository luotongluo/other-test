package com.lt.dailytest.othertest.leecode.curr;

/**
 * @author tong.luo
 * @description RestoreString
 * 1528. 重新排列字符串
 * 给你一个字符串 s 和一个 长度相同 的整数数组 indices 。
 * <p>
 * 请你重新排列字符串 s ，其中第 i 个字符需要移动到 indices[i] 指示的位置。
 * <p>
 * 返回重新排列后的字符串。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/shuffle-string
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2021/12/13 19:17
 */
public class RestoreString {
    public static void main(String[] args) {
        String str = "codeleet";
        int[] nums = {4,5,6,7,0,2,1,3};
        String string = restoreString(str, nums);
        System.out.println(string);
    }

    public static String restoreString(String s, int[] indices) {
        if (s.length() != indices.length) {
            return null;
        }
        int length = indices.length;
        char[] chars = new char[length];
        for (int i = 0; i < indices.length; i++) {
            chars[indices[i]] = s.charAt(i);
        }

        return new String(chars);
    }
}
