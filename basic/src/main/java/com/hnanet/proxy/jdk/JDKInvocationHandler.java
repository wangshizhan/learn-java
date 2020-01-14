package com.hnanet.proxy.jdk;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 *  代理类反射调用
 *  1、JDK 动态代理需要实现 InvocationHandler，用于方法增强｛监控、执行其他业务逻辑、远程调用等｝
 *  2、如果有需要额外的参数可以提供构造方法
 *
 *  自定义的InvocationHandler中，定义了一个属性：target，
 *  定义这个属性的目的是为了在InvocationHandler中持有对目标对象的引用，target属性的初始化是在构造器中进行初始化的。
 *
 */

public class JDKInvocationHandler implements InvocationHandler {

    // 持有目标对象
    private Object target;

    public JDKInvocationHandler(Object target){
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("JDKInvocationHandler.invoke() "+ "JDK代理: " + method.getName() + " 方法");
        // 通过反射调用目标对象的方法
        return method.invoke(target,args);
    }

}
