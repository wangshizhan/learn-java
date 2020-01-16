package com.hnanet._5_evntListener.spring;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class EventListener implements ApplicationListener<UserRegisterEvent> {

    /**
     * 用户注册成功之后的事件
     * @param event
     */
    @Override
    public void onApplicationEvent(UserRegisterEvent event) {
        //发邮件
        System.out.println("EventListener#onApplicationEvent 正在发送邮件至： " + event.getUser().getEmail());
        //发短信
        System.out.println("EventListener#onApplicationEvent 正在发短信到： " + event.getUser().getPhone());
    }
}