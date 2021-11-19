package com.techmart.controller;

import com.techmart.bean.SearchPrice;
import com.techmart.model.Product;
import com.techmart.service.CategoryService;
import com.techmart.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Controller
public class ProductController {
    @Autowired
    ProductService productService;
    @Autowired
    CategoryService categoryService;

    @RequestMapping("/product/category/{id}")
    public String category(@PathVariable("id") Integer id, Model model){
        if(categoryService.getCategory(id).getCategory().getCategory().getId()==40) {
        	Product pro = productService.findProductByPC(id);
        	System.out.println("-------------------------------"+pro.getId());
        	return "redirect:/product/detail/"+pro.getId();
        }
        model.addAttribute("category", id);
        return "product/index";        
    }
    @RequestMapping("/product/name")
    public String name(@RequestParam("search") String name, Model model){
        model.addAttribute("name", name);
        return "product/index";
    }
  
    @RequestMapping("/product/detail/{id}")
    public String detail(@PathVariable("id") Integer id, Model model) {
    	Product item = productService.findById(id);
    	model.addAttribute("item", item);
    	
    	List<Product> list = productService.findProductByCategory(item.getCategory().getId());
    	
    	switch (item.getCategory().getCategory().getCategory().getId()) {
      case 40:
        return "product/product-detailsPC";
      case 82:
          model.addAttribute("pro", list);
         return "product/product-detailsBP";
      case 106:
         model.addAttribute("pro", list);
         return "product/product-detailsChuot";
      case 124:
          model.addAttribute("pro", list);
          return "product/product-detailsTai";
      case 143:
          model.addAttribute("pro", list);
          return "product/product-detailsLoa";
      default:
        model.addAttribute("pro", list);
        return "product/product-details";
		  }  	    	
    }
}
