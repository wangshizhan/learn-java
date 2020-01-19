package com.hnanet;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class AspectDemo {

    @Pointcut("execution(* com.hnanet.AspectTest.say())")
    private void pointcut() {}  // signature

    @Before("pointcut()")
    public void before(){
        System.out.println("Hello");
    }
}
