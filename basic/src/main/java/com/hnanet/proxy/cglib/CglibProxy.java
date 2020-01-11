package com.hnanet.proxy.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.lang.reflect.Method;

/**
 * 1、提供构造方法 Object newInstall() ，生成CGLIB的代理类，回调this
 * 2、Object intercept() 可以进行方法的增强，处理相关业务逻辑
 * 3、CGLIB 是通过 ASM 来操作字节码生成类
 */

public class CglibProxy implements MethodInterceptor {

    private final static Logger logger = LoggerFactory.getLogger(CglibProxy.class);

    public Object newInstall(Object object) {
        return Enhancer.create(object.getClass(), this);
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        logger.debug("我被CglibProxy代理了");
        return methodProxy.invokeSuper(o, objects);
    }

}