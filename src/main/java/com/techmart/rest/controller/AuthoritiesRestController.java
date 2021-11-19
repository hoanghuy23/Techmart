package com.techmart.rest.controller;

import com.techmart.model.Authorities;
import com.techmart.service.AuthoritiesService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/authorities")
public class AuthoritiesRestController {
    @Autowired
    AuthoritiesService authoritiesService;
    
    @GetMapping 
    public List<Authorities> findAll(@RequestParam("admin") Optional<Boolean> admin){
    	if(admin.orElse(false)) {
    		return authoritiesService.findAuthoritiesOfAdministrators();
    	}
    	return authoritiesService.findAll();
    }

    @PostMapping
    public Authorities create(@RequestBody Authorities authorities){
        return authoritiesService.create(authorities);
    }
    
    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Integer id) {
    	authoritiesService.delete(id);
    }
}
