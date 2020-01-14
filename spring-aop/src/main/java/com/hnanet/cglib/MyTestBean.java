package com.hnanet.cglib;

public abstract class MyTestBean {

    /**
     * 用于测试lookup-method
     * @return
     */
    public abstract User getUserBean();

    /**
     * 用于测试replace-method
     */
    public void changedMethod() {
        System.out.println("Call MyTestBean.changedMethod()");
    }
}
