package com.hnanet.schema;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class XMLTest {
    @Test
    public void Tests(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean-defineXMLSchema-spring-config.xml");
        Hero hero = (Hero) ac.getBean(Hero.class.getName());
        System.out.println("name: " + hero.getName() + " age: " + hero.getAge());
    }
}
