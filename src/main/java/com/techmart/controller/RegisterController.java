package com.techmart.controller;

import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.techmart.service.AccountService;


@Controller
public class RegisterController {
	@Autowired
	AccountService service;
	
	@RequestMapping("/account/register")
	public String register(){
		return "account/register";
	}
	
	@GetMapping("/verify")
	public String verifyAccount(@Param("code") String code) {
		boolean verify = service.status(code);
		
		return verify?"home/index":"account/register";
	}

}
