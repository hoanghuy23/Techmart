package com.techmart.repository;

import com.techmart.model.Product;
import com.techmart.report.reportInventory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Integer> {
    @Query("SELECT p FROM Product p WHERE p.category.category.id = ?1 OR p.category.category.category.id =?1 AND p.status = true")
    List<Product> findProductsByCategory(Integer id);

    @Query("SELECT p FROM Product p WHERE  p.category.id = ?1 AND p.status = true")
    List<Product> findProductByCategory(Integer id);

    @Query("SELECT p FROM Product p WHERE  p.category.id = ?1 AND p.status = true")
    Product findProductByPC(Integer id);

    @Query("SELECT new reportInventory(o.category.category.category, sum(o.price), count(o)) "
            + "FROM Product o "
            + "GROUP BY o.category.category.category "
            + "ORDER BY sum(o.price) DESC")
    List<reportInventory> getInventory();


    @Query("SELECT p FROM Product p WHERE  p.name LIKE ?1 AND p.status = true")
    List<Product> findProductByName(String name);

    @Query("SELECT p FROM Product p WHERE  p.category.id = ?1 AND (p.price BETWEEN ?2 AND ?3)  AND p.status = true")
    List<Product> findProductByCategoryandPrice(Integer id, double from, double to);

    @Query("SELECT p FROM Product p WHERE  p.name LIKE ?1 AND (p.price BETWEEN ?2 AND ?3) AND p.status = true")
    List<Product> findProductByNameandPrice(String name, double from, double to);

    @Query("SELECT p FROM Product p WHERE  p.category.id = ?1 AND p.status = true")
    Page<Product> getTotalPageByCategory(Integer id, Pageable pageable);

    @Query("SELECT p FROM Product p WHERE  p.name LIKE ?1 AND p.status = true")
    Page<Product> getTotalPageByName(String name, Pageable pageable);

    @Query("SELECT p FROM Product p WHERE  p.category.id = ?1 AND (p.price BETWEEN ?2 AND ?3) AND p.status = true")
    Page<Product> getTotalPageByCategoryandPrice(Integer id, double from, double to, Pageable pageable);

    @Query("SELECT p FROM Product p WHERE  p.name LIKE ?1 AND (p.price BETWEEN ?2 AND ?3) AND p.status = true")
    Page<Product> getTotalPageByNameandPrice(String name, double from, double to, Pageable pageable);

    @Query(value = "{CALL sp_getProductByIsFeatured()}", nativeQuery = true)
    List<Product> getProductByisFeatured();

    @Query(value = "{CALL sp_getProductByViews}", nativeQuery = true)
    List<Product> getProductByViews();

    @Query(value = "{CALL sp_getProductByCategoryandViews(:categoryid)}", nativeQuery = true)
    List<Product> findProductByCategoryandViews(@Param("categoryid") Integer id);

}
