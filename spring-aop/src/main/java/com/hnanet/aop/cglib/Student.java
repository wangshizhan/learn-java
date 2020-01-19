package com.hnanet.aop.cglib;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Student implements User{
    private String id;
    private String name;

    @Override
    public void showMe() {
        System.out.println(toString()+" showMe()");
    }
}
