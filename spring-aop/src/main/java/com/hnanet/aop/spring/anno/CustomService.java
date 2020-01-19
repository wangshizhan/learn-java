package com.hnanet.aop.spring.anno;


import org.springframework.stereotype.Service;

@Service("customService")
public class CustomService {
    public void process() {
        System.out.println("不实现接口，默认使用 CGLIG 代理.");
    }
}