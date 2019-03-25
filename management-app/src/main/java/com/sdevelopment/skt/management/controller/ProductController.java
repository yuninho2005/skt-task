package com.sdevelopment.skt.management.controller;

import com.sdevelopment.skt.common.domain.Product;
import com.sdevelopment.skt.management.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
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
    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping("/create-product")
    public String createProduct(ModelMap model) {
        Product product = new Product();
        model.addAttribute("product", product);

        return "newProduct";
    }

    @RequestMapping("/products")
    public String listProducts(ModelMap model) {
        List<Product> products = new LinkedList<>();

        Product p1 = new Product();
        p1.setName("Product 1");

        Product p2 = new Product();
        p1.setName("Product 2");

        products.add(p1);
        products.add(p2); // Two elements just to show in the view.

        model.addAttribute("products", products);

        return "listProducts";
    }

    @RequestMapping(value = "/product-form", method = RequestMethod.POST)
    public String processNewProduct(@Valid @ModelAttribute("product")Product product,
                                    BindingResult result, ModelMap model, RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            return "formError";
        }

        productService.saveProduct(product);

        return "listProducts";
    }
}
