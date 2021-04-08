package com.lt.factorytest.decorator;

/**
 * @author tong.luo
 * @description PacketHttpHeaderCreator
 * @date 2021/2/8 17:11
 */
public class PacketHttpHeaderCreator extends PacketDecorator {
    public PacketHttpHeaderCreator(IPackageCreator iPackageCreator) {
        super(iPackageCreator);
    }

    /**
     * 用于处理内容逻辑
     * 给指定数据添加头部信息
     *
     * @return
     */
    @Override
    public String handlerContent() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Cache-Controller:no-cache\n");
        stringBuffer.append("Date:Mon,31Dec201204:25:57GMT\n");
        stringBuffer.append(iPackageCreator.handlerContent());

        return stringBuffer.toString();
    }
}
