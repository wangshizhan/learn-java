package com.hnanet._6_schemaEn;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SchemaTest {
    @Test
    public void Tests(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("spring/schema/bean-config.xml");
        Hero hero = (Hero) ac.getBean(Hero.class.getName());
        System.out.println("name: " + hero.getName() + " age: " + hero.getAge());
    }
}
