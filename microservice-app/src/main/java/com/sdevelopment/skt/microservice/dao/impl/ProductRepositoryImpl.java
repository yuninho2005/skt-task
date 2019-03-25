package com.sdevelopment.skt.microservice.dao.impl;

import com.sdevelopment.skt.common.domain.Product;
import com.sdevelopment.skt.microservice.dao.ProductRepository;
import com.sdevelopment.skt.microservice.dao.ProductRepositoryCustom;
import org.hibernate.Session;
import org.hibernate.procedure.ProcedureCall;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import java.sql.CallableStatement;
import java.sql.Types;
import java.util.List;

public class ProductRepositoryImpl implements ProductRepository {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void saveProduct(Product product) {
        /*StoredProcedureQuery saveProductProcedure = em.createNamedStoredProcedureQuery("saveProduct");
        saveProductProcedure.setParameter("_name", product.getName());

        saveProductProcedure.execute();*/

        //em.createStoredProcedureQuery("{ call insertproductsp(?) }").execute();

        StoredProcedureQuery query = em.createStoredProcedureQuery("call public.insertproductsp(?)");
        query.registerStoredProcedureParameter("_name", String.class, ParameterMode.IN);

        // set input parameter
        query.setParameter("_name", product.getName());

        query.execute();
    }

    public List<Product> getAllProducts() {
        /*StoredProcedureQuery findAllProcedure = em.createNamedStoredProcedureQuery("getAllProducts");

        findAllProcedure.execute();

        return findAllProcedure.getResultList();*/

        StoredProcedureQuery query = em.createStoredProcedureQuery("call lisproductssp");

        query.execute();

        return null;
    }
}
