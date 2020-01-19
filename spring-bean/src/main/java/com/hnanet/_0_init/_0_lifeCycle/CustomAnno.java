package com.hnanet._0_init._0_lifeCycle;

import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class CustomAnno {

    @Setter
    @Getter
    private User user;

    @PostConstruct
    public void initPostConstruct(){
        System.out.println("执行 PostConstruct 注解标注的方法");
        if(user.getPassword() == null){
            user.setPassword("CustomAnno password");
        }
    }
    @PreDestroy
    public void preDestroy(){
        System.out.println("执行 preDestroy 注解标注的方法");
    }
}
