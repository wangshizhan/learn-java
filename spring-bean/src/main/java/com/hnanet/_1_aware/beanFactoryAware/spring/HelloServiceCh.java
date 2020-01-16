package com.hnanet._1_aware.beanFactoryAware.spring;

import org.springframework.stereotype.Component;

@Component
public class HelloServiceCh implements HelloService {
    @Override
    public void sayHello(String beanName) {
        System.out.println("你好 "+ beanName);
    }
}
