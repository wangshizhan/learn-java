package com.hnanet._2_beanPostProcessor.spring;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
//@AllArgsConstructor
@ToString
public class User {

    /*private ApplicationContext applicationContext;
    @Autowired
    public void setApplicationContext(ApplicationContext applicationContext) {
        System.out.println("=== User#setApplicationContext() ===");
        this.applicationContext = applicationContext;
    }*/

    public User() {
    }

    public void show() {
        System.out.println("========= User show() =======" );//+ applicationContext);
    }

    public void init(){
        System.out.println("======== User init() ========");
    }

    public void destory(){
        System.out.println("======== User destory() ========");
    }


}