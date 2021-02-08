package com.lt.factorytest.decorator;

/**
 * @author tong.luo
 * @description PacketBodyCreator
 * @date 2021/2/8 17:06
 */
public class PacketBodyCreator implements IPackageCreator {
    /**
     * 用于处理内容逻辑
     *
     * @return
     */
    @Override
    public String handlerContent() {
        //构造核心数据 但不包括格式
        return "Content of Packet";
    }
}
