package com.hnanet.spi;

import org.junit.Test;

import java.util.ServiceLoader;

public class SpiTest {
    @Test
    public  void testSpi(){
        ServiceLoader<IShout> shouts = ServiceLoader.load(IShout.class);
        for (IShout shout: shouts) {
            shout.shout();
            System.out.println(shout.getClass().getName());
        }
    }
}
