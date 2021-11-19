package com.techmart.service.impl;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.techmart.model.Account;
import com.techmart.service.SendMailService;

@Service
@Transactional
public class SendMailServicelmpl implements SendMailService{
    @Autowired
    private JavaMailSender mailSender;
    
	public void SendEmail(Account account, String url){
		sendVeritificationEmail(account, url);
	}
	
	private void sendVeritificationEmail(Account account, String url)   	{
		try {
			String subject = "Vui lòng xác nhận đăng ký tài khoản";
			String sendName = "TechMart";
			String mailContent = "<p>Dear: " + account.getInfo().getFullName()+",</p>";
			mailContent += "<p>Vui lòng xác thực để hoàn tất đăng ký tài khoản: "+account.getUsername()+"</p>";
			
			String VerifyURL = url + "/verify?code=" + account.getVetificationCode();
			
			mailContent += "<h3><a href='" + VerifyURL + "'>XÁC THỰC</a></h3>";
			mailContent += "<p>Cảm ơn.<br>Thân ái.<br>TechMart.</p>";
			
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message);
			
			helper.setFrom("thhuy01@gmail.com",sendName);
			helper.setTo(account.getInfo().getEmail());
			helper.setSubject(subject);
			
			helper.setText(mailContent, true);
			mailSender.send(message);
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
	}
}
