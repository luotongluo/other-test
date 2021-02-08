package com.lt.factorytest.decorator;

/**
 * @author tong.luo
 * @description PacketDecorator
 * @date 2021/2/8 17:07
 */
public abstract class PacketDecorator implements IPackageCreator {

    IPackageCreator iPackageCreator;

    public PacketDecorator(IPackageCreator iPackageCreator) {
        this.iPackageCreator = iPackageCreator;
    }
}
