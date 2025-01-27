package com.slippery.microservices.service.impl;

import com.slippery.microservices.dto.ProductDto;
import com.slippery.microservices.models.Product;
import com.slippery.microservices.repository.ProductRepository;
import com.slippery.microservices.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class ProductServiceImplementation implements ProductService {
    private final ProductRepository repository;

    public ProductServiceImplementation(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public ProductDto createProduct(Product product) {
        ProductDto response = new ProductDto();
        repository.save(product);
        response.setMessage("Product created");
        log.info("Product created successfully!");
        response.setStatusCode(200);
        response.setProduct(product);
        return response;

    }

    @Override
    public ProductDto updateProduct(Product product, Long id) {
        ProductDto response = new ProductDto();
        Optional<Product> existingProduct = repository.findById(id);
        if (existingProduct.isEmpty()) {
            response.setMessage("Product not found");
            log.info("Product not found");
            response.setStatusCode(404);
            return response;
        }
        existingProduct.get().setName(product.getName() == null ? existingProduct.get().getName() : product.getName());
        existingProduct.get().setCategory(product.getCategory() ==null?existingProduct.get().getCategory(): product.getCategory());
        existingProduct.get().setDescription(product.getDescription() ==null?existingProduct.get().getDescription(): product.getDescription());
        existingProduct.get().setPrice(product.getPrice() ==null? existingProduct.get().getPrice():product.getPrice());
        existingProduct.get().setQuantity(product.getQuantity() ==null? existingProduct.get().getQuantity(): product.getQuantity());
        repository.save(existingProduct.get());
        response.setMessage("Product updated");
        response.setStatusCode(200);
        response.setProduct(existingProduct.get());
        return response;
    }

    @Override
    public ProductDto findProductById(Long id) {
        ProductDto response =new ProductDto();
        Optional<Product> existingProduct =repository.findById(id);
        if(existingProduct.isEmpty()){
            response.setMessage("Product not found");
            response.setStatusCode(404);
            return response;
        }
        response.setMessage("Product with id"+id);
        response.setStatusCode(200);
        response.setProduct(existingProduct.get());
        return response;
    }

    @Override
    public ProductDto deleteProductById(Long id) {
        ProductDto response =new ProductDto();
        Optional<Product> existingProduct = repository.findById(id);
        if(existingProduct.isEmpty()){
            response.setMessage("Product not found");
            response.setStatusCode(404);
            return response;
        }
        repository.delete(existingProduct.get());
        response.setMessage("Product deleted");
        log.info("Product deleted");
        response.setStatusCode(200);
        return response;
    }

    @Override
    public ProductDto findProductByName(String name) {
        ProductDto response =new ProductDto();
        var product =repository.findAll().stream()
                .filter(product1 -> product1.getName().toLowerCase().contains(name.toLowerCase()))
                .toList();
        if(product.isEmpty()){
            response.setMessage("No products with the name "+name+" were found");
            response.setStatusCode(404);
            return response;
        }
        response.setProductList(product);
        response.setMessage("Products matching the name "+name);
        response.setStatusCode(200);
        return response;
    }

    @Override
    public ProductDto getAllProducts() {
        ProductDto response =new ProductDto();
        var allProducts =repository.findAll();
        if(allProducts.isEmpty()){
            response.setMessage("Product list is empty");
            response.setStatusCode(404);
            return response;
        }
        response.setProductList(repository.findAll());
        response.setMessage("All products");
        response.setStatusCode(200);
        return response;
    }
}
