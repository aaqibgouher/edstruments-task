package com.example.edstruments.service;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.stereotype.Service;
import com.example.edstruments.model.Product;
import com.example.edstruments.repository.ProductRepository;
import com.example.edstruments.utils.dto.ProductRequest;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public Product addProduct(ProductRequest payload) {
        // Additional validation (beyond basic @Valid)
        if (payload.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Product name is required");
        }

        if (payload.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Price must be greater than 0");
        }

        // Convert DTO to Product entity
        Product product = new Product();
        product.setName(payload.getName());
        product.setDescription(payload.getDescription());
        product.setPrice(payload.getPrice());

        // Save and return
        return productRepository.save(product);
    }

    public Product getProduct(String id) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("Product ID is required");
        }

        return productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product not found with ID: " + id));
    }

    public Product updateProduct(String id, ProductRequest payload) {
        Product existing = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product not found with ID: " + id));

        // Update fields
        if (payload.getName() == null || payload.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Product name is required");
        }
        if (payload.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Price must be greater than 0");
        }

        existing.setName(payload.getName());
        existing.setDescription(payload.getDescription());
        existing.setPrice(payload.getPrice());

        return productRepository.save(existing);
    }

    public void deleteProduct(String id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product not found with ID: " + id));
        productRepository.delete(product);
    }
}
