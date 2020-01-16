package com.hnanet._0_init.deathEn.inter;

import com.hnanet._0_init.deathEn.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class InitEnTest {
    @Test
    public void initEnTest() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("spring/init/bean-deathEn-inter-config.xml");
        User user = (User) ac.getBean("user");
        System.out.println(user);
        ((ClassPathXmlApplicationContext)ac).registerShutdownHook();
    }
}