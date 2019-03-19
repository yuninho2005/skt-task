package com.sdevelopment.skt.microservice.dao;

import com.sdevelopment.skt.common.domain.Product;
import com.sdevelopment.skt.microservice.dao.impl.ProductRepositoryImpl;
import com.sdevelopment.skt.microservice.service.ProductService;
import com.sdevelopment.skt.microservice.service.impl.ProductServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ProductRepositoryIntegrationTest {

    @TestConfiguration
    static class EmployeeServiceImplTestContextConfiguration {

        @Bean
        public ProductRepository productRepository() {
            return new ProductRepositoryImpl();
        }
    }

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ProductRepository productRepository;

    //@Before
    public void setUp() {
        List<Product> products = new ArrayList<>();

        Product product = new Product();
        product.setName("product test");

        products.add(product);

        Mockito.when(productRepository.getAllProducts())
                .thenReturn(products);
    }

    //@Test
    public void whenGetAllProducts_thenReturnProductList() {
        // given
        Product product = new Product();
        product.setName("product test");

        entityManager.persist(product);
        entityManager.flush();

        // when
        List<Product> products = productRepository.getAllProducts();

        // then
        assertThat(products.size())
                .isEqualTo(1);
    }

    @Test
    public void emptyTestForNow() {}
}
