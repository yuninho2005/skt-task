package com.sdevelopment.skt.management.controller;

import com.sdevelopment.skt.common.domain.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
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

    @RequestMapping(value = "/product-form", method = RequestMethod.POST)
    public String processNewProduct(@Valid @ModelAttribute("product")Product product,
                                    BindingResult result, ModelMap model) {
        product.getName();
        System.out.println(product.getName());

        return "listProducts";
    }
}
