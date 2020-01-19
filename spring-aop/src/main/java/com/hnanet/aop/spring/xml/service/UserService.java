package com.hnanet.aop.spring.xml.service;

import com.hnanet.aop.spring.xml.dao.UserDao;
import lombok.Getter;
import lombok.Setter;

public class UserService {
    @Setter
    @Getter
    private UserDao userDao;
    public void playGame() {
        System.out.println("UserService#playGame");
        userDao.playGame();
    }
    public void findGame(String gameName , int size) {
        System.out.println("UserService#finGame "+ gameName+" "+ size);
        userDao.findGame(gameName,size);
    }
}
