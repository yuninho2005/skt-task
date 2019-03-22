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
import java.util.List;
import java.util.Map;

@Component
public class ProductServiceImpl implements ProductService {

    @Value("${spring.rabbitmq.savequeue}")
    private String sendingQueue;

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
        return null;
    }
}
