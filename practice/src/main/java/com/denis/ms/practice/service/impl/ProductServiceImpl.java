package com.denis.ms.practice.service.impl;

import com.denis.ms.practice.dto.ProductDTO;
import com.denis.ms.practice.dto.ProductRequestDTO;
import com.denis.ms.practice.entity.Product;
import com.denis.ms.practice.entity.Provider;
import com.denis.ms.practice.exceptions.ResponseException;
import com.denis.ms.practice.repository.ProductRepository;
import com.denis.ms.practice.repository.ProviderRepository;
import com.denis.ms.practice.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProviderRepository providerRepository;

    @Override
    public List<ProductDTO> getAll() {
        List<Product>  list = productRepository.findAll();
        if (list == null || list.size() == 0) {
            throw new ResponseException("List products is empty", HttpStatus.BAD_REQUEST);
        }

        return list.stream().map(element -> {
            ProductDTO dto = new ProductDTO();
            dto.setProductId(element.getProductId());
            dto.setName(element.getName());
            dto.setPrice(element.getPrice());
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public List<ProductDTO> getAllByProvider(Long providerId) {
        List<Product>  list = productRepository.findAllByProvider(providerId);
        if (list == null || list.size() == 0) {
            throw new ResponseException("List products is empty by provider id: " + providerId, HttpStatus.BAD_REQUEST);
        }

        return list.stream().map(element ->
                modelMapper.map(element, ProductDTO.class)).collect(Collectors.toList());
    }

    @Override
    public ProductDTO save(ProductRequestDTO dto) {
        Long providerId = dto.getProvider().getProviderId();
        Optional<Provider> provider = providerRepository.findById(providerId);
        Product product = new Product();

        if (provider.isEmpty()) {
            throw new ResponseException("Provider not found by id: " + providerId, HttpStatus.BAD_REQUEST);
        }

        product.setProvider(provider.get());
        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
        productRepository.save(product);

        return modelMapper.map(product, ProductDTO.class);
    }

    @Override
    public ProductDTO update(ProductRequestDTO dto) {
        Long providerId = dto.getProvider().getProviderId();
        Optional<Product> product = productRepository.findById(dto.getProductId());
        Optional<Provider> provider = providerRepository.findById(providerId);

        if (provider.isEmpty()) {
            throw new ResponseException("Provider not found by id: " + providerId, HttpStatus.BAD_REQUEST);
        }

        if (product.isEmpty()) {
            throw new ResponseException("Product not found by id: " + dto.getProductId(), HttpStatus.BAD_REQUEST);
        }

        Product productSave = product.get();
        productSave.setPrice(dto.getPrice());
        productSave.setName(dto.getName());
        productSave.setProvider(provider.get());
        productRepository.save(productSave);

        return modelMapper.map(productSave, ProductDTO.class);
    }

    @Override
    public ProductDTO getById(Long productId) {
        if (productId == null) {
            throw new ResponseException("Product id is empty", HttpStatus.BAD_REQUEST);
        }

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResponseException("Product not found by id: " + productId, HttpStatus.BAD_REQUEST));
        return modelMapper.map(product, ProductDTO.class);
    }

    @Override
    public void delete(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResponseException("Product not found by id: " + productId, HttpStatus.BAD_REQUEST));

        productRepository.delete(product);
    }
}
