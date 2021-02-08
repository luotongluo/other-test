package com.lt.factorytest.decorator;

/**
 * @author tong.luo
 * @description IPackageCreator
 * @date 2021/2/8 17:05
 */
public interface IPackageCreator {
    /**
     * 用于处理内容逻辑
     * @return
     */
    public String handlerContent();
}
