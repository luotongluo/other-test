package com.lt.dailytest;

import com.lt.dailytest.validate.TestBean;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DailyTestApplicationTests {

    @Test
    void contextLoads() {
        TestBean testBean = new TestBean();

    }

    public static void main(String[] args) {
        Integer a = new Integer(1234);
        Integer b = new Integer(1234);
        Integer d = 1234;
        System.out.println(a == b);
        System.out.println(a.equals(b));
    }
}
