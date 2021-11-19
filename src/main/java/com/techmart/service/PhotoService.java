package com.techmart.service;

import com.techmart.model.Photo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PhotoService {
    Photo create(Photo photo);
    void update(Integer id);
    void delete(int id);
    List<Photo> findAll();
    List<Photo> findAllByProduct(Integer id);
    Photo setMainPhoto(Integer id, Integer productId);
    List<Photo> savePhotos(String folder, MultipartFile[] files, Integer productId);
}
