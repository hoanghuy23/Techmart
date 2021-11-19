package com.techmart.service;

import com.techmart.model.Category;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CategoryService {
    Category create(Category category);
    Category update(Category category);
    void delete(Integer id);
    List<Category> findAll();

    public List<Category> findParentCategories();

    public List<Category> findSubCategoriesById(Integer id);

    Category getCategory(Integer id);

    List<Category> findAllByStatus();
}
