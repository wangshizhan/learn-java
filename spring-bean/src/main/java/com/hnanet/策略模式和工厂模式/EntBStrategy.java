package com.hnanet.策略模式和工厂模式;

import org.springframework.stereotype.Component;

@Component
public class EntBStrategy implements EntStrategy {
    @Override
    public String getStuff() {
        return "企业B";
    }

    @Override
    public void send() {
        System.out.println("发送B标准的报文给对应企业");
    }

    @Override
    public String toString() {
        return getStuff();
    }
}