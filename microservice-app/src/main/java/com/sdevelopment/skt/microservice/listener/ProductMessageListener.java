package com.sdevelopment.skt.microservice.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sdevelopment.skt.common.domain.Product;
import com.sdevelopment.skt.microservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
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
        ObjectMapper mapper = new ObjectMapper();

        String jsonInString = message.get("id"); // TODO Research what should i ask for here to the map

        Product product = null;
        try {
            product = mapper.readValue(jsonInString, Product.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        productService.saveProduct(product);
    }
}

