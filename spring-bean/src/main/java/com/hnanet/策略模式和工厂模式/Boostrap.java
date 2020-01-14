package com.hnanet.策略模式和工厂模式;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.hnanet.策略模式和工厂模式")
public class Boostrap {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Boostrap.class);
        context.getBean(EntStrategyHolder.class).print();
    }
}
