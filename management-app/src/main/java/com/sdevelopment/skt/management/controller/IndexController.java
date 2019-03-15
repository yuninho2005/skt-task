package com.sdevelopment.skt.management.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
public class IndexController {

    @RequestMapping("/")
    public String home(Map<String, Object> model) {
        model.put("message", "Welcome to the Products Management Portal");
        return "index";
    }
}
