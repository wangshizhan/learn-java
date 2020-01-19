package com.hnanet._2_beanPostProcessor.spring;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BeanPostProcessorTest {
    @Test
    public void beanPostProcessorTest(){
        AnnotationConfigApplicationContext acc = new AnnotationConfigApplicationContext("com.hnanet._3_beanPostProcessor.spring");
        acc.getBean(UserService.class).show();
        acc.close();
    }
}
