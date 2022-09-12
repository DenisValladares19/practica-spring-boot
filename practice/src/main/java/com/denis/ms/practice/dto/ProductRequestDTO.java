package com.denis.ms.practice.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductRequestDTO {
    private Long productId;
    private String name;
    private BigDecimal price;
    private ProviderDTO provider;
}
