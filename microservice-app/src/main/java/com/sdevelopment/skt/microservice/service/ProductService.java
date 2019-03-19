package com.sdevelopment.skt.microservice.service;

import com.sdevelopment.skt.common.domain.Product;

import java.util.List;

public interface ProductService {
    void saveProduct(Product product);
    List<Product> getAllProducts();
}
