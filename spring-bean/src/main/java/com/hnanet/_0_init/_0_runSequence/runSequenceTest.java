package com.hnanet._0_init._0_runSequence;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * CustomerBeanPostProcessor 实现 执行 BeanPostProcessor 的 postProcessBeforeInitialization 方法,beanName=user
 * CustomerBeanPostProcessor 实现 执行 BeanPostProcessor 的 postProcessAfterInitialization 方法,beanName=user
 *
 * 执行setBeanName:: Bean Name defined in context=customBeanAware
 * 执行setBeanClassLoader,ClassLoader Name = sun.misc.Launcher$AppClassLoader
 * 执行setBeanFactory,setBeanFactory:: CustomBeanAware bean singleton=true
 * 执行setEnvironment
 * 执行setResourceLoader:: Resource File Name=spring-beans.xml
 * 执行setApplicationEventPublisher
 * 执行setApplicationContext:: Bean Definition Names=[user, customBeanAware, customerBeanPostProcessor]
 * CustomerBeanPostProcessor 实现 执行 BeanPostProcessor 的 postProcessBeforeInitialization 方法,beanName=customBeanAware
 * CustomerBeanPostProcessor 实现 执行 BeanPostProcessor 的 postProcessAfterInitialization 方法,beanName=customBeanAware
 * password
 *
 */

public class runSequenceTest {
    @Test
    public void BeanTest(){
        String path = "spring/init/bean-runSequence-config.xml";
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(path);
        User user = ctx.getBean("user", User.class);
        System.out.println(user.getPassword());
        ctx.close();
    }
}
