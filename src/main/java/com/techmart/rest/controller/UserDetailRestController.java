package com.techmart.rest.controller;

import com.techmart.model.UserDetail;
import com.techmart.service.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/userdetails")
public class UserDetailRestController {
    @Autowired
    UserDetailService userDetailService;

    @PostMapping
    public UserDetail create(@RequestBody UserDetail userDetail){
        return userDetailService.create(userDetail);
    }

    @GetMapping("email/{email}")
    public UserDetail getUserDetailByEmail(@PathVariable("email") String email){
        return userDetailService.getUserDetailByEmail(email);
    }
    @PutMapping("{id}")
    public UserDetail update(@PathVariable("id") String id, @RequestBody UserDetail userDetail) {
    	return userDetailService.update(userDetail);
    }
    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") int id) {
    	userDetailService.delete(id);
    }
}
