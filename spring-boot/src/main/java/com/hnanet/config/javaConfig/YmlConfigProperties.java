package com.hnanet.config.javaConfig;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.List;
import java.util.Map;

/**
 *
 * @PropertySource 注解读取属性文件的关键在于 PropertySourceFactory 接口中的 createPropertySource 方法，
 * 所以我们想要实现 @PropertySource 注解读取 yml 文件就需要实现 createPropertySource 方法，
 * 在 @PropertySource 注解其是通过 DefaultPropertySourceFactory 类来实现这个方法，
 * 我们只需要继承此类，并重写其 createPropertySource 方法即可
 *
 */
@Configuration
@PropertySource(value = "classpath:javaConfig/madly.yml",encoding = "utf-8",factory= YmlPropertySourceFactory.class)
@ConfigurationProperties(prefix = "madly")
@Setter
@Getter
@ToString
public class YmlConfigProperties {
    @ToString
    @Getter
    @Setter
    public static class Credentials{
        private String authMethod;
        private String username;
        private String password;
    }

    private String host;
    private int port;
    private  String from;
    private YmlConfigProperties.Credentials credentials;

    /**
     * 接收者
     */
    private List<String> recipients;
    private Map<String,String> additionalHeaders;
    private Map<String, YmlConfigProperties.Credentials> mp;
    private List<YmlConfigProperties.Credentials>  cs;
}