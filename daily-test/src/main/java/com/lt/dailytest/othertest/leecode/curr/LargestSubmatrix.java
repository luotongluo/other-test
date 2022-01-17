package com.lt.dailytest.othertest.leecode.curr;

import com.alibaba.fastjson.JSON;

import java.util.Arrays;

/**
 * @author tong.luo
 * @description LargestSubmatrix
 * 重新排列后的最大子矩阵
 * 给你一个二进制矩阵 matrix ，它的大小为 m x n ，你可以将 matrix 中的 列 按任意顺序重新排列。
 * <p>
 * 请你返回最优方案下将 matrix 重新排列后，全是 1 的子矩阵面积。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/largest-submatrix-with-rearrangements
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2021/12/15 16:17
 */
public class LargestSubmatrix {
    public static void main(String[] args) {
        //[[1,1,0],[1,0,1]]
        int[][] matrix = {{1, 1, 0}, {1, 0, 1}};
        int i = largestSubmatrix(matrix);
        System.out.println(i);
    }

    public static int largestSubmatrix(int[][] matrix) {
        int length = matrix.length;
        //计算出其中1的个数
        int[][] matrixuse = new int[length][length];
        for (int i = 0; i < length; i++) {
            int[] nums = matrix[i];
            int count = 0;
            for (int j = 0; j < nums.length; j++) {
                if (1 == nums[j]) {
                    count++;
                }
            }
            matrixuse[i][0] = count;

        }
        Arrays.sort(matrixuse);
        System.out.println(matrix);
        //取出一位数组中的最大面积值
        int area = 0;
//        for (int j = 0; j < length; j++) {
//            int actarea = matrixuse[j] * (length - j);
//            if (actarea > area) {
//                area = actarea;
//            }
//
//        }
        System.out.println("after:" + JSON.toJSONString(matrixuse));
        System.out.println("area:" + area);
        return area;
    }
    public static int largestSubmatrix1(int[][] matrix) {
        int n=matrix.length;
        int m=matrix[0].length;
        int res=0;
        for(int i=1;i<n;i++){
            for(int j=0;j<m;j++){
                if(matrix[i][j]==1){
                    //记录向上连续1的个数
                    matrix[i][j]+=matrix[i-1][j];
                }
            }
        }
        for(int i=0;i<n;i++){
            Arrays.sort(matrix[i]);
            for(int j=m-1;j>=0;j--){
                //更新矩形的最大高度
                int height=matrix[i][j];
                //更新最大面积
                res=Math.max(res,height*(m-j));
            }
        }
        return res;
    }

}
