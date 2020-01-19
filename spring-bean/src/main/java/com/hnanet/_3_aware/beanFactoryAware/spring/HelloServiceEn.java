package com.hnanet._3_aware.beanFactoryAware.spring;

import org.springframework.stereotype.Component;

@Component
public class HelloServiceEn implements HelloService{
    @Override
    public void sayHello(String beanName) {
        System.out.println("Hello "+beanName);
    }
}
