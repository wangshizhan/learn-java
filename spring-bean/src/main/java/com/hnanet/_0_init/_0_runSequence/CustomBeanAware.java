package com.hnanet._0_init._0_runSequence;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.*;
import org.springframework.context.annotation.ImportAware;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Arrays;

/**
 *
 * 在Bean的初始化中，使用Spring框架自身的一些对象来执行一些操作
 * 比如获取ServletContext的一些参数，获取ApplicaitionContext中的BeanDefinition的名字，获取Bean在容器中的名字等等
 *
 * 为了让Bean可以获取到框架自身的一些对象，Spring提供了一组名为*Aware的接口。
 * 均继承于org.springframework.beans.factory.Aware标记接口
 * 并提供一个将由Bean实现的set*方法,Spring通过基于setter的依赖注入方式使相应的对象可以被Bean使用
 *
 *
 * ApplicationContextAware：获得ApplicationContext对象,可以用来获取所有Bean definition的名字。
 * BeanFactoryAware：获得BeanFactory对象，可以用来检测Bean的作用域。
 * BeanNameAware：获得Bean在配置文件中定义的名字。
 * ResourceLoaderAware：获得ResourceLoader对象，可以获得classpath中某个文件。
 * ServletContextAware：在一个MVC应用中可以获取ServletContext对象，可以读取context中的参数。
 * ServletConfigAware：在一个MVC应用中可以获取ServletConfig对象，可以读取config中的参数。
 *
 */

public class CustomBeanAware implements ApplicationContextAware,
        ApplicationEventPublisherAware, BeanClassLoaderAware, BeanFactoryAware,
        BeanNameAware, EnvironmentAware, ImportAware, ResourceLoaderAware {
    public CustomBeanAware(){
        super();
        System.out.println("CustomBeanAware 构造方法");
    }
    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        System.out.println("执行setBeanClassLoader,ClassLoader Name = " + classLoader.getClass().getName());
    }
    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("执行setBeanFactory,setBeanFactory:: CustomBeanAware bean singleton=" +  beanFactory.isSingleton("customBeanAware"));
    }
    @Override
    public void setBeanName(String s) {
        System.out.println("执行setBeanName:: Bean Name defined in context="
                + s);
    }
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("执行setApplicationContext:: Bean Definition Names="
                + Arrays.toString(applicationContext.getBeanDefinitionNames()));
    }
    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        System.out.println("执行setApplicationEventPublisher");
    }
    @Override
    public void setEnvironment(Environment environment) {
        System.out.println("执行setEnvironment");
    }
    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        Resource resource = resourceLoader.getResource("classpath:spring-beans.xml");
        System.out.println("执行setResourceLoader:: Resource File Name="
                + resource.getFilename());
    }
    @Override
    public void setImportMetadata(AnnotationMetadata annotationMetadata) {
        System.out.println("执行setImportMetadata");
    }
}
