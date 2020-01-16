package com.hnanet._1_aware.beanFactoryAware.basic.strategyFactory;

import org.springframework.stereotype.Component;

@Component
public class DefaultStrategy  implements EntStrategy {
    @Override
    public String getStuff() {
        return "其他企业";
    }

    @Override
    public void send() {
        System.out.println("发送默认标准的报文给对应企业");
    }

    @Override
    public String toString() {
        return getStuff();
    }
}