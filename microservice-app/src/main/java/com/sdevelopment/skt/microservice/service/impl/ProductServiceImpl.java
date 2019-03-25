package com.sdevelopment.skt.microservice.service.impl;

import com.sdevelopment.skt.common.domain.Product;
import com.sdevelopment.skt.microservice.dao.ProductRepository;
import com.sdevelopment.skt.microservice.exception.DuplicatedProductEception;
import com.sdevelopment.skt.microservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void saveProduct(Product product) {
        try {
            productRepository.saveProduct(product);
        } catch (DuplicatedProductEception duplicatedProductEception) {
            // TODO Handle this one
        }
    }

    @Override
    public List<Product> getAllProducts() {

        return productRepository.getAllProducts();
    }
}
