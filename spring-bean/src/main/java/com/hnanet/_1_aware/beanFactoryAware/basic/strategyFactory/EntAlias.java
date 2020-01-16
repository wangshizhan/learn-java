package com.hnanet._1_aware.beanFactoryAware.basic.strategyFactory;

import java.util.LinkedHashMap;
import java.util.Map;

public class EntAlias {

    private static Map<String, String> aliasMap;

    private static final String ENTA_STATEGY_NAME = "entAStrategy";
    private static final String ENTB_STATEGY_NAME = "entBStrategy";
    public static final String DEFAULT_STATEGY_NAME = "defaultStrategy";

    static {
        // 这个别名容器怎么注册别名、初始化，有很多种方式。
        aliasMap = new LinkedHashMap<>();
        aliasMap.put("entA", ENTA_STATEGY_NAME);
        aliasMap.put("entB", ENTB_STATEGY_NAME);
    }

    public static String of(String entNum) {
        return aliasMap.get(entNum);
    }
}