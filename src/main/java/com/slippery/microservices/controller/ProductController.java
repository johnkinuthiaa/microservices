package com.slippery.microservices.controller;

import com.slippery.microservices.dto.ProductDto;
import com.slippery.microservices.models.Product;
import com.slippery.microservices.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @PostMapping(value = "/create", produces = "application/json")
    public ResponseEntity<ProductDto> createProduct(@RequestBody Product product) {
        return ResponseEntity.ok(service.createProduct(product));
    }

    @PutMapping(path = "/{id}/update", produces = "application/json")
    public ResponseEntity<ProductDto> updateProduct(@RequestBody Product product, @PathVariable Long id) {
        return ResponseEntity.ok(service.updateProduct(product, id));
    }
    @GetMapping(path = "/{id}/get",produces = "application/json")
    public ResponseEntity<ProductDto> findProductById(@PathVariable Long id){
        return ResponseEntity.ok(service.findProductById(id));
    }
    @DeleteMapping("/{id}/delete")
    public ResponseEntity<ProductDto> deleteProductById(@PathVariable Long id){
        return ResponseEntity.ok(service.deleteProductById(id));
    }
    @GetMapping(path = "/get/name")
    public ResponseEntity<ProductDto> findProductByName(@RequestParam String name){
        return ResponseEntity.ok(service.findProductByName(name));
    }
    @GetMapping(path = "/get/all")
    public ResponseEntity<ProductDto> getAllProducts(){
        return ResponseEntity.ok(service.getAllProducts());
    }
}
