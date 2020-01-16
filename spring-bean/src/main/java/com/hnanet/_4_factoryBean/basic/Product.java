package com.hnanet._4_factoryBean.basic;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Product {

    private Material material;

    private ProduceLocation location;

    private double price;
}
