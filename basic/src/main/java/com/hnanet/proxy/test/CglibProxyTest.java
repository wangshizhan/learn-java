package com.hnanet.proxy.test;

import com.hnanet.proxy.cglib.CglibProxy;
import com.hnanet.proxy.service.UserService;
import org.junit.Test;

public class CglibProxyTest {

    /**
     * CGLIB代理机制：继承机制，代理类继承了目标类并重写了目标方法，通过回调函数MethodInterceptor调用父类方法执行原始逻辑
     * 适用于：非接口类，非final类，非final方法
     * 回调方式：通过FastClass方法索引调用
     * 效率：第一次调用因为要生成多个Class对象较JDK方式慢，多次调用因为有方法索引较反射方式快，如果方法过多switch case过多其效率还需测试
     */

    @Test
    public void test_proxy_cglib() {
        CglibProxy cglibProxy = new CglibProxy();
        UserService userService = (UserService) cglibProxy.newInstall(new UserService());
        String userName = userService.getUserNameById("10001");
        System.out.println(userName);
    }
}
