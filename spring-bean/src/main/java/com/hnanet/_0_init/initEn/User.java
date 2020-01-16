package com.hnanet._0_init.initEn;

import lombok.*;
import org.springframework.beans.factory.InitializingBean;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User implements InitializingBean {
    String username;
    String password;

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("User InitializingBean afterPropertiesSet() "+toString()+" init.");
    }

    public void initMethod(){
        System.out.println("User Method initMethod() "+toString()+" init.");
    }
}