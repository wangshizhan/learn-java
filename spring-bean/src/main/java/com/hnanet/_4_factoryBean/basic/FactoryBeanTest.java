package com.hnanet._4_factoryBean.basic;

import org.junit.Test;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class FactoryBeanTest {
    @Test
    public void testBean() throws Exception{
        ApplicationContext ac = new ClassPathXmlApplicationContext("spring/factory/bean-config.xml");
        Product pro1 = (Product)ac.getBean("productFactoryBean");
        System.out.println(pro1);

        // 得到 FactoryBean 自身对象
        FactoryBean<Product> pro2 = (ProductFactoryBean) ac.getBean("&productFactoryBean");
        System.out.println(pro2.getObject());
    }
}
