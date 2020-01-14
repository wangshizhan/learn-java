package com.hnanet.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Application {
    public static void main(String[] args){

        //启动WEB项目
        SpringApplication application = new SpringApplication(Application.class);

        ConfigurableApplicationContext context = application.run(args);

        ConfigController con = (ConfigController) context.getBean("configController");

        con.testConfig();
    }
}
