package com.hnanet.aop;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.util.Date;

@Configuration
@EnableAspectJAutoProxy(proxyTargetClass=false)
@ComponentScan("com.hnanet.aop")
public class Boostrap {

    public static void main(String[] args) {
        /**
         * 若 @EnableAspectJAutoProxy(proxyTargetClass=false)，并实现了接口的话，使用的是 JDK 代理，而不实现接口的话用的是 CGLIB 代理。
         * 若 @EnableAspectJAutoProxy(proxyTargetClass=false)，则强制使用 CGLIB 代理。
         */
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Boostrap.class);
        TestSvc svc = (TestSvc) context.getBean("testSvc");
        svc.process();
        System.out.println("==================");
        DateSvc dateSvc = (DateSvc) context.getBean("dateSvc");
        dateSvc.printDate(new Date());
    }
}
