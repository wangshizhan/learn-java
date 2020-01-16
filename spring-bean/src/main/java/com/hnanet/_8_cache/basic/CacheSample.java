package com.hnanet._8_cache.basic;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Component
public class CacheSample {
    @Cacheable(cacheNames = "users")
    public Map<String, User> getUser(final Collection<String> userIds) {
        System.out.println("not cache");
        final Map<String, User> mapUser = new HashMap<>();
        userIds.forEach(userId -> {
            mapUser.put(userId, User.builder().userId(userId).name("name").build());
        });
        return mapUser;
    }

    @Cacheable(cacheNames = "users2")
    public Map<String, User> getUserV2(final Collection<String> userIds) {
        System.out.println("not cache" + userIds);
        final Map<String, User> mapUser = new HashMap<>();
        userIds.forEach(userId -> {
            mapUser.put(userId, User.builder().userId(userId).name("name").build());
        });
        return mapUser;
    }
}