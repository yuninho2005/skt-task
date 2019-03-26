package com.sdevelopment.skt.management.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sdevelopment.skt.common.domain.Product;
import com.sdevelopment.skt.management.service.ProductService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Component
public class ProductServiceImpl implements ProductService {

    @Value("${spring.rabbitmq.savequeue}")
    private String sendingQueue;

    @Value("${spring.rabbitmq.listqueue}")
    private String receivingQueue;

    @Autowired
    private RabbitTemplate rabbitTemplate;


    @Override
    public void saveProduct(Product product) {
        Map<String, String> actionmap = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();

        String jsonInString = "";

        try {
            jsonInString = mapper.writeValueAsString(product);
        } catch (IOException e) {
            e.printStackTrace();
        }

        actionmap.put("product", jsonInString);

        rabbitTemplate.convertAndSend(sendingQueue, actionmap);
    }

    @Override
    public List<Product> getAllProducts() {
        Map<String, String> message = (Map<String, String>)rabbitTemplate.receiveAndConvert(receivingQueue);

        ObjectMapper mapper = new ObjectMapper();

        String jsonInString = message.get("products");

        List<Product> products = null;
        try {
            products = mapper.readValue(jsonInString, LinkedList.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return products;
    }
}
