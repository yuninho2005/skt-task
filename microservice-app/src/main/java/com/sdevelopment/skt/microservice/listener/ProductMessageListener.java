package com.sdevelopment.skt.microservice.listener;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.sdevelopment.skt.common.domain.Product;
import com.sdevelopment.skt.common.util.ProductSerializer;
import com.sdevelopment.skt.microservice.service.ProductService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * This is the queue listener class, its receiveMessage() method ios invoked with the
 * message as the parameter.
 */
@Component
public class ProductMessageListener {


    @Value("${spring.rabbitmq.listqueue}")
    private String sendingQueue;

    @Autowired
    private ProductService productService;

    @Autowired
    private RabbitTemplate rabbitTemplate;


    public void receiveMessage(Map<String, String> message) {
        ObjectMapper mapper = new ObjectMapper();

        String jsonInString = message.get("product");

        Product product = null;
        try {
            product = mapper.readValue(jsonInString, Product.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        productService.saveProduct(product);

        // TODO Exception handling in case product was not saved
        sendProductListBackToTheQueue();
    }

    private void sendProductListBackToTheQueue() {
        List<Product> products = productService.getAllProducts();
        Map<String, String> actionmap = new HashMap<>();

        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = "";

        SimpleModule module = new SimpleModule("ProductSerializer", new Version(1, 0, 0, null, null, null));
        module.addSerializer(Product.class, new ProductSerializer());
        mapper.registerModule(module);

        try {
            //products.forEach( p -> mapper.writeValueAsString(p));

            jsonInString = mapper.writeValueAsString(products);
        } catch (IOException e) {
            e.printStackTrace();
        }

        actionmap.put("products", jsonInString);
        System.out.println(jsonInString);

        rabbitTemplate.convertAndSend(sendingQueue, actionmap);
    }
}

