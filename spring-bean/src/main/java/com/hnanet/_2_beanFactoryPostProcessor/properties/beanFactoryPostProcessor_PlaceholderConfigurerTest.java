package com.hnanet._2_beanFactoryPostProcessor.properties;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class beanFactoryPostProcessor_PlaceholderConfigurerTest {
    @Test
    public void testP(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("spring/properties/bean-placeholderConfigurer-config.xml");
        Student student = (Student) ac.getBean("student");
        System.out.println(student);
    }
}