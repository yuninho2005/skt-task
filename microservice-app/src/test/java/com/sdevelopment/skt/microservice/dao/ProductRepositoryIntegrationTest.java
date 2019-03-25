package com.sdevelopment.skt.microservice.dao;

import com.sdevelopment.skt.common.domain.Product;
import com.sdevelopment.skt.microservice.dao.impl.ProductRepositoryImpl;
import com.sdevelopment.skt.microservice.exception.DuplicatedProductEception;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
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
