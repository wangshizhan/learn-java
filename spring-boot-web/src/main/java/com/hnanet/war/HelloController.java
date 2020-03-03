package com.hnanet.war;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    final static Logger log = LoggerFactory.getLogger(HelloController.class);
    @RequestMapping("hello")
    public void hello(){
        log.debug("hello");
    }
}
