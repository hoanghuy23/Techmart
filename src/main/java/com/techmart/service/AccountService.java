package com.techmart.service;

import com.techmart.model.Account;

import java.util.List;

public interface AccountService {
    Account create(Account account);
    Account update(Account account);
    void delete(String username);
    List<Account> findAll();
    Account getAccountByInfo(Integer id);
    boolean status(String verifyCode);
    Account findByUsername(String username);
    Account findById(String username);
	List<Account> getAdministrators();
}
