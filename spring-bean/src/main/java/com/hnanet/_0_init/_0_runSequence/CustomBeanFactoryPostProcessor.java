package com.hnanet._0_init._0_runSequence;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

public class CustomBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    public CustomBeanFactoryPostProcessor(){
        super();
        System.out.println("BeanFactoryPostProcessor 实现 构造方法");
    }
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("BeanFactoryPostProcessor 实现 执行 postProcessBeanFactory 方法");
    }
}
