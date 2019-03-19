package com.sdevelopment.skt.microservice.dao.impl;

import com.sdevelopment.skt.common.domain.Product;
import com.sdevelopment.skt.microservice.dao.ProductRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import java.util.List;

public class ProductRepositoryImpl implements ProductRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void saveProduct(Product product) {
        StoredProcedureQuery saveProductProcedure =
                em.createNamedStoredProcedureQuery("saveProduct");
        saveProductProcedure.execute();
    }

    @Override
    public List<Product> getAllProducts() {
        StoredProcedureQuery findAllProcedure =
                em.createNamedStoredProcedureQuery("getAllProducts");
        return findAllProcedure.getResultList();
    }

    @Override
    public <S extends Product> S save(S s) {
        return null;
    }

    @Override
    public <S extends Product> Iterable<S> save(Iterable<S> iterable) {
        return null;
    }

    @Override
    public Product findOne(Long aLong) {
        return null;
    }

    @Override
    public boolean exists(Long aLong) {
        return false;
    }

    @Override
    public Iterable<Product> findAll() {
        return null;
    }

    @Override
    public Iterable<Product> findAll(Iterable<Long> iterable) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void delete(Long aLong) {

    }

    @Override
    public void delete(Product product) {

    }

    @Override
    public void delete(Iterable<? extends Product> iterable) {

    }

    @Override
    public void deleteAll() {

    }
}
