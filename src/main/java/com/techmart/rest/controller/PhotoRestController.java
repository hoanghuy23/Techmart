package com.techmart.rest.controller;

import com.techmart.model.Photo;
import com.techmart.service.PhotoService;
import com.techmart.utility.FileManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/photos")
public class PhotoRestController {
    @Autowired
    private PhotoService photoService;

    @Autowired
    private FileManager fileService;

    @GetMapping("/prd/{id}")
    public List<Photo> getAllByProductId(@PathVariable Integer id) {
        return photoService.findAllByProduct(id);
    }

    @PostMapping("/main/{id}/{productId}")
    public Photo setMainPhoto(@PathVariable Integer id, @PathVariable Integer productId) {

        return photoService.setMainPhoto(id,productId);
    }

    @GetMapping("/{folder}/{file}")
    public byte[] download(@PathVariable("folder") String folder , @PathVariable("file") String file){
        return fileService.read(folder,file);
    }

    @PostMapping("/{folder}/{productId}")
    public List<Photo> upload (@PathVariable("folder") String folder,
                                @PathVariable("files") MultipartFile[] files,
                                @PathVariable("productId") Integer productId){
        return photoService.savePhotos(folder,files,productId);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Integer id){
        System.out.println("***********PREPARE FOR SETTING PHOTO WITH ID: "+id);
        photoService.delete(id);
    }

    @PutMapping("{id}")
    public void revert(@PathVariable("id") Integer id){
        System.out.println("***********PREPARE FOR SETTING PHOTO WITH ID: "+id);
        photoService.update(id);
    }

    @GetMapping("/{folder}")
    public List<String> list(@PathVariable("folder") String folder ){
        return fileService.list(folder);
    }

}
