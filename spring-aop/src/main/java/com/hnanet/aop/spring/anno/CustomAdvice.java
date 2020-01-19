package com.hnanet.aop.spring.anno;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;


@Component
@Aspect
public class CustomAdvice {

    /**
     *  根据切点，配置 Advice
     *  拦截，打印日志，并且通过JoinPoint 获取方法参数
     *  @param joinPoint
     */
    @Before("com.hnanet.aop.spring.anno.CustomPointCut.xx1()")
    public void logBeforeService(JoinPoint joinPoint) {
        System.out.println("在 service 方法执行前，JoinPoint 类参数。 打印第 1 次日志");
        System.out.println("拦截的service 方法的方法签名: " + joinPoint.getSignature());
        System.out.println("拦截的service 方法的方法入参: " + Arrays.toString(joinPoint.getArgs()));
    }

    /**
     * 配置 Advice
     * 这里是 Advice 和 Pointcut 合在一起配置的方式，效果等于上面那两个
     * 匹配 com.hnanet.aop.spring 包及子包下所有类的方法
     */
    @Before("within(com.hnanet.aop.spring..*)")
    public void logBeforeService2() {

        System.out.println("在 service 的方法执行前，无参数。 打印第 2 次日志");
    }
}
