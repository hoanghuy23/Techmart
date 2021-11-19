package com.techmart.service.impl;

import com.techmart.model.Category;
import com.techmart.repository.CategoryRepository;
import com.techmart.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public Category create(Category category){ return categoryRepository.save(category);}

    @Override
    public Category update(Category category){ return categoryRepository.save(category);}

    @Override
    public void delete(Integer id){ categoryRepository.deleteById(id);}

    @Override
    public List<Category> findAll(){ return categoryRepository.findAll();}

    @Override
    public List<Category> findParentCategories() {
        return categoryRepository.findParentCategories();
    }

    Map<Integer,List<Category>> listHashMap = new HashMap<>();
    @Override
    public List<Category> findSubCategoriesById(Integer id) {
        List<Category> listCategory = null;
        if(listHashMap.get(id) == null || !listHashMap.containsKey(id)){
            listCategory = categoryRepository.findSubCategoriesById(id);
            listHashMap.put(id,listCategory);
        }else{
            listCategory = listHashMap.get(id);
        }
        return listCategory;
    }

    @Override
    public Category getCategory(Integer id) {
        return categoryRepository.findById(id).get();
    }

    @Override
    public List<Category> findAllByStatus() {
        return categoryRepository.findAllByStatus();
    }
}
