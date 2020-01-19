package com.hnanet._2_beanPostProcessor.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean(name = "user", initMethod = "init",destroyMethod = "destory")
    public User createUser(){
        return new UserService();
    }
}