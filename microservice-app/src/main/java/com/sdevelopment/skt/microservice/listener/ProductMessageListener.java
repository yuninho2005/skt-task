package com.sdevelopment.skt.microservice.listener;

import com.sdevelopment.skt.common.domain.Product;
import com.sdevelopment.skt.microservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Map;

/**
 * This is the queue listener class, its receiveMessage() method ios invoked with the
 * message as the parameter.
 */
@Component
public class ProductMessageListener {

    @Autowired
    private ProductService productService;


    public void receiveMessage(Map<String, String> message) {
        message.get("id"); // TODO Convert from json to object
        Product product;

        productService.saveProduct(product);
    }
}

