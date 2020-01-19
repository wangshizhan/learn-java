package com.hnanet.aop.cglib;

public abstract class CustomBean {

    /**
     * 用于测试lookup-method
     * @return
     */
    public abstract User getUserBean();

    /**
     * 用于测试replace-method
     */
    public void changedMethod() {
        System.out.println("Call CustomBean.changedMethod()");
    }
}
