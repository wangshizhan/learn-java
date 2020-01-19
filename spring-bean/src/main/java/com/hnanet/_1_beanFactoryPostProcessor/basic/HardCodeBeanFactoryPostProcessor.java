package com.hnanet._1_beanFactoryPostProcessor.basic;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.core.Ordered;

public class HardCodeBeanFactoryPostProcessor implements BeanFactoryPostProcessor , Ordered {
    public HardCodeBeanFactoryPostProcessor() {
        super();
        System.out.println("这是 HardCodeBeanFactoryPostProcessor 实现类构造器！！");
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

        System.out.println("Hard Code BeanFactory Post Processor execute time HardCodeBeanFactoryPostProcessor#postProcessBeanFactory");
    }
    @Override
    public int getOrder() {
        return Integer.MAX_VALUE;
    }
}
