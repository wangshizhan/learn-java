package com.hnanet.proxy.service;

public class UserService implements IUserService {
    @Override
    public String getUserNameById(String id) {
        return "hi username" + id;
    }
}
