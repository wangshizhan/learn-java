package com.hnanet._1_aware.beanFactoryAware.basic.strategyFactory;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.hnanet._1_aware.beanFactoryAware.basic.strategyFactory")
public class StrategyFactoryTest {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(StrategyFactoryTest.class);
        context.getBean(EntStrategyHolder.class).print();
    }
}
