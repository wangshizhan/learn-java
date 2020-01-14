package com.hnanet.proxy.cglib;

import net.sf.cglib.proxy.*;
import org.junit.Test;

import java.lang.reflect.Method;

public class CglibProxyTest {

    @Test
    public void test_proxy_cglib() {
        //创建一个Enhancer对象
        Enhancer enchaner = new Enhancer();
        //设置被代理的类
        enchaner.setSuperclass(Student.class);
        //创建一个回调接口
        Callback interceptor = new MethodInterceptor() {

            @Override
            public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy)
                    throws Throwable {
                System.out.println("原方法名是 ： " + method.getName());
                System.out.println("原方法声明的类为 " + method.getDeclaringClass());
                System.out.println("我是 " + (String) proxy.invokeSuper(obj, args));
                System.out.println("我调用结束了");
                return null;
            }
        };
        enchaner.setCallback(interceptor);
        Student student = (Student) enchaner.create();
        student.getStuName();
    }

    public static void main(String[] args) {
        //创建一个Enhancer对象
        Enhancer enchaner = new Enhancer();
        //设置被代理的类
        enchaner.setSuperclass(Student.class);

        //创建一个回调接口
        Callback interceptor = new MethodInterceptor() {

            @Override
            public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy)
                    throws Throwable {
                System.out.println("原方法名是 ： " + method.getName());
                System.out.println("原方法声明的类为 " + method.getDeclaringClass());
                System.out.println("我是 " + (String) proxy.invokeSuper(obj, args));
                System.out.println("我调用结束了");
                return proxy.invokeSuper(obj, args);
            }
        };
        CallbackFilter callbackFilter;
        callbackFilter = new CallbackFilter() {
            @Override
            public int accept(Method method) {
                int flag = 0;
                if ("getStuName".equals(method.getName())) {
                    System.err.println("我将 getStuName() 方法过滤掉了，不对该方法进行拦截");
                    return 1;
                }
                return 0;
            }
        };
        // NoOp.INSTANCE：这个 NoOp 表示 no operator ，即什么操作也不做，代理类直接调用被代理的方法不进行拦截。
        // getStuName 对应的 CallbackFilter 中定义的索引1，在 Callback[] 数组中使用的过滤为 NoOp ，因此直接执行了被代理方法。
        // getRename 对应 CallbackFilter 中定义的索引0，在 Callback[] 数组中使用的过滤为 interceptor ，因此执行了方法拦截器进行拦截。
        Callback[] callbacks = new Callback[] { interceptor, NoOp.INSTANCE };
        enchaner.setCallbackFilter(callbackFilter);
        enchaner.setCallbacks(callbacks);

        Student student = (Student) enchaner.create();
        System.out.println(student.getStuName());
        System.out.println(student.getRename());
    }
}
