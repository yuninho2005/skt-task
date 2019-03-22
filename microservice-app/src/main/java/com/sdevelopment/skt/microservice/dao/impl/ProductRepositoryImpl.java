package com.sdevelopment.skt.microservice.dao.impl;

import com.sdevelopment.skt.common.domain.Product;
import com.sdevelopment.skt.microservice.dao.ProductRepository;
import com.sdevelopment.skt.microservice.dao.ProductRepositoryCustom;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import java.util.List;

public class ProductRepositoryImpl implements ProductRepository {

    @PersistenceContext
    private EntityManager em;

    public void saveProduct(Product product) {
        StoredProcedureQuery saveProductProcedure = em.createNamedStoredProcedureQuery("saveProduct");
        saveProductProcedure.setParameter("_name", product.getName());

        saveProductProcedure.execute();
    }

    public List<Product> getAllProducts() {
        StoredProcedureQuery findAllProcedure = em.createNamedStoredProcedureQuery("getAllProducts");

        findAllProcedure.execute();

        return findAllProcedure.getResultList();
    }
}
