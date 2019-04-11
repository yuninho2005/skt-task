package com.sdevelopment.skt.microservice.service.impl;

import com.sdevelopment.skt.common.domain.Product;
import com.sdevelopment.skt.microservice.dao.ProductRepository;
import com.sdevelopment.skt.microservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;

    @Override
    public void saveProduct(Product product) {
        productRepository.saveProduct(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.getAllProducts();
    }

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
}
