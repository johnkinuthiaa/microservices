package com.slippery.microservices.controller;

import com.slippery.microservices.dto.ProductDto;
import com.slippery.microservices.models.Product;
import com.slippery.microservices.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }
    @PostMapping("/create")
    public ResponseEntity<ProductDto> createProduct(@RequestBody Product product){
        return ResponseEntity.ok(service.createProduct(product));
    }

}
