package com.hnanet._8_cache.basic;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {
    private String userId;
    private String name;

}
