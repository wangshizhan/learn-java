package com.hnanet._0_init.deathEn.method;

import com.hnanet._0_init.deathEn.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DeathEnTest {
    @Test
    public void deathEnTest(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("spring/init/bean-deathEn-method-config.xml");
        User user = (User) ac.getBean("user");
        System.out.println(user);
    }
}