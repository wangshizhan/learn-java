package com.hnanet._1_aware.beanFactoryAware.spring;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AwareTest {
    @Test
    public void awareTest(){
        ApplicationContext context = new ClassPathXmlApplicationContext("spring/aware/bean-beanFactoryAware-config.xml");
        HelloFace helloFace = (HelloFace)context.getBean("helloFace");
        CustomBeanFactoryAware customBeanFactoryAware = (CustomBeanFactoryAware)context.getBean("customBeanFactoryAware");

        /**
         * 消除 if-else
         * 只需要传入不同HelloService的实现类的beanName
         * 就可以执行不同的业务逻辑
         */
        helloFace.sayHello("helloServiceEn");
        helloFace.sayHello("helloServiceCh");
    }
}
