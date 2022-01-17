package com.lt.dailytest.othertest.leecode.curr;

import com.alibaba.fastjson.JSON;

import java.util.Arrays;

/**
 * @author tong.luo
 * @description
 * 给定一个由 整数 组成的 非空 数组所表示的非负整数，在该数的基础上加一。
 *
 * 最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。
 *
 * 你可以假设除了整数 0 之外，这个整数不会以零开头。
 * 示例 1：
 * 输入：digits = [1,2,3]
 * 输出：[1,2,4]
 * 解释：输入数组表示数字 123。
 * 示例 2：
 * 输入：digits = [4,3,2,1]
 * 输出：[4,3,2,2]
 * 解释：输入数组表示数字 4321。
 * 示例 3：
 * 输入：digits = [0]
 * 输出：[1]
 * 提示：
 * 1 <= digits.length <= 100
 * 0 <= digits[i] <= 9
 * Related Topics
 * 数组
 * 数学
 * @date 2022/1/13 17:41
 */
public class AddOne66 {
    public static void main(String[] args) {
        int[] digits = {9,9};
        AddOne66 addOne66 = new AddOne66();
        addOne66.plusOne(digits);
    }
    public int[] plusOne(int[] digits) {
        /*if (digits.length == 1) {
            digits[0] = digits[0]++;
            return digits;
        }
        List inteArray = Arrays.asList(digits);
        inteArray.stream().sorted();
        System.out.println(JSON.toJSONString(inteArray));*/
        digits[digits.length - 1] = digits[digits.length - 1] +1;
        if(digits[digits.length - 1] > 9){
            int[] copyOf = Arrays.copyOf(digits, digits.length + 1);
            copyOf[copyOf.length - 2] = 1;
            return copyOf;
        }
        System.out.println(JSON.toJSONString(digits));
        return digits;
    }
}
