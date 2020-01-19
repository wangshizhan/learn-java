package com.hnanet.aop.cglib;

import org.junit.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class AopTest {
    @Test
    public void aopTest(){
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("aop/bean-cglib-config.xml");
        CustomBean bean1 = (CustomBean) context.getBean("customBean1");
        CustomBean bean2 = (CustomBean) context.getBean("customBean2");
        bean1.getUserBean().showMe();
        bean2.getUserBean().showMe();
    }
}
