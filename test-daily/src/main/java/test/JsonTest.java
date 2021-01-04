package test;

import bean.TestBean;
import com.alibaba.fastjson.JSON;
import com.google.gson.GsonBuilder;
import enums.TimeEnums;

import java.util.Date;

/**
 * @author tong.luo
 * @description JsonTest
 * @date 2021/1/4 14:51
 */
public class JsonTest {
    public static void main(String[] args) {
        TestBean testBean = new TestBean("123", 22, "33",new Date());
        String jsonString = JSON.toJSONString(testBean);
        String jsonGson = new GsonBuilder().setDateFormat(TimeEnums.YYYYMMDDHHMMSS).create().toJson(testBean);

        System.out.println(jsonGson);
        System.out.println(jsonString);
    }
}
