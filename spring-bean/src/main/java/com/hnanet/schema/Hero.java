package com.hnanet.schema;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@Setter
@Getter
@ToString
public class Hero {
    private  String name;
    private  int age;
}