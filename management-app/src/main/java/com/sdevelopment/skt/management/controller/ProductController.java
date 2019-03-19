package com.sdevelopment.skt.management.controller;

import com.sdevelopment.skt.common.domain.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Controller
public class ProductController {

    @RequestMapping("/create-product")
    public String createProduct(ModelMap model) {
        Product product = new Product();
        model.addAttribute("product", product);

        return "newProduct";
    }

    @RequestMapping("/products")
    public String listProducts(ModelMap model) {
        List<Product> products = new LinkedList<>(); // Initially empty

        model.addAttribute("products", products);

        return "listProducts";
    }

    @RequestMapping(value = "/product-form", method = RequestMethod.POST)
    public String processNewProduct(@Valid @ModelAttribute("product")Product product,
                                    BindingResult result, ModelMap model, RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            return "formError";
        }

        return "listProducts";
    }
}
