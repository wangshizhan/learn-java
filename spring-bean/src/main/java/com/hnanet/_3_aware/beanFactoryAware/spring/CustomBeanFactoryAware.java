package com.hnanet._3_aware.beanFactoryAware.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.stereotype.Service;

@Service
public class CustomBeanFactoryAware implements BeanFactoryAware {

    private BeanFactory beanFactory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory=beanFactory;
    }
    /**
     * 提供一个execute 方法来实现不同业务实现类的调度器方案。
     * @param beanName
     */
    public void execute(String beanName){
        HelloService helloService=(HelloService) beanFactory.getBean(beanName);
        helloService.sayHello(beanName);
    }

}
