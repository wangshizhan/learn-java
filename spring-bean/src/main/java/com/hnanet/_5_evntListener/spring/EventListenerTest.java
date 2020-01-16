package com.hnanet._5_evntListener.spring;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class EventListenerTest {
    @Test
    public void testEvent(){
        String path = "spring/eventListener/bean-config.xml";
        ApplicationContext ctx = new ClassPathXmlApplicationContext(path);
        UserRegisterController userRegisterController = (UserRegisterController) ctx.getBean("userRegisterController");
        userRegisterController.register(User.builder().Email("1.qq.com").password("123456").Phone("13910101010").username("wang").build());
    }
}