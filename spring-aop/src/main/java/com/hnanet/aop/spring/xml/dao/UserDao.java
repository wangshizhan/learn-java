package com.hnanet.aop.spring.xml.dao;

public class UserDao {
    public void playGame(){
        System.out.println("UserDao#playGame");
    }
    public void findGame(String gameName,int size){

        System.out.println("UserDao#finGame " + gameName+" "+ size);
    }
}
