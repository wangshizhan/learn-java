package com.hnanet._8_cache.basic;

import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * @author 王世展
 * 配置一个CacheManager
 */
@Configuration
public class CacheConfig {
    @Primary
    @Bean(name = { "cacheManager" })
    public CacheManager getCache() {
        return new ConcurrentMapCacheManager("users");
    }
}
