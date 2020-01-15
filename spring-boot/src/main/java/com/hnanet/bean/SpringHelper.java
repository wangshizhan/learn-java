package com.hnanet.bean;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

@Service
public class SpringHelper implements ApplicationContextAware {
    private static ApplicationContext context = null;

    public SpringHelper() {
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

    public static <T> T getBean(Class clazz) throws BeansException {
        return context.getBean((Class<T>) clazz);
    }

    public static <T> T getBean(String clazz) throws BeansException {
        return (T) context.getBean(clazz);
    }

    public static Object getBeanByName(String beanName) throws BeansException {
        return context.getBean(beanName);
    }
}

