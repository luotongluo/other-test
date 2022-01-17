package com.lt.dailytest.othertest.leecode.curr;

/**
 * @author tong.luo
 * @description ContinucationCharacter
 * 1446. 连续字符
 * 给你一个字符串 s ，字符串的「能量」定义为：只包含一种字符的最长非空子字符串的长度。
 * <p>
 * 请你返回字符串的能量。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "leetcode"
 * 输出：2
 * 解释：子字符串 "ee" 长度为 2 ，只包含字符 'e' 。
 * 示例 2：
 * <p>
 * 输入：s = "abbcccddddeeeeedcba"
 * 输出：5
 * 解释：子字符串 "eeeee" 长度为 5 ，只包含字符 'e' 。
 * 示例 3：
 * <p>
 * 输入：s = "triplepillooooow"
 * 输出：5
 * 示例 4：
 * <p>
 * 输入：s = "hooraaaaaaaaaaay"
 * 输出：11
 * 示例 5：
 * <p>
 * 输入：s = "tourist"
 * 输出：1
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= s.length <= 500
 * s 只包含小写英文字母。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/consecutive-characters
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2021/12/24 17:08
 */
public class ContinucationCharacter {
    public static void main(String[] args) {
        ContinucationCharacter continucationCharacter = new ContinucationCharacter();
        int tourist = continucationCharacter.maxPower("hooraaaaaaaaaaay");
        System.out.println(tourist);
    }

    public int maxPower(String s) {
        char[] toCharArray = s.toCharArray();
        //初始值为1  因为2个值只会比较1次 定义两个值 一个返回值 一个比较值 判断两个值的差 返回数据较大的
        int numRet = 1, cnt = 1;
        for (int i = 0; i < s.length() - 1; i++) {
            if (toCharArray[i] == toCharArray[i + 1]) {
                cnt++;
                numRet = numRet > cnt ? numRet : cnt;
            } else {
                cnt = 1;
            }
        }
        return numRet == 0 ? 1 : numRet;
    }
}
