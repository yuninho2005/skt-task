package com.sdevelopment.skt.management.service.impl;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.sdevelopment.skt.common.domain.Product;
import com.sdevelopment.skt.common.util.ProductDeserializer;
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
        List<Product> products = null;
        Map<String, String> message = (Map<String, String>)rabbitTemplate.receiveAndConvert(receivingQueue);

        if(message != null) {
            ObjectMapper mapper = new ObjectMapper();

            String jsonInString = message.get("products");
            System.out.println(jsonInString);

            SimpleModule module = new SimpleModule("ProductDeserializer", new Version(1, 0, 0, null, null, null));
            module.addDeserializer(Product.class, new ProductDeserializer());
            mapper.registerModule(module);

            try {
                products = mapper.readValue(jsonInString, new TypeReference<List<Product>>(){});

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return products;
    }
}
