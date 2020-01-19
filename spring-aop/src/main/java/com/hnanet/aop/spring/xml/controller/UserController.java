package com.hnanet.aop.spring.xml.controller;

import com.hnanet.aop.spring.xml.service.UserService;
import lombok.Getter;
import lombok.Setter;

public class UserController {
    @Setter
    @Getter
    private UserService userService;
    public void playGame() {
        System.out.println("UserController#playGame");
        userService.playGame();
    }
    public void findGame(String gameName,int size) {
        System.out.println("UserController#playGame "+ gameName+ " " +size);
        userService.findGame(gameName,size);
    }
}
