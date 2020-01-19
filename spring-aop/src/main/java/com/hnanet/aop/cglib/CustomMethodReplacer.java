package com.hnanet.aop.cglib;

import org.springframework.beans.factory.support.MethodReplacer;

import java.lang.reflect.Method;

public class CustomMethodReplacer implements MethodReplacer {

    @Override
    public Object reimplement(Object o, Method method, Object[] objects) throws Throwable {
        System.out.println("Call CustomMethodReplacer.reimplement() method "+ method);

        // 可以直接 return null; 不用运行实际需要代理的代码
        return method.invoke(o.getClass().newInstance(), objects);
    }
}
