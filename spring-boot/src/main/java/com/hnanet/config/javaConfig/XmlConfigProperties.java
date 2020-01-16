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
 * 将配置文件信息的内容转化为List<Map<String, String>>，注入到ShiroConfig中
 * 注意：prefix="permission-config"和perms要与.yml文件中的属性对应
 * 注意：文件名以 application 开头
 */
@Configuration
@PropertySource("classpath:javaConfig/mail.properties")
@ConfigurationProperties(prefix = "mail")
@Setter
@Getter
@ToString
public class XmlConfigProperties {


    public static class Credentials{

        private String authMethod;
        private String username;
        private String password;

        public String getAuthMethod() {
            return authMethod;
        }

        public void setAuthMethod(String authMethod) {
            this.authMethod = authMethod;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        @Override
        public String toString() {
            return "Credentials{" +
                    "authMethod='" + authMethod + '\'' +
                    ", username='" + username + '\'' +
                    ", password='" + password + '\'' +
                    '}';
        }
    }


    private String host;
    private int port;
    private  String from;
    private  Credentials credentials;
    /**
     * 接收者
     */
    private List<String> recipients;
    private Map<String,String> additionalHeaders;
    private Map<String,Credentials> mp;
    private List<Credentials>  cs;

    //getter setter 省略

}