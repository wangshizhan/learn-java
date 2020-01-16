package com.hnanet._0_init._0_runSequence;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@ToString
public class User {


    public User() {
        super();
        System.out.println("User 构造方法");
    }
    String username;
    String password;
}