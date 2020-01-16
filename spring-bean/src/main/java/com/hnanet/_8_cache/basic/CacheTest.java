package com.hnanet._8_cache.basic;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CacheTest {

    @Test
    public void testC(){
        ApplicationContext con = new ClassPathXmlApplicationContext("spring/cache/bean-config.xml");
        CacheSample cacheSample = (CacheSample) con.getBean("cacheSample");
        List<User> users = cacheSample.getUser(Arrays.asList("ds","dsf")).values().stream().collect(Collectors.toList());
        System.out.println(users);
    }
}
