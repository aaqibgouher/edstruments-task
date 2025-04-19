package com.example.edstruments.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.edstruments.model.Product;

public interface ProductRepository extends MongoRepository<Product, String> {
}
