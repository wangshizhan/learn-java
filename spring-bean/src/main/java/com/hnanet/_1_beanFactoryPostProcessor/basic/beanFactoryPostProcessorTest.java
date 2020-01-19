package com.hnanet._1_beanFactoryPostProcessor.basic;

import org.junit.Test;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class beanFactoryPostProcessorTest {

    @Test
    public void testP() {
        /**
         * 我们定义的后处理器在 bean 信息加载时就放入注册表中
         * 然后通过 beanFactory.getBeanNamesForType(BeanDefinitionRegistryPostProcessor.class, true, false) 方法获取后处理器列表遍历执行。
         */
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("spring/beanFactoryPostProcessor/bean-postProcessor-config.xml");
         // 输出 ：敏感词被替换了
        System.out.println(context.getBean("web"));
    }

    @Test
    public void testP2(){
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("spring/beanFactoryPostProcessor/bean-postProcessor-config.xml");
        /**
         * 延迟加载的标记会被忽略，即使你在元素的声明中将default-lazy-init属性设置为true,BeanFactoryPostProcessor 也会尽早地实例化
         */
        BeanFactoryPostProcessor hardCodeBeanFactoryPostProcessor = new HardCodeBeanFactoryPostProcessor();
        context.addBeanFactoryPostProcessor(hardCodeBeanFactoryPostProcessor);
        /**
         * 通过硬编码方法方式，手动添加进去
         * 并通过 context.refresh() 方法后，再执行硬编码的后处理器
         */
        context.refresh();
        System.out.println(context.getBean("web"));
    }
}
