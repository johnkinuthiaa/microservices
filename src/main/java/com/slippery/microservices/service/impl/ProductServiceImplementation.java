package com.slippery.microservices.service.impl;

import com.slippery.microservices.dto.ProductDto;
import com.slippery.microservices.models.Product;
import com.slippery.microservices.repository.ProductRepository;
import com.slippery.microservices.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ProductServiceImplementation implements ProductService {
    private final ProductRepository repository;

    public ProductServiceImplementation(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public ProductDto createProduct(Product product) {
        ProductDto response =new ProductDto();
        repository.save(product);
        response.setMessage("Product created");
        log.info("Product created successfully!");
        response.setStatusCode(200);
        response.setProduct(product);
        return response;

    }

    @Override
    public ProductDto updateProduct(Product product, Long id) {
        return null;
    }

    @Override
    public ProductDto findProductById(Long id) {
        return null;
    }

    @Override
    public ProductDto deleteProductById(Long id) {
        return null;
    }

    @Override
    public ProductDto findProductByName(String name) {
        return null;
    }

    @Override
    public ProductDto getAllProducts() {
        return null;
    }
}
