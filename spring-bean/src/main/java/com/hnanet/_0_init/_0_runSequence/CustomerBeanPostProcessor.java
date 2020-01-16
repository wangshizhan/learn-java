package com.hnanet._0_init._0_runSequence;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * 可以针对容器中的所有Bean，或者某些Bean定制初始化过程，只需提供一个实现BeanPostProcessor接口的类即可。
 * postProcessBeforeInitialization() 方法会在容器中的Bean初始化之前执行
 * postProcessAfterInitialization() 方法在容器中的Bean初始化之后执行。
 */
public class CustomerBeanPostProcessor implements BeanPostProcessor {
    public CustomerBeanPostProcessor(){
        super();
        System.out.println("BeanPostProcessor 实现 构造方法");
    }
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("CustomerBeanPostProcessor 实现 执行 BeanPostProcessor 的 postProcessBeforeInitialization 方法,beanName=" + beanName);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("CustomerBeanPostProcessor 实现 执行 BeanPostProcessor 的 postProcessAfterInitialization 方法,beanName=" + beanName);
        return bean;
    }
}
