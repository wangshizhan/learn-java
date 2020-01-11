package com.hnanet.springBean.service;

import com.hnanet.springBean.bean.User;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 在 bean 的 XML 配置文件中指定init-method和destroy-method方法 查看 Bean 的生命周期
 * 注入速度比 实现 InitializingBean, DisposableBean 接口的方式慢，注解的方式最慢
 * 在 XML 自定义的 init-method 和post-method 方法可以抛异常但是不能有参数
 */
public class UserService_Bean_Method {

    private static final Logger logger = LoggerFactory.getLogger(UserService_Bean_Method.class);
    @Setter
    @Getter
    private User user;

    public UserService_Bean_Method(){
        System.out.println("UserService_Bean_Method 无参 构造函数 被调用");
    }

    /**
     * pre-destroy method
     * @throws Exception
     */
    public void destroy() throws Exception {
        System.out.println("UserService_Bean_Method destroy() 方法被调用：Closing resources");
    }

    /**
     * post-init method
     * @throws Exception
     */
    public void init() throws Exception {
        System.out.println("UserService_Bean_Method init() 方法被调用");
        if(user.getPassword() == null){
            user.setPassword("UserService_Bean_Method password");
        }
    }
}