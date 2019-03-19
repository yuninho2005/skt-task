package com.sdevelopment.skt.microservice.dao;

import com.sdevelopment.skt.common.domain.Product;

import java.util.List;

public interface ProductRepositoryCustom {
    void saveProduct(Product product);
    List<Product> getAllProducts();
}
