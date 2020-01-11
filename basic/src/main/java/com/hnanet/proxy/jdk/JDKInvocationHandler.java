package com.hnanet.proxy.jdk;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * JDK 动态代理需要实现 InvocationHandler，用于方法增强｛监控、执行其他业务逻辑、远程调用等｝
 * 如果有需要额外的参数可以提供构造方法
 */

public class JDKInvocationHandler implements InvocationHandler {

    private static final Logger logger = LoggerFactory.getLogger(JDKInvocationHandler.class);

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //logger.debug("JDK代理: "+method.getName());
        System.out.println(("JDK代理: "+method.getName()));
        return method.invoke("我被JDK代理了");
    }

}
