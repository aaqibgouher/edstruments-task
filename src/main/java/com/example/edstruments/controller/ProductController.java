package com.example.edstruments.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.edstruments.model.Product;
import com.example.edstruments.service.ProductService;
import com.example.edstruments.utils.dto.ProductRequest;
import com.example.edstruments.utils.output.ApiResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Product>>> getProducts() {
        try {
            // calling service to get all products
            List<Product> products = productService.getProducts();

            // response
            ApiResponse<List<Product>> response = new ApiResponse<>(200, "Successfully get products", products);

            // return
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            // if error
            ApiResponse<List<Product>> errorResponse = new ApiResponse<>(400, e.getMessage(), null);

            // if error
            return ResponseEntity.status(400).body(errorResponse);
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Product>> addProduct(@Valid @RequestBody ProductRequest payload) {
        try {
            // calling service to get all products
            Product product = productService.addProduct(payload);

            // response
            ApiResponse<Product> response = new ApiResponse<>(200, "Product saved successfully", product);

            // return
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ApiResponse<Product> errorResponse = new ApiResponse<>(400, e.getMessage(), null);
            return ResponseEntity.status(400).body(errorResponse);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Product>> getProduct(@PathVariable String id) {
        try {
            // calling service to get all products
            Product product = productService.getProduct(id);

            // response
            ApiResponse<Product> response = new ApiResponse<>(200, "Successfully get product by id", product);

            // return
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            System.out.println(e + "------");
            // if error
            ApiResponse<Product> errorResponse = new ApiResponse<>(404, e.getMessage(), null);

            return ResponseEntity.status(404).body(errorResponse);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Product>> updateProduct(
            @PathVariable String id,
            @Valid @RequestBody ProductRequest payload) {
        try {
            Product updatedProduct = productService.updateProduct(id, payload);
            ApiResponse<Product> response = new ApiResponse<>(200, "Product updated successfully", updatedProduct);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ApiResponse<Product> errorResponse = new ApiResponse<>(400, e.getMessage(), null);
            return ResponseEntity.status(400).body(errorResponse);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Object>> deleteProduct(@PathVariable String id) {
        try {
            productService.deleteProduct(id);
            ApiResponse<Object> response = new ApiResponse<>(200, "Product deleted successfully", null);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ApiResponse<Object> errorResponse = new ApiResponse<>(400, e.getMessage(), null);
            return ResponseEntity.status(400).body(errorResponse);
        }
    }
}
