package com.hnanet._5_evntListener.spring;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Service;

/**
 * 执行用户注册，并发布事件
 */
@Service
public class UserRegisterService implements ApplicationEventPublisherAware {

    private ApplicationEventPublisher applicationEventPublisher;

    public boolean register(User user) {

        //用户注册
        System.out.println("UserRegisterService#register [service]用户["  + user + "]注册成功！");
        //消息发布
        applicationEventPublisher.publishEvent(new UserRegisterEvent(this, user));

        return true;
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }
}
