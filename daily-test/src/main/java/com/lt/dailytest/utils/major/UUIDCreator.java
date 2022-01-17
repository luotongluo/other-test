package com.lt.dailytest.utils.major;

import java.util.UUID;

/**
 * @author tong.luo
 * @description UUIDCreator
 * @date 2021/7/16 17:33
 */
public class UUIDCreator implements IMajorKey {
    /**
     * 取得生成的主键
     *
     * @return 主键字符串
     */
    @Override
    public String getMajorKey() {
        String uuid = UUID.randomUUID().toString().replace("-", "").toUpperCase();
        return uuid;
    }
}
