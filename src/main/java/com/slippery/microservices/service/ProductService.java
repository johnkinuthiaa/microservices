package com.slippery.microservices.service;

import com.slippery.microservices.dto.ProductDto;
import com.slippery.microservices.models.Product;

public interface ProductService {
    ProductDto createProduct(Product product);

    ProductDto updateProduct(Product product, Long id);

    ProductDto findProductById(Long id);

    ProductDto deleteProductById(Long id);

    ProductDto findProductByName(String name);

    ProductDto getAllProducts();
}
