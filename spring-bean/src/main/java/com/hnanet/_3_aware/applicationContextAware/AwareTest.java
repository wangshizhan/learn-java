package com.hnanet._3_aware.applicationContextAware;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AwareTest {
    @Test
    public void awareTestTest(){
        ApplicationContext context = new ClassPathXmlApplicationContext("spring/aware/bean-applicationContextAware-config.xml");
        //这里通过实现ApplicationContextAware接口的类来完成bean的获取
        CustomApplicationContextAware glmapperApplicationContext = (CustomApplicationContextAware)context.getBean("glmapperApplicationContext");

        ApplicationContext applicationContext = glmapperApplicationContext.getApplicationContext();

        CustomApplicationContextAware glmapperApplicationContext2 = (CustomApplicationContextAware) applicationContext.getBean("glmapperApplicationContext");

        glmapperApplicationContext2.sayHello();
    }
}
