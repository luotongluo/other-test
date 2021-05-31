package com.lt.dailytest.utils;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Joiner;
import com.lt.dailytest.exception.BusinessException;
import com.lt.dailytest.othertest.validate.TestBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.helpers.SubstituteLogger;
import org.springframework.util.CollectionUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.groups.Default;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author tong.luo
 * @description ValidatorUtil
 * @date 2021/4/26 19:14
 */
public class ValidatorUtil {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private static Validator validator = Validation.buildDefaultValidatorFactory()
            .getValidator();
    private static final Validator VALIDATOR;

    static {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        VALIDATOR = validatorFactory.getValidator();
    }

    public static <T> Map<String, StringBuffer> validate(T obj) {
        Map<String, StringBuffer> errorMap = null;
        Set<ConstraintViolation<T>> set = VALIDATOR.validate(obj, Default.class);
        // 输出错误消息
        set.stream().map(v -> v.getPropertyPath() + " " + v.getMessage() + ": " + v.getInvalidValue())
                .forEach(System.out::println);
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

    /**
     * JSR303校验方法
     *
     * @param target 目标校验对象
     * @param groups 校验组，如果没有可不传
     * @param <T>    形式类型参数
     * @throws BusinessException 校验不通过会抛出此异常，异常信息包含了非法数据信息
     */
    public static <T> void validate(T target, Class<?>... groups) {
        Set<ConstraintViolation<T>> validate = VALIDATOR.validate(target, groups);
        List<String> result = new ArrayList<>();
        for (ConstraintViolation<T> constraintViolation : validate) {
            result.add(constraintViolation.getMessage());
        }
        if (!CollectionUtils.isEmpty(result)) {
            throw new BusinessException(Joiner.on("|").join(result));
        }
    }

    /**
     * @param target 目标对象
     * @param groups 校验组
     * @param <T>
     * @return Set<String> 结果集
     */
    public static <T> String validateModel(T target, Class<?>... groups) {
        Set<ConstraintViolation<T>> result = VALIDATOR.validate(target, groups);
        StringBuffer massage = new StringBuffer();
        if (result != null && result.size() > 0) {
            int count = 0;
            for (ConstraintViolation<T> constraintViolation : result) {
                if (count != result.size() - 1) {
                    massage = massage.append(constraintViolation.getPropertyPath() + ":" + constraintViolation.getMessage())
                            .append("|");
                } else {
                    massage = massage.append(constraintViolation.getPropertyPath() + ":" + constraintViolation.getMessage());
                }
                count++;
            }
        }
        return massage.toString();
    }

    /**
     * 校验目标类上带有JSR303校验规则的属性
     *
     * @param target 校验目标类
     * @param groups 分组
     * @return 校验不符合提示信息
     */
    public static List<String> validates(Object target, Class<?>... groups) {
        Set<ConstraintViolation<Object>> validate = VALIDATOR.validate(target, groups);
        List<String> collect = validate.stream().map(ConstraintViolation::getMessage).collect(Collectors.toList());
        return collect;
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        TestBean testBean = new TestBean();
        List<String> validates = validates(testBean);
        System.out.println(System.currentTimeMillis() - start);
        System.out.println(JSON.toJSON(validates));
    }
}
