package com.hnanet.targetSource;

import org.junit.Test;
import org.springframework.aop.TargetSource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TargetSourceTest {
    @Test
    public void targetSourceTest() throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("/images/targetSource/bean-targetSource-config.xml");
        TargetSource targetSource = (TargetSource) context.getBean("targetSource");
        for (int i = 0; i < 10; i++) {
            Apple apple = (Apple) targetSource.getTarget();
            apple.eat();
        }
    }
}
