package com.techmart.rest.controller;

import com.techmart.model.Product;
import com.techmart.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/products")
public class ProductRestController {
    @Autowired
    private ProductService productService;

    @GetMapping("/{id}")
    public List<Product> getAllProductsByCategory(@PathVariable Integer id){

        return productService.findProductsByCategory(id);
    }

    @PostMapping
    public Product create(@RequestBody Product product){
        product.setCreateDate(new Date());
        product.setModifiedDate(new Date());
//        product.setCreatedBy()
//        product.setModifiedBy();
        return productService.create(product);

    }

    @PutMapping("/{id}")
    public Product update(@PathVariable("id") Integer id, @RequestBody Product product){
        return productService.update(product);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer id) {
        productService.delete(id);
    }

    @GetMapping("category/{id}")
    public List<Product> findAllByCategory(@PathVariable("id") Integer id){
        return productService.findProductByCategory(id);
    }

    @GetMapping("name/{name}")
    public List<Product> findAllByName(@PathVariable("name") String name){
        return productService.findProductByName(name);
    }

    @GetMapping("categorypages/{id}")
    public List<Integer> getListPageByCategory(@PathVariable("id") Integer id){
        Pageable pageable = PageRequest.of(0,9);
        Page<Product> page = productService.getTotalPageByCategory(id, pageable);
        int totalPages = page.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            return pageNumbers;
        }
        return null;
    }

    @GetMapping("namepages/{name}")
    public List<Integer> getListPageByName(@PathVariable("name") String name){
        Pageable pageable = PageRequest.of(0,9);
        Page<Product> page = productService.getTotalPageByName(name, pageable);
        int totalPages = page.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            return pageNumbers;
        }
        return null;
    }
  
    @GetMapping("categoryandprice/{id}/{from}/{to}")
    public List<Product> findAllByCategoryandPrice(@PathVariable("id") Integer id, @PathVariable("from") double from, @PathVariable("to") double to){
        return productService.findProductByCategoryandPrice(id, from, to);
    }

    @GetMapping("categoryandpricepages/{id}/{from}/{to}")
    public List<Integer> getListPageByCategoryandPrice(@PathVariable("id") Integer id, @PathVariable("from") double from, @PathVariable("to") double to){
        Pageable pageable = PageRequest.of(0,9);
        Page<Product> page = productService.getTotalPageByCategoryandPrice(id, from, to, pageable);
        int totalPages = page.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            return pageNumbers;
        }
        return null;
    }

    @GetMapping("nameandprice/{name}/{from}/{to}")
    public List<Product> findAllByNameandPrice(@PathVariable("name") String name, @PathVariable("from") double from, @PathVariable("to") double to){
        return productService.findProductByNameandPrice(name, from, to);
    }

    @GetMapping("nameandpricepages/{name}/{from}/{to}")
    public List<Integer> getListPageByNameandPrice(@PathVariable("name") String name, @PathVariable("from") double from, @PathVariable("to") double to){
        Pageable pageable = PageRequest.of(0,9);
        Page<Product> page = productService.getTotalPageByNameandPrice(name, from, to, pageable);
        int totalPages = page.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            return pageNumbers;
        }
        return null;
    }

    @GetMapping("/category-home/{id}")
    public List<Product> getAllProductHome(@PathVariable Integer id){
        return productService.findProductByCategoryandViews(id);
    }
}
