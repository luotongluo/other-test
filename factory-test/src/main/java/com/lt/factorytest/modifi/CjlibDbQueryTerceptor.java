package com.lt.factorytest.modifi;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author tong.luo
 * @description CjlibDbQueryTerceptor
 * @date 2021/2/8 11:09
 */
public class CjlibDbQueryTerceptor implements MethodInterceptor {
    IdbQuery idbQuery = null;

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        if (null == idbQuery) {
            idbQuery = new IdbQuery();
        }
        return idbQuery.request();
    }

    public static IdbQuery createCglibProxy() {
        Enhancer enhancer = new Enhancer();
        //指定切入器，定义代理类逻辑
        enhancer.setCallback(new CjlibDbQueryTerceptor());
        //生成代理类实例
        enhancer.setInterfaces(new Class[]{IdbQuery.class});
        return (IdbQuery) enhancer.create();

    }
}
