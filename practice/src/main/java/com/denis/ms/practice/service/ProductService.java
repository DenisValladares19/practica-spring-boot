package com.denis.ms.practice.service;

import com.denis.ms.practice.dto.ProductDTO;
import com.denis.ms.practice.dto.ProductRequestDTO;
import com.denis.ms.practice.dto.ResponseDTO;

import java.util.List;

public interface ProductService {
    List<ProductDTO> getAll();
    List<ProductDTO> getAllByProvider(Long providerId);
    ProductDTO save(ProductRequestDTO dto);
    ProductDTO update(ProductRequestDTO dto);
    ProductDTO getById(Long productId);
    void delete(Long productId);
}
