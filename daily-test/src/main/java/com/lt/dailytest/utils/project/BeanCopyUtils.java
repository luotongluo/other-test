package com.lt.dailytest.utils.project;
import com.alibaba.fastjson.JSON;

import java.util.Date;

import com.lt.dailytest.bean.CopyBean1;
import com.lt.dailytest.bean.CopyBean2;
import net.sf.cglib.beans.BeanCopier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author tong.luo
 * @description BeanCopyUtils
 * @date 2021/6/24 13:50
 */
public class BeanCopyUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(BeanCopyUtils.class);

    /**
     * 缓存BeanCopier实例
     */
    private static final Map<String, BeanCopier> BEAN_COPIERS = new HashMap<String, BeanCopier>();

    /**
     * 属性拷贝
     *
     * @param srcObj    源类
     * @param targetObj 目标类
     */
    public static void copy(Object srcObj, Object targetObj) {
        String key = genKey(srcObj.getClass(), targetObj.getClass());
        BeanCopier copier = null;
        if (!BEAN_COPIERS.containsKey(key)) {
            copier = BeanCopier.create(srcObj.getClass(), targetObj.getClass(), false);
            BEAN_COPIERS.put(key, copier);
        } else {
            copier = BEAN_COPIERS.get(key);
        }
        copier.copy(srcObj, targetObj, null);
    }

    public static <T> T copy(Object obj, Class<T> clazz) {
        T newInstance = null;
        try {
            newInstance = clazz.newInstance();
            copy(obj, newInstance);
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return newInstance;
    }

    public static <T> List<T> copy(List<?> srcList, Class<T> invoiceBodyClazz, Class<?> detailsClazz) {
        List<T> respInvoices = new ArrayList<>();
        srcList.forEach(sl -> {
            T respInvoice = copy(sl, invoiceBodyClazz, detailsClazz);
            respInvoices.add(respInvoice);
        });
        return respInvoices;
    }

    public static <T> T copy(Object obj, Class<T> invoiceBodyClazz, Class<?> detailsClazz) {
        T invoiceBody = null;
        try {
            invoiceBody = invoiceBodyClazz.newInstance();
            copy(obj, invoiceBody);
            List<?> sourceDetails = (List<?>) obj.getClass().getMethod("getItemList").invoke(obj);
            if (!sourceDetails.isEmpty()) {
                List<?> targetDetails = copy(sourceDetails, detailsClazz);
                invoiceBody.getClass().getMethod("setItemList", List.class).invoke(invoiceBody, targetDetails);
            }
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
        }
        return invoiceBody;
    }

    public static <T> List<T> copy(List<?> srcList, Class<T> targetClazz) {
        List<T> targets = new ArrayList<>(srcList.size());
        T targetDetail = null;
        try {
            for (Object dl : srcList) {
                targetDetail = targetClazz.newInstance();

                copy(dl, targetDetail);
                targets.add(targetDetail);
            }
        } catch (InstantiationException | IllegalAccessException e) {
            LOGGER.error("外层工具类集合拷贝集合异常===>{}", e);
        }
        return targets;
    }

    /**
     * 生成缓存键
     *
     * @param srcClazz  源类
     * @param targetObj 目标类
     * @return 键值
     */
    private static String genKey(Class<?> srcClazz, Class<?> targetObj) {
        return srcClazz.getName() + targetObj.getName();
    }

    public static void main(String[] args) {
        CopyBean1 copyBean1 = new CopyBean1();
        copyBean1.setName("setName");
        copyBean1.setAge(0);
        copyBean1.setAnInt(0);
        copyBean1.setDate(new Date());
        ArrayList<String> strings = new ArrayList<>();
        strings.add("123");
        strings.add("222");
        copyBean1.setStringList(strings);
        CopyBean2 copyBean21 = new CopyBean2();
        copy(copyBean1, copyBean21);
        LOGGER.info("copyBean2:[{}]", JSON.toJSONString(copyBean21));
        LOGGER.info("copyBean2:[{}]", JSON.toJSONString(copy(copyBean1, CopyBean2.class)));
    }
}
