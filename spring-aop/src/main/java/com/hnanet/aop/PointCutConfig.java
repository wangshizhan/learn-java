package com.hnanet.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;


/**
 * 切面表达式
 */
@Aspect
@Component
public class PointCutConfig {

    /**
     * 匹配 com.hnanet.aop 包及子包下所有类的方法
     */
    @Pointcut("within(com.hnanet.aop..*)")
    public void inSvcLayer() {}

}