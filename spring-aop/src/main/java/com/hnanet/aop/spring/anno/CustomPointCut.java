package com.hnanet.aop.spring.anno;

import org.aspectj.lang.annotation.Pointcut;

/**
 * 使用 @Pointcut 注解的方法的方法体必须是空的，并且没有任何throws语句。
 * 如果切入点绑定了形式参数（使用args()、target()、this()、@args()、@target()、@this()、@annotation()）,那么它们必须也是方法的形式参数。
 */
public class CustomPointCut {
    /**
     * 定义一个切点
     * 匹配 com.hnanet.aop.spring 包及子包下所有类的方法
     */
    @Pointcut("within(com.hnanet.aop.spring..*)")
    public void xx1() {
    }
}
