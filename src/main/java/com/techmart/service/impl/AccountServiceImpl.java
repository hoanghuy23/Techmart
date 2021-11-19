package com.techmart.service.impl;

import com.techmart.model.Account;
import com.techmart.repository.AccountRepository;
import com.techmart.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.transaction.Transactional;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {
    @Autowired
    AccountRepository accountRepository;

    @Override
    public Account create(Account account){ return accountRepository.save(account);}

    @Override
    public Account update(Account account){ return  accountRepository.save(account);}

    @Override
    public void delete(String username){ accountRepository.deleteById(username);}

    @Override
    public List<Account> findAll(){ return accountRepository.findAll();}

    @Override
    public Account getAccountByInfo(Integer id){ return  accountRepository.getAccountByInfo(id);}

	@Override
	public boolean status(String verifyCode) {
		Account account = accountRepository.findByVerifyCode(verifyCode);
		if(account == null || account.isStatus()) {
			return false;
		}else {
			accountRepository.status(account.getUsername());
			return true;
		}
	}

    @Override
    public Account findByUsername(String username) {
        return  accountRepository.findByUsername(username);
    }

    @Override
    public Account findById(String username) {
        return accountRepository.getAccountByUsername(username);
    }

	@Override
	public List<Account> getAdministrators() {
		
		return accountRepository.getAdministrators();
	}

}
