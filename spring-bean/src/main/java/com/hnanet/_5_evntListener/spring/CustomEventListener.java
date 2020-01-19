package com.hnanet._5_evntListener.spring;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

/**
 * 事件订阅者
 * 4.2 版本以后，直接注解的形式进行事件监听
 * 可以使用 @EventListener 注解在方法，完成 ApplicationListener<E extends ApplicationEvent> 接口的使命
 */
@Service
public class CustomEventListener implements ApplicationListener<UserRegisterEvent> {

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