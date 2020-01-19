package com.hnanet._1_beanFactoryPostProcessor.properties;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class beanFactoryPostProcessor_OverrideConfigurerTest {
    @Test
    public void testP(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("spring/properties/bean-overrideConfigurer-config.xml");
        Student student = (Student) ac.getBean("student");
        System.out.println(student);
    }
}