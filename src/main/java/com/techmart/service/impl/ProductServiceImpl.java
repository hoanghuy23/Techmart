package com.techmart.service.impl;

import com.techmart.model.Product;
import com.techmart.repository.ProductRepository;
import com.techmart.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product create(Product product) {
//        product.setStatus(true);
        return productRepository.save(product);
    }

    @Override
    public Product update(Product product) { return productRepository.save(product); }

    @Override
    public void delete(int id) {
        Product product = productRepository.findById(id).get();
        product.setStatus(false);
        productRepository.save(product);
    }

    @Override
    public List<Product> findAll(){ return productRepository.findAll(); }


    Map<Integer,List<Product>> listHashMap = new HashMap<>();
    @Override
    public List<Product> findProductsByCategory(Integer id) {
        List<Product> listProduct = null;
//        if(listHashMap.get(id) == null){
//            listProduct = productRepository.findProductsByCategory(id);
//            listHashMap.put(id,listProduct);
//        }else{
//            listProduct = listHashMap.get(id);
//        }
        listProduct = productRepository.findProductsByCategory(id);
        return listProduct;
    }

    @Override
    public Product findById(Integer id) {
        return productRepository.findById(id).get();
    }

    @Override
    public List<Product> findProductByCategory(Integer id) {
        return productRepository.findProductByCategory(id);
    }

    @Override
    public List<Product> findProductByName(String name) {
        return productRepository.findProductByName("%"+name+"%");
    }

    @Override
    public Page<Product> getTotalPageByCategory(Integer id, Pageable pageable) {
        return productRepository.getTotalPageByCategory(id, pageable);
    }

    @Override
    public List<Product> findProductByCategoryandPrice(Integer id, double from, double to) {
        return productRepository.findProductByCategoryandPrice(id, from, to);
    }

    @Override
    public Page<Product> getTotalPageByCategoryandPrice(Integer id, double from, double to, Pageable pageable) {
        return productRepository.getTotalPageByCategoryandPrice(id, from, to, pageable);
    }

    @Override
    public List<Product> findProductByNameandPrice(String name, double from, double to) {
        return productRepository.findProductByNameandPrice("%"+name+"%", from, to);
    }

    @Override
    public Page<Product> getTotalPageByNameandPrice(String name, double from, double to, Pageable pageable) {
        return productRepository.getTotalPageByNameandPrice("%"+name+"%", from, to, pageable);
    }


    @Override
    public Page<Product> getTotalPageByName(String name, Pageable pageable) {
        return productRepository.getTotalPageByName("%"+name+"%", pageable);
    }

	@Override
	public Product findProductByPC(Integer id) {		
		return productRepository.findProductByPC(id);
	}

    @Override
    public List<Product> getProductByisFeatured() {
        return productRepository.getProductByisFeatured();
    }

    @Override
    public List<Product> getProductByViews() {
        return productRepository.getProductByViews();
    }

    @Override
    public List<Product> findProductByCategoryandViews(Integer id) {
        return productRepository.findProductByCategoryandViews(id);
    }
}
