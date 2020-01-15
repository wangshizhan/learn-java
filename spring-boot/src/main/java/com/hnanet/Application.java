package com.hnanet;

import com.hnanet.config.JavaConfig.ApplicationProperties;
import com.hnanet.config.JavaConfig.XmlConfigProperties;
import com.hnanet.config.JavaConfig.YmlConfigProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@EnableConfigurationProperties
public class Application {

    public static void main(String[] args){

        //启动WEB项目
        //SpringApplication.run(Application.class);

        ConfigurableApplicationContext context = new SpringApplication(Application.class).run(args);
        //context.getBean(ConfigController.class).testConfig();
        ApplicationProperties con = (ApplicationProperties) context.getBean("applicationProperties");
        System.out.println(con.toString());

        XmlConfigProperties configProperties = (XmlConfigProperties) context.getBean("xmlConfigProperties");
        System.out.println(configProperties.toString());

        YmlConfigProperties ymlConfigProperties = (YmlConfigProperties) context.getBean("ymlConfigProperties");
        System.out.println(ymlConfigProperties.toString());


        //ConfigController con2 = SpringHelper.getBean(ConfigController.class);
        //con2.testConfig();

        //ApplicationContextAwareImpl.applicationContext.getBean(ConfigController.class).testConfig();

    }
}
