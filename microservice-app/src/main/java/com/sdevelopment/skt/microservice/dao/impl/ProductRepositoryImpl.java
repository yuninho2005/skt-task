package com.sdevelopment.skt.microservice.dao.impl;

import com.sdevelopment.skt.common.domain.Product;
import com.sdevelopment.skt.microservice.dao.ProductRepository;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import java.util.LinkedList;
import java.util.List;

@Component
public class ProductRepositoryImpl implements ProductRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void saveProduct(Product product){
        StoredProcedureQuery query = em.createStoredProcedureQuery("InsertProduct");
        query.registerStoredProcedureParameter("pname", String.class, ParameterMode.IN);

        // set input parameter
        query.setParameter("pname", product.getName());

        query.execute();
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> products = new LinkedList<>();
        StoredProcedureQuery query = em.createStoredProcedureQuery("GetAllProducts");

        query.execute();

        List<Object[]> results = query.getResultList();

        results.stream().forEach((record) -> {
            Product product = new Product();
            String name = (String)record[1];

            product.setName(name);

            products.add(product);
        });

        return products;
    }
}
