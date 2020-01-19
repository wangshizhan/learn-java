package com.hnanet.aop.custom;

import org.springframework.cglib.proxy.Proxy;

import java.lang.reflect.Method;

public class SimpleAOP {
    public static Object getProxy(Object bean, Advice advice) {
        return Proxy.newProxyInstance(SimpleAOP.class.getClassLoader(),
                bean.getClass().getInterfaces(), advice);
    }
}