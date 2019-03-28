package com.sdevelopment.skt.microservice.dao;

import com.sdevelopment.skt.common.domain.Product;
import com.sdevelopment.skt.microservice.exception.DuplicatedProductEception;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository {

    @Procedure(name = "insertproductsp")
    void saveProduct(@Param("_name")Product product) throws DuplicatedProductEception;

    @Procedure(name = "listproductssp")
    List<Product> getAllProducts();
}
