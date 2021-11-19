package com.techmart.service.impl;

import com.techmart.model.Photo;
import com.techmart.model.Product;
import com.techmart.repository.PhotoRepository;
import com.techmart.service.PhotoService;
import com.techmart.service.ProductService;
import com.techmart.utility.FileManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
public class PhotoServiceImpl implements PhotoService {
    @Autowired
    PhotoRepository photoRepository;

    @Autowired
    private FileManager fileService;

    @Autowired
    private ProductService productService;

    @Override
    public Photo create(Photo photo){ return photoRepository.save(photo);}

    @Override
    public void update(Integer id){
        Photo photo = photoRepository.findById(id).get();
        photo.setStatus(true);
        photoRepository.save(photo);
    }

    @Override
    public void delete(int id){
        Photo p = photoRepository.findById(id).get();
        p.setStatus(false);
        photoRepository.save(p);
    }

    @Override
    public List<Photo> findAll(){ return photoRepository.findAll();}

    @Override
    public List<Photo> findAllByProduct(Integer id) {
        return photoRepository.getAllByProduct(id);
    }

    @Override
    public Photo setMainPhoto(Integer id , Integer productId) {
        List<Photo> photos = findAllByProduct(productId);
        for(Photo p : photos){
            if(p.isMain()){
                p.setMain(false);
                photoRepository.save(p);
            }
        }
        Photo photo = photoRepository.findById(id).get();
        photo.setMain(true);
        return photoRepository.save(photo);
    }

    @Override
    public List<Photo> savePhotos(String folder, MultipartFile[] files, Integer productId) {
        if(files != null || files.length != 0) {
            List<String> listPhotoNames = fileService.save(folder,files);
            List<Photo> photoList = new ArrayList<>();
            Product p = productService.findById(productId);
            System.out.println("---SAVING PHOTO----");
            for(String name : listPhotoNames){
                System.out.println("---PHOTO NAME: "+name);
                Photo photo = new Photo();
                photo.setName(name);
                photo.setStatus(true);
                photo.setProduct(p);
                photo.setMain(false);
                photoRepository.save(photo);
                photoList.add(photo);
            }
            return photoList;
        }
        return null;
    }

}
