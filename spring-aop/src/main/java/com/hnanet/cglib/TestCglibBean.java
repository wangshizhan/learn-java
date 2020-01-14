package com.hnanet.cglib;

import org.junit.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class TestCglibBean {
    @Test
    public void testBean(){
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("bean-aop-cglib-spring-config.xml");
        MyTestBean bean1 = (MyTestBean) context.getBean("myTestBean1");
        MyTestBean bean2 = (MyTestBean) context.getBean("myTestBean2");
        bean1.getUserBean().showMe();
        bean2.getUserBean().showMe();
    }
}
