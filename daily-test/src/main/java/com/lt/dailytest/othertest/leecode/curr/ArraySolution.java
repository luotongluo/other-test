package com.lt.dailytest.othertest.leecode.curr;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 12828
 * @description ArraySolution
 * 在排序数组中查找元素的第一个和最后一个位置
 * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
 * <p>
 * 如果数组中不存在目标值 target，返回 [-1, -1]。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2021/12/18 22:51
 */
public class ArraySolution {
    /**
     * 一般的算法计算
     * 可以优化喂二分查找，因为提供的数组元素是有序的
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] searchRange(int[] nums, int target) {
        int[] returnArray = new int[2];
        returnArray[0] = -1;
        returnArray[1] = -1;
        if (nums.length < 1) {
            return returnArray;
        }
        List<Integer> useArrays = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (target == nums[i]) {
                useArrays.add(i);
            }
        }
        if (useArrays.size() > 0) {
            returnArray[0] = useArrays.get(0);
            returnArray[1] = useArrays.get(useArrays.size() - 1);
        }
        return returnArray;
    }
}
