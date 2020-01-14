package com.hnanet.proxy.jdk;

public class UserService implements IUserService {

    @Override
    public int insert() {
        System.out.println("UserService insert()");
        // 目标对象方法内部调用自己的另一个方法时，另一个方法在执行时，没有经过代理对象
        query();
        return 0;
    }

    @Override
    public String query() {
        System.out.println("UserService query()");
        return null;
    }
}
