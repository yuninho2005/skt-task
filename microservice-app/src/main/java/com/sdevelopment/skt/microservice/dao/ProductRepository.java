package com.sdevelopment.skt.microservice.dao;

import com.sdevelopment.skt.common.domain.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long>, ProductRepositoryCustom {

}
