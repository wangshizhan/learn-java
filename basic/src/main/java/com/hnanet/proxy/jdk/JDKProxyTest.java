package com.hnanet.proxy.jdk;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.ProxyGenerator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Modifier;
import java.lang.reflect.Proxy;

public class JDKProxyTest {

    /**
     * JDK代理机制：委托机制，代理类和目标类都实现了同样的接口，InvocationHandler 持有目标类，代理类委托 InvocationHandler 去调用目标类的原始方法
     * 适用于：目标类是接口类
     * 回调方式：反射
     * 效率：效率瓶颈在反射调用稍慢
     */

    @Test
    public void test_proxy_jdk() throws Exception {
        // 指明一个类加载器，要操作class文件，怎么少得了类加载器呢
        ClassLoader classLoader = JDKProxyTest.class.getClassLoader();
        // 为代理对象指定要是实现哪些接口，这里我们要为UserServiceImpl这个目标对象创建动态代理，所以需要为代理对象指定实现UserService接口
        Class[] classes = new Class[]{IUserService.class};
        // 初始化一个InvocationHandler，并初始化InvocationHandler中的目标对象
        InvocationHandler invocationHandler = new JDKInvocationHandler(new UserService());
        // 创建动态代理
        IUserService userService = (IUserService) Proxy.newProxyInstance(classLoader, classes, invocationHandler);
        // 执行代理对象的方法，通过观察控制台的结果，判断我们是否对目标对象(UserServiceImpl)的方法进行了增强
        userService.insert();
    }

    @Test
    public void test3() throws IOException {
        String proxyName = "com.hnanet.proxy.jdk.$Proxy0";
        Class[] interfaces = new Class[]{UserService.class};
        int accessFlags = Modifier.PUBLIC;
        byte[] proxyClassFile = ProxyGenerator.generateProxyClass(proxyName, interfaces, accessFlags);
        // 将字节数组写出到磁盘
        File file = new File("$Proxy0.class");
        OutputStream outputStream = new FileOutputStream(file);
        outputStream.write(proxyClassFile);
    }

    public static void main(String[] args){
        // 让代理对象的class文件写入到磁盘
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        // 指明一个类加载器，要操作class文件，怎么少得了类加载器呢
        ClassLoader classLoader = JDKProxyTest.class.getClassLoader();
        // 为代理对象指定要是实现哪些接口，这里我们要为UserServiceImpl这个目标对象创建动态代理，所以需要为代理对象指定实现UserService接口
        Class[] classes = new Class[]{IUserService.class};
        // 初始化一个InvocationHandler，并初始化InvocationHandler中的目标对象
        InvocationHandler invocationHandler = new JDKInvocationHandler(new UserService());
        // 创建动态代理
        IUserService userService = (IUserService) Proxy.newProxyInstance(classLoader, classes, invocationHandler);
        // 执行代理对象的方法，通过观察控制台的结果，判断我们是否对目标对象(UserServiceImpl)的方法进行了增强
        userService.insert();
    }
}
