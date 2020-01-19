package com.hnanet.aop.spring.xml;


import com.hnanet.aop.spring.xml.controller.UserController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AopTest {

    /**
     * 若 @EnableAspectJAutoProxy(proxyTargetClass=false)，并实现了接口的话，使用的是 JDK 代理；默认使用 CGLIB 代理。
     * 若 @EnableAspectJAutoProxy(proxyTargetClass=true)，则强制使用 CGLIB 代理。
     */
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("aop/bean-aop-config.xml");
        UserController userController = (UserController) context.getBean("userController");
        userController.playGame();
        userController.findGame("少林足球",11);
    }
}
