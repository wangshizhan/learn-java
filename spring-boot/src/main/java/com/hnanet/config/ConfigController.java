package com.hnanet.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ConfigController {
    @Autowired
    PermsMap permsMap;

    public void testConfig(){
        System.out.println(permsMap);
    }
}
