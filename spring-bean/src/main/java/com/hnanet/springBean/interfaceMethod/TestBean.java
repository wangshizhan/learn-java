package com.hnanet.springBean.interfaceMethod;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 这是BeanFactoryPostProcessor实现类构造器！！
 * BeanFactoryPostProcessor调用postProcessBeanFactory方法，可以在这里 先定义Bean信息 person.phone=110
 * 这是BeanPostProcessor实现类构造器！！
 * 这是InstantiationAwareBeanPostProcessorAdapter实现类构造器！！
 * InstantiationAwareBeanPostProcessor调用postProcessBeforeInstantiation方法
 * 【构造器】调用 Persion 的构造器实例化
 * InstantiationAwareBeanPostProcessor调用postProcessPropertyValues方法
 * 【注入属性】注入属性address
 * 【注入属性】注入属性name
 * 【注入属性】注入属性phone
 * 【BeanNameAware接口】调用BeanNameAware.setBeanName()
 * 【BeanFactoryAware接口】调用BeanFactoryAware.setBeanFactory()
 * BeanPostProcessor接口方法postProcessBeforeInitialization对属性进行更改！person.phone=1100
 * 【注入属性】注入属性phone
 * 【InitializingBean接口】调用InitializingBean.afterPropertiesSet()
 * 【init-method】调用<bean>的init-method属性指定的初始化方法
 * BeanPostProcessor接口方法postProcessAfterInitialization对属性进行更改！person.phone=1101
 * 【注入属性】注入属性phone
 * InstantiationAwareBeanPostProcessor调用postProcessAfterInitialization方法 Person.phone=1102
 * 【注入属性】注入属性phone
 * 容器初始化成功
 * Person(name=张三, address=广州, phone=1102, beanFactory=org.springframework.beans.factory.support.DefaultListableBeanFactory@6a396c1e: defining beans [beanPostProcessor,instantiationAwareBeanPostProcessor,beanFactoryPostProcessor,person]; root of factory hierarchy, beanName=person)
 * 现在开始关闭容器！
 */
public class TestBean {
    @Test
    public void testBean(){
        System.out.println("现在开始初始化容器");

        ApplicationContext factory = new ClassPathXmlApplicationContext("bean-interfaceMethod-spring-config.xml");
        System.out.println("容器初始化成功");

        Person person = factory.getBean("person",Person.class);
        System.out.println(person);

        System.out.println("现在开始关闭容器！");
        ((ClassPathXmlApplicationContext)factory).registerShutdownHook();

    }
}
