package com.sdevelopment.skt.management.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
public class ProductController {

    @RequestMapping("/create-product")
    public String createProduct(Map<String, Object> model) {
        return "newProduct";
    }

    @RequestMapping("/products")
    public String listProducts(Map<String, Object> model) {
        return "listProducts";
    }
}
