package test;

import bean.TestBeanVo;
import com.alibaba.fastjson.JSON;
import com.google.gson.GsonBuilder;
import enums.TimeEnums;
import org.apache.commons.lang.StringUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author tong.luo
 * @description JsonTest
 * @date 2021/1/4 14:51
 */
public class JsonTest {
    public static void main(String[] args) throws Exception {
//        TestBeanVo testBeanVo = new TestBeanVo("123", 22, "33", new Date());
//        String jsonString = JSON.toJSONString(testBeanVo);
//        String jsonGson = new GsonBuilder().setDateFormat(TimeEnums.YYYYMMDDHHMMSS).create().toJson(testBeanVo);
//
//        System.out.println(jsonGson);
//        System.out.println(jsonString);
//        int hour = 17472;
//        double day = hour / 24D;
//        System.out.println(day);
        String dateBeg = "2020-12-01";
//        String replaceAll = dateBeg.replaceAll("-", "");
//        if(StringUtils.isNotBlank(replaceAll)){
//            Integer integer = Integer.valueOf(replaceAll);
//            System.out.println(integer);
//        }
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
//        Date parse = dateFormat.parse(dateBeg);
//        System.out.println(parse);


//        Date date = new Date();
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        System.out.println(sdf.format(date));
//        sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        System.out.println(sdf.format(date));
//        sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
//        sdf = new SimpleDateFormat("yyyyMMdd");
//        String format = sdf.format(date);
//        System.out.println("format" + format);
//        System.out.println(sdf.format(date));

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(sdf.format(dateBeg));
    }
}
