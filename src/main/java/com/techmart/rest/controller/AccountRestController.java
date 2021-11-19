package com.techmart.rest.controller;

import com.techmart.model.Account;
import com.techmart.model.UserDetail;
import com.techmart.service.AccountService;
import com.techmart.service.SendMailService;
import com.techmart.utility.*;

import net.bytebuddy.utility.RandomString;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Optional;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/accounts")
public class AccountRestController {
    @Autowired
    AccountService accountService;
    @Autowired
    SendMailService mailService;
    
    @GetMapping
    public List<Account> getAccount(@RequestParam("admin") Optional<Boolean> admin){
    	if(admin.orElse(false)) {
    		return accountService.getAdministrators();
    	}
    	return accountService.findAll();
    }

    @PostMapping
    public Account create(@RequestBody Account account, HttpServletRequest request) 
    		throws UnsupportedEncodingException, MessagingException{
    	/*Create VetificationCode Send Mail (open)*/
    	String randomcode = RandomString.make(64);
    	account.setVetificationCode(randomcode);
    	String url = Utility.getURL(request);
    	mailService.SendEmail(account, url);
    	/*Create VetificationCode Send Mail (close)*/
        return accountService.create(account);
    }
    
    @PostMapping("admin")
    public Account createAdmin(@RequestBody Account account) {
    	return accountService.create(account);
    }

	@GetMapping("info/{info}")
    public Account getAccountByInfo(@PathVariable("info") Integer id){
        return accountService.getAccountByInfo(id);
    }
	
    @GetMapping("username/{username}")
    public Account getAccountByInfo(@PathVariable("username") String username){
        return accountService.findById(username);
    }
    
    @PutMapping("{username}")
    public Account update(@PathVariable("username") String username, @RequestBody Account account) {
    	return accountService.update(account);
    }
}
