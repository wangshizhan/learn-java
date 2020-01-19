package com.hnanet._3_aware.beanFactoryAware.basic.strategyFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 *
 * 消除 if/else
 */
@Component
public class EntStrategyHolder {

    @Autowired
    private Map<String, EntStrategy> entStrategyMap;

    @Autowired
    private List<EntStrategy> entStrategyList;

    public EntStrategy getBy(String entNum) {

        return entStrategyMap.get(entNum);
    }

    public void print() {
        System.out.println("===== implementation Map =====");
        System.out.println(entStrategyMap);
        entStrategyMap.forEach((name, impl)-> {
            System.out.println(name + ":" + impl.getStuff());
        });
        System.out.println("===== implementation List =====");
        System.out.println(entStrategyList);
        entStrategyList.forEach(
                impl-> System.out.println(impl.getStuff())
        );
    }
}