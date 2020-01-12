package com.hnanet.springBean.interfaceMethod;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class MyBeanPostProcessor implements BeanPostProcessor {

    public MyBeanPostProcessor() {
        super();
        System.out.println("这是BeanPostProcessor实现类构造器！！");
        // TODO Auto-generated constructor stub
    }

    @Override
    public Object postProcessAfterInitialization(Object arg0, String arg1)throws BeansException {
        System.out.println("BeanPostProcessor接口方法postProcessAfterInitialization对属性进行更改！person.phone=1101");
        if(arg0 instanceof Person){
            ((Person) arg0).setPhone("1101");
        }
        return arg0;
    }

    @Override
    public Object postProcessBeforeInitialization(Object arg0, String arg1)throws BeansException {
        System.out.println("BeanPostProcessor接口方法postProcessBeforeInitialization对属性进行更改！person.phone=1100");
        if(arg0 instanceof Person){
            ((Person) arg0).setPhone("1100");
        }
        return arg0;
    }
}
