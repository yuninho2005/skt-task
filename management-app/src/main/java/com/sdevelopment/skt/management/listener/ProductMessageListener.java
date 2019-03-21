package com.sdevelopment.skt.management.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sdevelopment.skt.common.domain.Product;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Component
public class ProductMessageListener {

    public void receiveMessage(Map<String, String> message) {
        ObjectMapper mapper = new ObjectMapper();

        String jsonInString = message.get("products");

        List<Product> products = null;
        try {
            products = mapper.readValue(jsonInString, LinkedList.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
