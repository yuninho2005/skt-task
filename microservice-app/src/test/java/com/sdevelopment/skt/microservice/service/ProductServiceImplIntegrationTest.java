package com.sdevelopment.skt.microservice.service;

import com.sdevelopment.skt.common.domain.Product;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
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
    }

    @Autowired
    private ProductService productService;

    @MockBean
    private ProductRepository productRepository;

    @Before
    public void setUp() {
        Product product = new Product();
        product.setName("product 1");

        Mockito.when(productRepository.findByName(product.getName()))
                .thenReturn(product);
    }

    @Test
    public void whenValidNameThenProductShouldBeFound() {
        String name = "alex";
        Product found = productService.getProductByName(name);

        assertThat(found.getName())
                .isEqualTo(name);
    }
}
