package com.sdevelopment.skt.management.service;

import com.sdevelopment.skt.common.domain.Product;
import com.sdevelopment.skt.management.service.impl.ProductServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class ProductServiceImplIntegrationTest {

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
    private RabbitTemplate rabbitTemplate;


    @Test
    public void whenSaveProduct_thenSendToQueue() {
        // given
        Product product = new Product();
        product.setName("product test");

        productService.saveProduct(product);

    }
}
