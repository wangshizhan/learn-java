package com.hnanet.aop.spring.xml.advice;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

import java.util.Arrays;

public class AopAdvice {
    public void before(JoinPoint joinPoint) throws Throwable {
        System.out.println("AopAdvice#before "+joinPoint.getSignature() +" 入参: " + Arrays.toString(joinPoint.getArgs()));
    }

    public void after(JoinPoint joinPoint) throws Throwable {
        System.out.println("AopAdvice#after "+joinPoint.getSignature() +" 入参: " + Arrays.toString(joinPoint.getArgs()));
    }
    public void aroundMethod(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("[Aspect] around advise aroundMethod 1 " + pjp.getSignature() +" 入参: " + Arrays.toString(pjp.getArgs()));
        pjp.proceed();
        System.out.println("[Aspect] around advise aroundMethod 2 " + pjp.getSignature() +" 入参: " + Arrays.toString(pjp.getArgs()));
    }

}
