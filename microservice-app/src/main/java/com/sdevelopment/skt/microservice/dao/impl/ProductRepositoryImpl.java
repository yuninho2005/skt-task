package com.sdevelopment.skt.microservice.dao.impl;

import com.sdevelopment.skt.common.domain.Product;
import com.sdevelopment.skt.microservice.dao.ProductRepository;
import com.sdevelopment.skt.microservice.dao.ProductRepositoryCustom;
import com.sdevelopment.skt.microservice.exception.DuplicatedProductEception;
import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.procedure.ProcedureCall;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.sql.CallableStatement;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Types;
import java.util.List;

public class ProductRepositoryImpl implements ProductRepository {

    @PersistenceContext
    private EntityManager em;

    //@Transactional
    public void saveProduct(Product product) throws DuplicatedProductEception {
        try {
            StoredProcedureQuery query = em.createStoredProcedureQuery("InsertProduct");
            query.registerStoredProcedureParameter("pname", String.class, ParameterMode.IN);

            // set input parameter
            query.setParameter("pname", product.getName());

            query.execute();
        }


        catch(Exception ex) {
            if(ex instanceof PersistenceException)
                throw new DuplicatedProductEception("Duplicated product. Please check product name.");
        }
    }

    public List<Product> getAllProducts() {
        StoredProcedureQuery query = em.createStoredProcedureQuery("GetAllProducts");

        query.execute();

        return query.getResultList();
    }
}
