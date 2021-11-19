package com.techmart.rest.controller;

import com.techmart.model.Category;
import com.techmart.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/categories")
public class CategoryRestController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("parent")
    public List<Category> getParentCategory(){
        return categoryService.findParentCategories();
    }

    @GetMapping("sub/{id}")
    public List<Category> getSubCategory(@PathVariable Integer id){
        return categoryService.findSubCategoriesById(id);
    }

    @GetMapping
    public List<Category> findAll(){
        return categoryService.findAllByStatus();
    }

    @PostMapping
    public Category create(@RequestBody Category category){
        return categoryService.create(category);
    }

    @PutMapping("{id}")
    public Category update(@PathVariable("id") Integer id, @RequestBody Category category){
        return categoryService.update(category);
    }

    @GetMapping("{id}")
    public Category getCategory(@PathVariable("id") Integer id){
        return categoryService.getCategory(id);
    }
}
