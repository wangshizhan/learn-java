package com.hnanet.aop.spring.anno;

import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service("dataService")
public class CustomImpl implements CustomInterface {

    @Override
    public void service(Date date) {
        System.out.println(" 实现接口，默认使用 JDK 代理.");
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date) );
    }
}
