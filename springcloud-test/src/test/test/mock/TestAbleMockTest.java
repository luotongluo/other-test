package mock;

import com.alibaba.testable.core.annotation.MockMethod;
import com.alibaba.testable.core.annotation.TestableMock;
import org.junit.jupiter.api.Test;

import static com.alibaba.testable.core.matcher.InvokeVerifier.verify;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author tong.luo
 * @description TestAbleMockTest
 * @date 2021/1/27 15:20
 */
public class TestAbleMockTest {
    private TestAbleMock testAbleMock = new TestAbleMock();

    @MockMethod(targetClass = String.class)
    private String trim(){
        return "123123 ";
    }
    @MockMethod(targetClass = String.class, targetMethod = "substring")
    private String substr(int i) {
        return "javastack.cn_";
    }
    @MockMethod(targetClass = String.class)
    private boolean startsWith(String website) {
        return false;
    }
    /**
     * Mock 成员方法
     * @param text
     * @return
     */
    @MockMethod(targetClass = TestAbleMock.class)
    private String innerMethod(String text) {
        return "mock_" + text;
    }
    /**
     * Mock 静态方法
     * @return
     */
    @MockMethod(targetClass = TestableMock.class)
    private String staticMethod() {
        return "_MOCK_JAVASTACK";
    }

    @Test
    void commonMethodTest() {
        assertEquals("http://www.javastack.cn_false", testAbleMock.commonMethod());
        verify("trim").withTimes(1);
        verify("substr").withTimes(1);
        verify("startsWith").withTimes(1);
    }

    @Test
    void memberMethodTest() {
//        assertEquals("innerSTATIChello", testAbleMock.memMehod("hello"));
        verify("innerMethod").withTimes(1);
        verify("staticMethod").withTimes(1);
        verify("innerMethod").with("hello");
        verify("staticMethod").with();
    }

}
