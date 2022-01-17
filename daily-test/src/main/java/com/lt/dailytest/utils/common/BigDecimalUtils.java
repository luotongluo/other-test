package com.lt.dailytest.utils.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import java.math.BigDecimal;

/**
 * @author tong.luo
 * @description BigDecimalUtils
 * @date 2021/8/12 10:33
 */
public class BigDecimalUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(BigDecimalUtils.class);

    public static void main(String[] args) {
        Object o1 = "12";
        Object o2 = 2.01;
        //5.9701493
        BigDecimal add = BigDecimalUtils.divid(o1, o2,6,BigDecimal.ROUND_UP);
        System.out.println(add);
    }
    /**
     * @param o1
     * @param o2
     * @return
     */
    public static BigDecimal add(Object o1, Object o2) {
        try {
            Assert.notNull(o1, "传入参数不能为空");
            Assert.notNull(o2, "传入参数不能为空");
            BigDecimal bigDecimal1 = new BigDecimal(String.valueOf(o1));
            BigDecimal bigDecimal2 = new BigDecimal(String.valueOf(o2));
            return bigDecimal1.add(bigDecimal2);
        } catch (Exception e) {
            LOGGER.error("add : req o1 :[{}],o2:{}", o1, o2, e);
            return null;
        }
    }

    public static BigDecimal sub(Object o1, Object o2) {
        try {
            Assert.notNull(o1, "传入参数不能为空");
            Assert.notNull(o2, "传入参数不能为空");
            BigDecimal bigDecimal1 = new BigDecimal(String.valueOf(o1));
            BigDecimal bigDecimal2 = new BigDecimal(String.valueOf(o2));
            return bigDecimal1.subtract(bigDecimal2);
        } catch (Exception e) {
            LOGGER.error("sub : req o1 :[{}],o2:{}", o1, o2, e);
            return null;
        }
    }

    public static BigDecimal mult(Object o1, Object o2) {
        try {
            Assert.notNull(o1, "传入参数不能为空");
            Assert.notNull(o2, "传入参数不能为空");
            BigDecimal bigDecimal1 = new BigDecimal(String.valueOf(o1));
            BigDecimal bigDecimal2 = new BigDecimal(String.valueOf(o2));
            return bigDecimal1.multiply(bigDecimal2);
        } catch (Exception e) {
            LOGGER.error("mult : req o1 :[{}],o2:{}", o1, o2, e);
            return null;
        }
    }

    /**
     * @param o1
     * @param o2
     * @param scale        穿空的默认保存位数是0位
     * @param roundingMode 传空的默认舍去方式是四舍五入
     * @return
     */
    public static BigDecimal divid(Object o1, Object o2, Integer scale, Integer roundingMode) {
        try {
            Assert.notNull(o1, "传入参数不能为空");
            Assert.notNull(o2, "传入参数不能为空");
            BigDecimal bigDecimal1 = new BigDecimal(String.valueOf(o1));
            BigDecimal bigDecimal2 = new BigDecimal(String.valueOf(o2));
            if (null == scale) {
                scale = 0;
            }
            if (null == roundingMode) {
                roundingMode = BigDecimal.ROUND_HALF_UP;
            }
            return bigDecimal1.divide(bigDecimal2, scale, roundingMode);
        } catch (Exception e) {
            LOGGER.error("divid : req o1 :[{}],o2:{}", o1, o2, e);
            return null;
        }
    }
}
