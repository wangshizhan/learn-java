package com.hnanet.springBean;

import com.hnanet.springBean.bean.Employee;
import com.hnanet.springBean.bean.User;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class Bean_Life_Cycle {

    private static final Logger logger = LoggerFactory.getLogger(Bean_Life_Cycle.class);

    @Test
    public void TestLife() {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring-config.xml");

        System.out.println("Spring Context initialized");

        //MyEmployeeService service = ctx.getBean("myEmployeeService", MyEmployeeService.class);
        EmployeeService service = ctx.getBean("employeeService", EmployeeService.class);

        System.out.println("Bean retrieved from Spring Context");

        System.out.println("Employee Name=" + service.getEmployee().getName());

        ctx.close();
        System.out.println("Spring Context Closed");
    }
}

class EmployeeService implements InitializingBean, DisposableBean{

    private static final Logger logger = LoggerFactory.getLogger(EmployeeService.class);
    private Employee employee;

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public EmployeeService(){
        System.out.println("EmployeeService 无参 构造函数 被调用");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("EmployeeService Closing resources");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("EmployeeService initializing to dummy value");
        if(employee.getName() == null){
            employee.setName("Pankaj");
        }
    }
}
class MyEmployeeService{

    private static final Logger logger = LoggerFactory.getLogger(MyEmployeeService.class);
    private Employee employee;

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public MyEmployeeService(){
        System.out.println("MyEmployeeService 无参 构造函数 被调用");
    }

    //pre-destroy method
    public void destroy() throws Exception {
        System.out.println("MyEmployeeService Closing resources");
    }

    //post-init method
    public void init() throws Exception {
        System.out.println("MyEmployeeService initializing to dummy value");
        if(employee.getName() == null){
            employee.setName("Pankaj");
        }
    }
}
class MyService {

    private static final Logger logger = LoggerFactory.getLogger(MyService.class);
    @PostConstruct
    public void init(){
        System.out.println("MyService init 方法 被调用");
    }

    public MyService(){
        System.out.println("MyService 无参 构造函数 被调用");
    }

    @PreDestroy
    public void destory(){
        System.out.println("MyService destroy 方法 被调用");
    }
}