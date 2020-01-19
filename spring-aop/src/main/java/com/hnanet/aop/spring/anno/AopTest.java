package com.hnanet.aop.spring.anno;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.util.Date;

/**
 *
 * 可以用 javaConfig 方式，也可以使用这种方式
 * <!-- 引入注解扫描 -->
 * <!-- <context:component-scan base-package="com.hnanet.aop.spring.xml"/> -->
 * <!-- 开启aop -->
 * <!-- <aop:aspectj-autoproxy/> -->
 *
 */
@Configuration
@EnableAspectJAutoProxy(proxyTargetClass=false)
@ComponentScan("com.hnanet.aop.spring")
public class AopTest {

    /**
     * 若 @EnableAspectJAutoProxy(proxyTargetClass=false)，并实现了接口的话，使用的是 JDK 代理；默认使用 CGLIB 代理。
     * 若 @EnableAspectJAutoProxy(proxyTargetClass=true)，则强制使用 CGLIB 代理。
     */
    public static void main(String[] args) {

        /**
         * @EnableAspectJAutoProxy(proxyTargetClass=false) ,默认使用 CGLIB 代理
         */
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AopTest.class);
        CustomService service = (CustomService) context.getBean("customService");
        service.process();

        /**
         *  @EnableAspectJAutoProxy(proxyTargetClass=false) ,且实现接口，使用的是 JDK 代理
         */
        System.out.println("==================");
        CustomInterface dataService = (CustomInterface) context.getBean("dataService");
        dataService.service(new Date());
    }
}
