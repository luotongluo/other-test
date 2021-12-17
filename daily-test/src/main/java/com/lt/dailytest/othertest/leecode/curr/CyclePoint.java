package com.lt.dailytest.othertest.leecode.curr;

import com.alibaba.fastjson.JSON;

import java.math.BigDecimal;

/**
 * @author tong.luo
 * @description CyclePoint
 * 给定圆的半径和圆心的 x、y 坐标，写一个在圆中产生均匀随机点的函数 randPoint 。
 * <p>
 * 说明:
 * <p>
 * 输入值和输出值都将是浮点数。
 * 圆的半径和圆心的 x、y 坐标将作为参数传递给类的构造函数。
 * 圆周上的点也认为是在圆中。
 * randPoint 返回一个包含随机点的x坐标和y坐标的大小为2的数组。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/generate-random-point-in-a-circle
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2021/12/17 15:40
 */
public class CyclePoint {
    double rad, xc, yc;

    public static void main(String[] args) {
        CyclePoint cyclePoint = new CyclePoint();
        cyclePoint.Solution(5, 1.1, 2.2);
        double[] doubles = cyclePoint.randPoint();
        System.out.println(JSON.toJSONString(doubles));
    }

    /**
     * @param radius   半径
     * @param x_center 圆心点
     * @param y_center 圆心点
     */
    public void Solution(double radius, double x_center, double y_center) {
        rad = radius;
        xc = x_center;
        yc = y_center;
    }

    /**
     * 随机生成 在园内的点
     *
     * @return
     */
    public double[] randPoint() {
        double x0 = xc - rad;
        double y0 = yc - rad;
        while (true) {
            double xg = x0 + Math.random() * rad * 2;
            double yg = y0 + Math.random() * rad * 2;
            System.out.println("xg:" + xg + " yg:" + yg);
            BigDecimal bigDecimalx = new BigDecimal(String.valueOf(xg)).abs();
            BigDecimal bigdecimalXc = new BigDecimal(String.valueOf(xc)).abs();
            boolean judgex = bigDecimalx.subtract(bigdecimalXc).compareTo(new BigDecimal(String.valueOf(rad))) <= 0;
            BigDecimal decimaly = new BigDecimal(String.valueOf(yg)).abs();
            BigDecimal bigDecimalyc = new BigDecimal(String.valueOf(yc)).abs();
            boolean judgey = decimaly.subtract(bigDecimalyc).compareTo(new BigDecimal(String.valueOf(rad))) <= 0;
            //x值是否大于元的半径
            if (judgex && judgey) {
                return new double[]{xg, yg};
            }
        }

    }
}

