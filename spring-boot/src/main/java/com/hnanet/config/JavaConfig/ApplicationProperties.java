package com.hnanet.config.JavaConfig;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 将配置文件信息的内容转化为List<Map<String, String>>，注入到ShiroConfig中
 * 注意：prefix="permission-config"和perms要与.yml文件中的属性对应
 * 注意：文件名以 application 开头
 */

@ToString
@Component
@ConfigurationProperties(prefix = "permission-config")
public class ApplicationProperties {

    private List<Map<String,String>> perms;

    public List<Map<String, String>> getPerms() {
        return perms;
    }

    public void setPerms(List<Map<String, String>> perms) {
        this.perms = perms;
    }
}
