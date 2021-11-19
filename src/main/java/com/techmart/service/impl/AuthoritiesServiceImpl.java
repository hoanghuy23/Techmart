package com.techmart.service.impl;

import com.techmart.model.Account;
import com.techmart.model.Authorities;
import com.techmart.repository.AccountRepository;
import com.techmart.repository.AuthoritiesRepository;
import com.techmart.service.AuthoritiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthoritiesServiceImpl implements AuthoritiesService {
    @Autowired
    private AuthoritiesRepository authoritiesRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Authorities create (Authorities authorities){ return authoritiesRepository.save(authorities);}

    @Override
    public Authorities update (Authorities authorities){ return authoritiesRepository.save(authorities);}

    @Override
    public void delete (Integer id){ authoritiesRepository.deleteById(id);}

    @Override
    public List<Authorities> findAll(){ return authoritiesRepository.findAll();}

    @Override
    public List<Authorities> findAuthoritiesOfAdministrators(){
        List<Account> accounts = accountRepository.getAdministrators();
        return authoritiesRepository.authoritiesOf(accounts);
    }
}
