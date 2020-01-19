package com.hnanet;

import org.junit.Test;

public class AspectTest {

    @Test
    public void aspectTest() {
        System.out.println( new AspectTest().say() );
    }
    public String say() {
        return "World";
    }
}