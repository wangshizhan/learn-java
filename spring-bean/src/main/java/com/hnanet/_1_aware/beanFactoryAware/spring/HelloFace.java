package com.hnanet._1_aware.beanFactoryAware.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 这个类的作用就是持有一个 BeanFactoryAware 的实例对象
 * 然后通过 HelloFacade 实例对象的方法来屏蔽底层 BeanFactoryAware 实例的实现细节
 */

@Component
public class HelloFace {
    @Autowired
    private CustomBeanFactoryAware customBeanFactoryAware;

    /**
     * 调用 customBeanFactoryAware 的 execute方法
     * @param beanName
     */
    public void sayHello(String beanName){
        customBeanFactoryAware.execute(beanName);
    }
}
