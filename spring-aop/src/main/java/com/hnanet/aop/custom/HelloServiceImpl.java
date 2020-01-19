package com.hnanet.aop.custom;

public class HelloServiceImpl implements HelloService {
    @Override
    public void sayHelloWorld() {
        System.out.println("hello world!");
    }
}
