package com.hnanet.aop.custom;

import org.junit.Test;
import org.springframework.beans.factory.xml.XmlBeanFactory;


/**
 *
 * MethodInvocation 接口  // 实现类包含了切面逻辑，如上面的 logMethodInvocation
 * Advice 接口        // 继承了 InvocationHandler 接口
 * BeforeAdvice 类    // 实现了 Advice 接口，是一个前置通知
 * SimpleAOP 类       // 生成代理类
 * SimpleAOPTest      // SimpleAOP 从测试类
 * HelloService 接口   // 目标对象接口
 * HelloServiceImpl   // 目标对象
 *
 */

public class SimpleAOPTest {
    @Test
    public void getProxy() throws Exception {
        // 1. 创建一个 MethodInvocation 实现类
        MethodInvocation logTask = () -> System.out.println("log task start");
        HelloService helloService = new HelloServiceImpl();

        // 2. 创建一个 Advice
        Advice beforeAdvice = new BeforeAdvice(helloService, logTask);

        // 3. 为目标对象生成代理
        HelloService helloServiceImplProxy = (HelloService) SimpleAOP.getProxy(helloService,beforeAdvice);
        helloServiceImplProxy.sayHelloWorld();
    }
}