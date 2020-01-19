package com.hnanet._2_beanPostProcessor.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class CustomBeanPostProcessor implements BeanPostProcessor {

    /**
     * 属性设置之后调用
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("==================== postProcessBeforeInitialization [" + beanName + "]========================");
        if(bean instanceof User){
            System.out.println("bean instanceof User 可以修改 User bean");
        }
        return bean;
    }


    /**
     * 是在Bean init方法之后触发的
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("==================== postProcessAfterInitialization [" + beanName + "]========================");
        return bean;
    }
}