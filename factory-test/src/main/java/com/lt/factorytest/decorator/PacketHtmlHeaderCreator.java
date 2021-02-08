package com.lt.factorytest.decorator;

/**
 * @author tong.luo
 * @description PacketHtmlHeaderCreator
 * @date 2021/2/8 17:08
 */
public class PacketHtmlHeaderCreator extends PacketDecorator {
    public PacketHtmlHeaderCreator(IPackageCreator iPackageCreator) {
        super(iPackageCreator);
    }

    /**
     * 用于处理内容逻辑
     *  将指定数据封装成html
     * @return
     */
    @Override
    public String handlerContent() {
        StringBuffer sb = new StringBuffer();
        sb.append("<html>");
        sb.append("<body>");
        sb.append(iPackageCreator.handlerContent());
        sb.append("</body>");
        sb.append("</html>");
        return sb.toString();
    }
}
