package com.techmart.repository;

import com.techmart.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    @Query("from Category c where c.category = null")
    public List<Category> findParentCategories();

    @Query("SELECT c from Category c where c.category.id = ?1")
    public List<Category> findSubCategoriesById(Integer id);

    @Query("SELECT c FROM Category c WHERE c.status = true")
    List<Category> findAllByStatus();
}