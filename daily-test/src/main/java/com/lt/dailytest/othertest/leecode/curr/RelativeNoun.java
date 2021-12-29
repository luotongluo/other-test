package com.lt.dailytest.othertest.leecode.curr;

import com.alibaba.fastjson.JSON;
import com.lt.dailytest.utils.project.ThreadPoolUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

/**
 * @author admin
 * @description RelativeNoun
 * 506. 相对名次
 * 给你一个长度为 n 的整数数组 score ，其中 score[i] 是第 i 位运动员在比赛中的得分。所有得分都 互不相同 。
 * <p>
 * 运动员将根据得分 决定名次 ，其中名次第 1 的运动员得分最高，名次第 2 的运动员得分第 2 高，依此类推。运动员的名次决定了他们的获奖情况：
 * <p>
 * 名次第 1 的运动员获金牌 "Gold Medal" 。
 * 名次第 2 的运动员获银牌 "Silver Medal" 。
 * 名次第 3 的运动员获铜牌 "Bronze Medal" 。
 * 从名次第 4 到第 n 的运动员，只能获得他们的名次编号（即，名次第 x 的运动员获得编号 "x"）。
 * 使用长度为 n 的数组 answer 返回获奖，其中 answer[i] 是第 i 位运动员的获奖情况。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：score = [5,4,3,2,1]
 * 输出：["Gold Medal","Silver Medal","Bronze Medal","4","5"]
 * 解释：名次为 [1st, 2nd, 3rd, 4th, 5th] 。
 * 示例 2：
 * <p>
 * 输入：score = [10,3,8,9,4]
 * 输出：["Gold Medal","5","Bronze Medal","Silver Medal","4"]
 * 解释：名次为 [1st, 5th, 3rd, 2nd, 4th] 。
 *  
 * <p>
 * 提示：
 * <p>
 * n == score.length
 * 1 <= n <= 104
 * 0 <= score[i] <= 106
 * score 中的所有值 互不相同
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/relative-ranks
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2021/12/28 15:46
 */
public class RelativeNoun {
    public static void main(String[] args) {
        RelativeNoun relativeNoun = new RelativeNoun();
        int[] score = {10, 3, 8, 9, 4};
        String[] relativeRanks = relativeNoun.findRelativeRanks(score);
        System.out.println(JSON.toJSONString(relativeRanks));

        Executor defaultThreadPool = ThreadPoolUtils.getDefaultThreadPool();
        for (int i = 0; i < 10000; i++) {
            defaultThreadPool.execute(() -> {

                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                String[] relativeRanks1 = relativeNoun.findRelativeRanks(score);
                System.out.println(JSON.toJSONString(relativeRanks1) + ":::                        " + Thread.currentThread().getName());
            });
        }
        for (; ; ) {

        }
    }

    public String[] findRelativeRanks(int[] score) {

        int[] fefore = Arrays.copyOf(score, score.length);
        Arrays.sort(score);
        HashMap<Integer, String> hashMap = new HashMap<>(64);
        System.out.println(JSON.toJSONString(score));
        int length = score.length;
        String[] strRets = new String[length];
        for (int i = 0; i < length; i++) {
            //从小到大的排序 最开始的是最后一名
            String valuestr = "";
            if (length - i == 2) {
                valuestr = "Silver Medal";
            } else if (length - i == 1) {
                valuestr = "Gold Medal";
            } else if (length - i == 3) {
                valuestr = "Bronze Medal";
            } else {
                valuestr = String.valueOf(length - i);
            }
            hashMap.put(score[i], valuestr);

        }
        System.out.println(JSON.toJSONString(hashMap));

        for (int i = 0; i < length; i++) {
            int num = fefore[i];
            String rets = hashMap.get(num);
            strRets[i] = rets;
        }
        return strRets;
    }
}
