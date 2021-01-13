package test;

import bean.TestBeanVo;
import com.alibaba.fastjson.JSON;

import java.util.Date;

/**
 * @author tong.luo
 * @description FinalTest
 * @date 2021/1/11 10:35
 */
public class FinalTest {
    public static void main(String[] args) {
        TestBeanVo testBeanVo = testFinal();
        System.out.println(JSON.toJSONString(testBeanVo));
        testBeanVo.setName("123");
        System.out.println(JSON.toJSONString(testBeanVo));
    }

    private static TestBeanVo testFinal() {
        final TestBeanVo testBeanVo = new TestBeanVo("123", 44, "on", new Date());
        testBeanVo.setAge(123);
        testBeanVo.setCreateTime(new Date());
        testBeanVo.setName("name");
        testBeanVo.setOtherName("othname");
        return testBeanVo;
    }
}
