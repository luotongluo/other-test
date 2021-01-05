package test;

import bean.ListBeanVo;
import bean.TestBeanVo;
import com.alibaba.fastjson.JSON;
import com.google.gson.GsonBuilder;
import enums.TimeEnums;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author tong.luo
 * @description EfficenctTestOfJson
 * @date 2021/1/5 16:24
 */
public class EfficenctTestOfJson {
    public static void main(String[] args) {
        List<TestBeanVo> testBeanVoList = new ArrayList<TestBeanVo>();
        TestBeanVo testBeanVo = new TestBeanVo("ne", 1, "123", new Date());
        TestBeanVo testBeanVo1 = new TestBeanVo("ne2", 3, "1234", new Date());
        testBeanVoList.add(testBeanVo1);
        testBeanVoList.add(testBeanVo);

        ListBeanVo listBeanVo = new ListBeanVo();
        listBeanVo.setTestBeanVoList(testBeanVoList);
        listBeanVo.setCreatedDate(new Date());

        long start1 = System.currentTimeMillis();
        String jsonString = JSON.toJSONString(testBeanVoList);
//        List<TestBeanVo> beanVo = JSON.parseArray(jsonString, TestBeanVo.class);
        long start2 = System.currentTimeMillis();
        String reqJson = new GsonBuilder().setDateFormat(TimeEnums.YYYYMMDDHHMMSS).create().toJson(testBeanVoList);
//        TestBeanVo testBeanVo2 = new GsonBuilder().create().fromJson(reqJson, TestBeanVo.class);
        long start3 = System.currentTimeMillis();

        String listjsonString = JSON.toJSONString(listBeanVo);
        ListBeanVo listBeanVos = JSON.parseObject(listjsonString, ListBeanVo.class);
        long start4 = System.currentTimeMillis();
        String listreqJson = new GsonBuilder().setDateFormat(TimeEnums.YYYYMMDDHHMMSS).create().toJson(listBeanVo);
        ListBeanVo listBeanVos1 = new GsonBuilder().create().fromJson(listreqJson, ListBeanVo.class);
        long start5 = System.currentTimeMillis();
        String json = JSON.toJSONString(testBeanVo);
        TestBeanVo listBeanVos3 = JSON.parseObject(json, TestBeanVo.class);
        long start6 = System.currentTimeMillis();
        String gson = new GsonBuilder().setDateFormat(TimeEnums.YYYYMMDDHHMMSS).create().toJson(testBeanVo);
        TestBeanVo listBeanVos14 = new GsonBuilder().create().fromJson(gson, TestBeanVo.class);

        long start7 = System.currentTimeMillis();

        System.out.println("json:" + json + " \n cost：" + (start6 - start5));
        System.out.println("gson:" + gson + " \n cost：" + (start7 - start6));
        System.out.println("Gson:" + reqJson + " \n cost：" + (start3 - start2));
        System.out.println("jsonString:" + jsonString + " \n cost：" + (start2 - start1));
        System.out.println("listjsonString:" + listjsonString + " \n cost：" + (start4 - start3));
        System.out.println("listGsonJson:" + listreqJson + " \n cost：" + (start5 - start4));


    }
}
