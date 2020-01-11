package com.hnanet.proxy.jdk;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * 定义一个代理类获取的服务
 *
 */
public class JDKProxy {

    private static final Logger logger = LoggerFactory.getLogger(JDKProxy.class);

    public static <T> T getProxy(Class<T> interfaceClass) throws Exception {
        logger.debug("JDKProxy 代理类获取的服务");
        InvocationHandler handler = new JDKInvocationHandler();
        ClassLoader classLoader = ClassLoaderUtils.getCurrentClassLoader();

        logger.debug("实际生成代理类");
        /**
         * 实际生成代理类
         * 1、Class<?> cl = getProxyClass0(loader, intfs); 查找或生成指定的代理类
         * 2、proxyClassCache.get(loader, interfaces); 代理类的缓存中获取
         * 3、subKeyFactory.apply(key, parameter)      继续下一层
         * 4、byte[] proxyClassFile = ProxyGenerator.generateProxyClass(proxyName, interfaces, accessFlags); 生成代理类的字节码
         */
        T result = (T) Proxy.newProxyInstance(classLoader, new Class[]{interfaceClass}, handler);
        return result;
    }
}
