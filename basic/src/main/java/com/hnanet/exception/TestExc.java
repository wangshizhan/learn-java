package com.hnanet.exception;

import lombok.Data;

import java.io.FileNotFoundException;

public class TestExc {
    public static void main(String[] args){
        System.out.println("main:" + getPerson() );
    }

    public static String getPerson() {
        Person person = new Person();
        try {
            person.setName("小明");
            System.out.println("try:" + person);
            throw new FileNotFoundException();
        } catch( Exception e ) {
            person.setSex("男");
            System.out.println("catch:" + person);
            return person.toString();
        } finally {
            person.setAge(24);
            System.out.println("finally:" + person);
            return person.toString();
        }

    }
}
@Data
class Person{
    private String name;
    private String Sex;
    private int age;
}
