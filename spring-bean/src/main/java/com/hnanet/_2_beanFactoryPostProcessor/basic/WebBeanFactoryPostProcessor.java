package com.hnanet._2_beanFactoryPostProcessor.basic;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionVisitor;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.core.Ordered;
import org.springframework.util.StringValueResolver;

import java.util.Set;


public class WebBeanFactoryPostProcessor implements BeanFactoryPostProcessor, Ordered {

    public WebBeanFactoryPostProcessor() {
        super();
        System.out.println("这是 WebBeanFactoryPostProcessor 实现类构造器！！");
    }
    /**
     * 敏感词
     */
    @Getter
    @Setter
    private Set<String> obscenties;
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {


        System.out.println("BeanFactory Post Processor execute time WebBeanFactoryPostProcessor#postProcessBeanFactory " +beanFactory);
        /**
         * 从 beanFactory 中获取 bean 名字列表
         */
        String[] beanNames = beanFactory.getBeanDefinitionNames();
        for (String beanName : beanNames) {
            BeanDefinition definition = beanFactory.getBeanDefinition(beanName);
            StringValueResolver valueResolver = strVal -> {
                if (isObscene(strVal)) {
                    return "****";
                }
                return strVal;
            };
            BeanDefinitionVisitor visitor = new BeanDefinitionVisitor(valueResolver);
            /**
             * 这一步才是真正处理 bean 的配置信息
             */
            visitor.visitBeanDefinition(definition);
        }
    }

    /**
     * 判断 value 是否在敏感词列表中
     * @param value	值
     * @return		boolean
     */
    private boolean isObscene(Object value) {
        String potentialObscenity = value.toString().toUpperCase();
        boolean isMatcher = false;
        for (String obscentie : obscenties) {
            if(potentialObscenity.contains(obscentie)){
                isMatcher = true;
                break;
            }
        }
        return isMatcher;
    }

    @Override
    public int getOrder() {
        return Integer.MIN_VALUE;
    }
}