package com.lt.factorytest.modifi;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author tong.luo
 * @description JDKDbQueryHandler
 * @date 2021/2/8 11:03
 */
public class JDKDbQueryHandler implements InvocationHandler {
    IdbQuery real = null;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (null == real) {
            real = new IdbQuery();
        }
        return real.request();
    }

    public static IdbQuery createJdkProxy() {
        return (IdbQuery) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), new Class[]{IdbQuery.class}, new JDKDbQueryHandler());
    }
}
