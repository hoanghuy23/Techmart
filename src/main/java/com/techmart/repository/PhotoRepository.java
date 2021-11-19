package com.techmart.repository;

import com.techmart.model.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PhotoRepository extends JpaRepository<Photo, Integer> {
    @Query("SELECT p FROM Photo p WHERE p.product.id = ?1")
    List<Photo> getAllByProduct(Integer id);

}