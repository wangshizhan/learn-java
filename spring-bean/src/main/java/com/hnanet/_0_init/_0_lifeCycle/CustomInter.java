package com.hnanet._0_init._0_lifeCycle;

import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * 使用实现 InitializingBean, DisposableBean 类的方式，查看 Bean 的生命周期，速度比从 XML 注入的方式快，注解的方式最慢
 */
public class CustomInter implements InitializingBean, DisposableBean {

    private static final Logger logger = LoggerFactory.getLogger(CustomInter.class);
    @Setter
    @Getter
    private User user;

    public CustomInter(){
        System.out.println("CustomInter 无参 构造函数 被调用");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("CustomInter destroy() 方法被调用：Closing resources");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("CustomInter afterPropertiesSet() 方法被调用");
        if(user.getPassword() == null){
            user.setPassword("CustomInter password");
        }
    }
}
