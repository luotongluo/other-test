package com.lt.dailytest.entity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author tong.luo
 * @description RetVo
 * @date 2021/8/23 17:25
 */
public class RetVo implements Serializable {
    private static final long serialVersionUID = -6230280650894949688L;
    BigDecimal bi;
    Double d1;

    public BigDecimal getBi() {
        return bi;
    }

    public void setBi(BigDecimal bi) {
        this.bi = bi;
    }

    public Double getD1() {
        return d1;
    }

    public void setD1(Double d1) {
        this.d1 = d1;
    }
}
