package com.hnanet._0_init.deathEn;

import lombok.*;
import org.springframework.beans.factory.DisposableBean;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User implements DisposableBean {
    String username;
    String password;
    public void deathMethod(){
        System.out.println("User Method initMethod() "+toString()+" destory.");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("User DisposableBean "+toString()+" destory.");
    }
}