package com.hnanet._0_init._0_lifeCycle;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class LifeCycleTest {

    private static final Logger logger = LoggerFactory.getLogger(LifeCycleTest.class);

    /**
     *
     * UserService_Imp_Spring 无参 构造函数 被调用
     * UserService_Imp_Spring afterPropertiesSet() 方法被调用
     * UserService_Bean_Method 无参 构造函数 被调用
     * UserService_Bean_Method init() 方法被调用
     * 执行 PostConstruct 注解标注的方法
     * Spring Context 初始化
     * 从Spring Context 拿到 Bean
     * User password = UserService_Imp_Spring password
     * User password = UserService_Imp_Spring password
     * User password = UserService_Imp_Spring password
     * 执行 preDestroy 注解标注的方法
     * UserService_Bean_Method destroy() 方法被调用：Closing resources
     * UserService_Imp_Spring destroy() 方法被调用：Closing resources
     * Spring Context 关闭
     *
     */

    @Test
    public void TestLife() {

        // 这两个文件效果一样
        //String path = "bean-noContextType-config.xml";
        String path = "spring/lifeCycle/bean-contextType-config.xml";
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(path);

        System.out.println("Spring Context 初始化");

        // 注意：这里的实现判断了 User 的 password 的值，被设置之后，不会再重新设置。
        InterTest userService_imp = ctx.getBean("userService_imp", InterTest.class);
        MethodTest userService_method = ctx.getBean("userService_method", MethodTest.class);
        AnnoTest userService_anno = ctx.getBean("userService_anno", AnnoTest.class);

        System.out.println("从Spring Context 拿到 Bean");
        System.out.println("User password = " + userService_imp.getUser().getPassword());
        System.out.println("User password = " + userService_method.getUser().getPassword());
        System.out.println("User password = " + userService_anno.getUser().getPassword());
        ctx.close();
        System.out.println("Spring Context 关闭");
    }
}