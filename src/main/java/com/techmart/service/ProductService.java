package com.techmart.service;

import com.techmart.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface ProductService {
    Product create(Product product);
    Product update(Product product);
    void delete(int id);
    List<Product> findAll();
    List<Product> findProductsByCategory(Integer id);
    Product findById(Integer id);
    List<Product> findProductByCategory(Integer id);
    List<Product> findProductByName(String name);
    Page<Product> getTotalPageByCategory(Integer id, Pageable pageable);
    Page<Product> getTotalPageByName(String name, Pageable pageable);
    Product findProductByPC(Integer id);
    List<Product> findProductByCategoryandPrice(Integer id, double from, double to);
    Page<Product> getTotalPageByCategoryandPrice(Integer id, double from, double to, Pageable pageable);
    List<Product> findProductByNameandPrice(String name, double from, double to);
    Page<Product> getTotalPageByNameandPrice(String name, double from, double to, Pageable pageable);
    List<Product> getProductByisFeatured();
    List<Product> getProductByViews();
    List<Product> findProductByCategoryandViews(Integer id);
}
