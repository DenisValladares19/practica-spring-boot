package com.denis.ms.practice.dto;

import lombok.Data;

import java.util.List;
@Data
public class ProviderDTO {
    private Long providerId;
    private String name;
    private String email;
    private String phone;
    private List<ProductDTO> products;
}
