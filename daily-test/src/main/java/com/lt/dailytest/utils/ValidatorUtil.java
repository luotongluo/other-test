package com.lt.dailytest.utils;

import com.alibaba.fastjson.JSON;
import com.lt.dailytest.validate.TestBean;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.groups.Default;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author tong.luo
 * @description ValidatorUtil
 * @date 2021/4/26 19:14
 */
public class ValidatorUtil {
    private static Validator validator = Validation.buildDefaultValidatorFactory()
            .getValidator();

    public static <T> Map<String, StringBuffer> validate(T obj) {
        Map<String, StringBuffer> errorMap = null;
        Set<ConstraintViolation<T>> set = validator.validate(obj, Default.class);
        if (set != null && set.size() > 0) {
            errorMap = new HashMap();
            String property = null;
            for (ConstraintViolation<T> cv : set) {
                //这里循环获取错误信息，可以自定义格式
                property = cv.getPropertyPath().toString();
                if (errorMap.get(property) != null) {
                    errorMap.get(property).append("," + cv.getMessage());
                } else {
                    StringBuffer sb = new StringBuffer();
                    sb.append(cv.getMessage());
                    errorMap.put(property, sb);
                }
            }
        }
        return errorMap;
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        TestBean testBean = new TestBean();
        Map<String, StringBuffer> validate = validate(testBean);
        System.out.println(System.currentTimeMillis() -start);
        System.out.println(JSON.toJSON(validate));
    }
}
