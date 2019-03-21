package com.sdevelopment.skt.microservice.dao;

import com.sdevelopment.skt.common.domain.Product;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepositoryCustom {

    @Procedure(name = "insertproductsp")
    void saveProduct(@Param("_name")Product product);

    @Procedure(name = "listproductssp")
    List<Product> getAllProducts();
}
