package com.lt.dailytest.othertest.leecode.curr;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author tong.luo
 * @description TownJudge
 * 997.小镇里有 n 个人，按从 1 到 n 的顺序编号。传言称，这些人中有一个暗地里是小镇法官。
 * <p>
 * 如果小镇法官真的存在，那么：
 * <p>
 * 小镇法官不会信任任何人。
 * 每个人（除了小镇法官）都信任这位小镇法官。
 * 只有一个人同时满足属性 1 和属性 2 。
 * 给你一个数组 trust ，其中 trust[i] = [ai, bi] 表示编号为 ai 的人信任编号为 bi 的人。
 * <p>
 * 如果小镇法官存在并且可以确定他的身份，请返回该法官的编号；否则，返回 -1 。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-the-town-judge
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2021/12/20 11:25
 */
public class TownJudge {
    public static void main(String[] args) {
        int[][] trust = {{1, 3}, {2, 3}, {3, 1}};
        int n = 3;
        TownJudge townJudge = new TownJudge();
        int judge = townJudge.findJudge(n, trust);
        System.out.println(judge);
    }

    public int findJudge(int n, int[][] trust) {
        //其中value为true的是警长的候选人
        HashMap<Integer, Boolean> judgeMap = new HashMap<>();
        HashMap<Integer, Boolean> judgenormalMap = new HashMap<>();
        for (int i = 0; i < trust.length; i++) {
            int[] number = trust[i];
            Boolean aBoolean = judgeMap.get(number[1]);
            Boolean bBoolean = judgenormalMap.get(number[0]);

            if (null == aBoolean) {
                judgeMap.put(number[1], Boolean.TRUE);
            }
            if (null == bBoolean) {
                judgenormalMap.put(number[0], Boolean.FALSE);
            }
        }
        List<Integer> actual = new ArrayList<>();
        for (Map.Entry<Integer, Boolean> integerBooleanEntry : judgeMap.entrySet()) {
            Integer key = integerBooleanEntry.getKey();
            Boolean value = integerBooleanEntry.getValue();
            if (value) {
                actual.add(key);
            }
        }
        if (null != actual && actual.size() == 1 && !judgenormalMap.keySet().contains(actual)) {
            return actual.get(0);
        } else {
            return -1;
        }

    }
}
