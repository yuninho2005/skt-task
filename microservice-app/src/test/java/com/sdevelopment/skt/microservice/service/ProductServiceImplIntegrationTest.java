package com.sdevelopment.skt.microservice.service;

import com.sdevelopment.skt.common.domain.Product;
import com.sdevelopment.skt.microservice.dao.ProductRepository;
import com.sdevelopment.skt.microservice.dao.impl.ProductRepositoryImpl;
import com.sdevelopment.skt.microservice.service.impl.ProductServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.PersistenceException;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest
public class ProductServiceImplIntegrationTest {

    /**
     * To check the Service class, we need to have an instance of Service class created and available as a @Bean
     * so that we can @Autowire it in our test class. This configuration is achieved by using the @TestConfiguration annotation.
     *
     * During component scanning, we might find components or configurations created only for specific tests
     * accidentally get picked up everywhere. To help prevent that, Spring Boot provides @TestConfiguration annotation
     * that can be used on classes in src/test/java to indicate that they should not be picked up by scanning.
     */
    @TestConfiguration
    static class EmployeeServiceImplTestContextConfiguration {

        @Bean
        public ProductService productService() {
            return new ProductServiceImpl();
        }

        @Bean
        public ProductRepository productRepository() {
            return new ProductRepositoryImpl();
        }
    }

    @Autowired
    private ProductService productService;

    @Test(expected = PersistenceException.class)
    public void whenSavingDuplicatedProduct_thenHandleException() {
        Product product = new Product();
        product.setName("Duplicated name");

        productService.saveProduct(product);
    }

    @Test
    public void whenGetAllProducts_thenReturnProductList() {
        List<Product> products = productService.getAllProducts();

        if(products != null)
            assertThat(products.size() > 0);
    }
}
