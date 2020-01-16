package com.hnanet._0_init._0_runSequence;

import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessorAdapter;

import java.beans.PropertyDescriptor;

public class CustomInstantiationAwareBeanPostProcessorAdapter extends InstantiationAwareBeanPostProcessorAdapter {

    public CustomInstantiationAwareBeanPostProcessorAdapter() {
        super();
        System.out.println("InstantiationAwareBeanPostProcessorAdapter 实现 构造方法");
    }

    /**
     * 接口方法、实例化Bean之前调用
     * @param beanClass
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessBeforeInstantiation(Class beanClass,String beanName) throws BeansException {
        System.out.println("InstantiationAwareBeanPostProcessor 调用 postProcessBeforeInstantiation 方法");
        return null;
    }

    /**
     * 接口方法、实例化Bean之后调用
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName)throws BeansException {
        System.out.println("InstantiationAwareBeanPostProcessor 调用 postProcessAfterInitialization 方法 Person.phone=1102");
        return bean;
    }

    /**
     * 接口方法、设置某个属性时调用
     * @param pvs
     * @param pds
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public PropertyValues postProcessPropertyValues(PropertyValues pvs, PropertyDescriptor[] pds, Object bean, String beanName)throws BeansException {
        System.out.println("InstantiationAwareBeanPostProcessor 调用 postProcessPropertyValues 方法");
        return pvs;
    }
}