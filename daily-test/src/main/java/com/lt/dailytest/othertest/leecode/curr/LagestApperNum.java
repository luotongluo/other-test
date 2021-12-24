package com.lt.dailytest.othertest.leecode.curr;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author tong.luo
 * @description lagestPaperNum
 * 1705. 吃苹果的最大数目
 * 有一棵特殊的苹果树，一连 n 天，每天都可以长出若干个苹果。在第 i 天，树上会长出 apples[i] 个苹果，
 * 这些苹果将会在 days[i] 天后（也就是说，第 i + days[i] 天时）腐烂，变得无法食用。也可能有那么几天，树上不会长出新的苹果，
 * 此时用 apples[i] == 0 且 days[i] == 0 表示。
 * <p>
 * 你打算每天 最多 吃一个苹果来保证营养均衡。注意，你可以在这 n 天之后继续吃苹果。
 * <p>
 * 给你两个长度为 n 的整数数组 days 和 apples ，返回你可以吃掉的苹果的最大数目。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-number-of-eaten-apples
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 示例 1：
 * <p>
 * 输入：apples = [1,2,3,5,2], days = [3,2,1,4,2]
 * 输出：7
 * 解释：你可以吃掉 7 个苹果：
 * - 第一天，你吃掉第一天长出来的苹果。
 * - 第二天，你吃掉一个第二天长出来的苹果。
 * - 第三天，你吃掉一个第二天长出来的苹果。过了这一天，第三天长出来的苹果就已经腐烂了。
 * - 第四天到第七天，你吃的都是第四天长出来的苹果。
 * 示例 2：
 * <p>
 * 输入：apples = [3,0,0,0,0,2], days = [3,0,0,0,0,2]
 * 输出：5
 * 解释：你可以吃掉 5 个苹果：
 * - 第一天到第三天，你吃的都是第一天长出来的苹果。
 * - 第四天和第五天不吃苹果。
 * - 第六天和第七天，你吃的都是第六天长出来的苹果。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-number-of-eaten-apples
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2021/12/24 11:09
 */
public class LagestApperNum {
    public static void main(String[] args) {
        LagestApperNum lagestApperNum = new LagestApperNum();
        int[] apples = {1, 2, 3, 5, 2};
        int[] days = {3, 2, 1, 4, 2};
        int i = lagestApperNum.eatenApples2(apples, days);
        System.out.println(i);
    }

    /**
     * 经过的天数 需要计算出当天的可用苹果数 与过期苹果数；
     * 如果可用苹果数大于0 就说明 当天吃到了苹果 吃到苹果的天数 +1 如果当天没有吃到苹果则吃苹果天数不增加
     * 根据过期天数进行排序，优先过期的最先食用
     *
     * @param apples 每天产生的苹果数
     * @param days   苹果对应的有效期天数
     * @return
     */
    public int eatenApples(int[] apples, int[] days) {
        int day = 0, actday = 0, passday = 0;
        for (int i = 0; i < apples.length; i++) {
            //苹果对应的天数
            int dayApple = apples[i];
            int actAppDay = days[i];

            while (actday < actAppDay && dayApple > 0) {
                //当当前的可吃苹果数大于当前的天数 吃苹果的天数+1
                //将 苹果数 -1 将剩余的天数 -1 与可吃的天数+1
                apples[i] = dayApple--;
                days[i] = actAppDay--;
                day++;
                passday++;
                actday++;
                if (days[i] == 0) {
                    actday = 0;
                }
            }
        }
        return day;
    }

    /**
     * 使用 map
     *
     * @param apples
     * @param days
     * @return
     */
    public int eatenApples2(int[] apples, int[] days) {
        //key是对应的天数  value 是存在的苹果数
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < apples.length; i++) {
            //苹果对应的天数
            int dayApple = apples[i];
            int actAppDay = days[i];
            if (dayApple > 0 && actAppDay > 0) {
                hashMap.put(actAppDay + i, dayApple);
            }
        }
        Set<Integer> integers = hashMap.keySet();
        //天数的排序 在这天所拥有的的苹果数
        Set<Integer> daySet = integers.stream().sorted().collect(Collectors.toSet());
        for (Integer integer : daySet) {
            //
        }
        System.out.println(JSON.toJSONString(hashMap));
        return 0;
    }

    public int eatenApples1(int[] apples, int[] days) {
        int ans = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>((a, b) -> a[0] - b[0]);
        int n = apples.length;
        int i = 0;
        while (i < n) {
            while (!pq.isEmpty() && pq.peek()[0] <= i) {
                pq.poll();
            }
            int rottenDay = i + days[i];
            int count = apples[i];
            if (count > 0) {
                pq.offer(new int[]{rottenDay, count});
            }
            if (!pq.isEmpty()) {
                int[] arr = pq.peek();
                arr[1]--;
                if (arr[1] == 0) {
                    pq.poll();
                }
                ans++;
            }
            i++;
        }
        while (!pq.isEmpty()) {
            while (!pq.isEmpty() && pq.peek()[0] <= i) {
                pq.poll();
            }
            if (pq.isEmpty()) {
                break;
            }
            int[] arr = pq.poll();
            int curr = Math.min(arr[0] - i, arr[1]);
            ans += curr;
            i += curr;
        }
        return ans;
    }

}
