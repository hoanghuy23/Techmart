package com.techmart.service.impl;

import com.techmart.model.UserDetail;
import com.techmart.repository.UserDetailRepository;
import com.techmart.service.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDetailServiceImpl implements UserDetailService {
    @Autowired
    UserDetailRepository userDetailRepository;

    @Override
    public UserDetail create(UserDetail userDetail){ return userDetailRepository.save(userDetail);}

    @Override
    public UserDetail update(UserDetail userDetail){ return userDetailRepository.save(userDetail);}

    @Override
    public void delete(int id){ userDetailRepository.deleteById(id);}

    @Override
    public List<UserDetail> findAll(){ return userDetailRepository.findAll();}

    @Override
    public UserDetail getUserDetailByEmail(String email){ return  userDetailRepository.getUserDetailByEmail(email);}
}
