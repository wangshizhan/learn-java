package com.hnanet.aop.custom;

import org.springframework.aop.framework.AdvisedSupport;
import org.springframework.aop.framework.AopProxy;

public interface MethodInvocation {
    void invoke();
}