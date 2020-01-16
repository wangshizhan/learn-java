package com.hnanet._0_init.initEn.inter;

import com.hnanet._0_init.initEn.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class InitEnInterTest {
    @Test
    public void initEnTest(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("spring/init/bean-initEn-inter-config.xml");
        User user = (User) ac.getBean("user");
        System.out.println(user);
    }
}