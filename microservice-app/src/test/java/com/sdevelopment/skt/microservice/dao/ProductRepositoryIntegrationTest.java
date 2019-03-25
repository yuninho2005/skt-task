package com.sdevelopment.skt.microservice.dao;

import com.sdevelopment.skt.common.domain.Product;
import com.sdevelopment.skt.microservice.dao.impl.ProductRepositoryImpl;
import com.sdevelopment.skt.microservice.exception.DuplicatedProductEception;
import com.sdevelopment.skt.microservice.service.ProductService;
import com.sdevelopment.skt.microservice.service.impl.ProductServiceImpl;
import org.hibernate.jpa.internal.EntityManagerImpl;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
//@DataJpaTest
@ActiveProfiles("test")
@SpringBootTest
public class ProductRepositoryIntegrationTest {

    @TestConfiguration
    static class EmployeeServiceImplTestContextConfiguration {

        @Bean
        public ProductRepository productRepository() {
            return new ProductRepositoryImpl();
        }

    }

    //@PersistenceContext
    //private EntityManager entityManager;

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void checkIfSelectAllWorking() {
        List<Product> before = new LinkedList<>();

        before.addAll(productRepository.getAllProducts());

        List<Product> after = productRepository.getAllProducts();

        assertThat(before.size())
                .isEqualTo(after.size());
    }

    @Test(expected = DuplicatedProductEception.class)
    public void ifSameNameShouldThrowException() throws DuplicatedProductEception {
        Product product = new Product();
        product.setName("product test");

        productRepository.saveProduct(product);

    }
}
