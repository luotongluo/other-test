package com.lt.springcloudtest.atomic;

import java.io.Serializable;

/**
 * @author tong.luo
 * @description BackCard
 * 账户名和账户金额设置后就不能修改
 * @date 2021/1/20 16:42
 */
public class BackCard implements Serializable {
    private static final long serialVersionUID = 2526680240479566959L;
    /**
     * 账户名称
     */
    private final String accountName;
    /**
     * 账户金额
     */
    private final String money;

    public BackCard(String accountName, String money) {
        this.accountName = accountName;
        this.money = money;
    }

    public String getAccountName() {
        return accountName;
    }

    public String getMoney() {
        return money;
    }

    @Override
    public String toString() {
        return "BackCard{" +
                "accountName='" + accountName + '\'' +
                ", money='" + money + '\'' +
                '}';
    }
}
