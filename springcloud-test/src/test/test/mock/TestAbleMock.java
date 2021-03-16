package mock;

/**
 * @author tong.luo
 * @description TestAbleMock
 * @date 2021/1/27 15:16
 */
public class TestAbleMock {
    public String commonMethod() {
        return "http://www".trim() + "." + "jjavastack.cn_".substring(1) + "www.baidu.com".startsWith("com");
    }

    public String memMehod(String ss) {
        return innerMethod() + staticMethod() + ss;
    }

    private String innerMethod() {
        return "inner";
    }

    private static String staticMethod() {
        return "STATIC";
    }
}
