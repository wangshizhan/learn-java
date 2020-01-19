package com.hnanet._2_beanPostProcessor.spring;

public class UserService extends User{
    @Override
    public void show(){
        System.out.println("UserService start ......");
        super.show();
        System.out.println("UserService end ......");
    }
}
