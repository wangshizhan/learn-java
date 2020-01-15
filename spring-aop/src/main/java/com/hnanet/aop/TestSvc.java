package com.hnanet.aop;


import org.springframework.stereotype.Service;

@Service("testSvc")
public class TestSvc{
    public void process() {
        System.out.println("test svc is working");
    }
}