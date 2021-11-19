package com.techmart.controller;

import com.techmart.model.Product;
import com.techmart.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    ProductService productService;

    @RequestMapping("/index")
    public String home(Model model){
        List<Product> features = productService.getProductByisFeatured();
        model.addAttribute("features", features);
        List<Product> views = productService.getProductByViews();
        model.addAttribute("views", views);
        return "home/index";
    }

    @RequestMapping({"/admin", "/admin/home/index"})
    public String admin() {
        return "redirect:/admin/index.html";
    }
}

