package com.techmart.service;

import com.techmart.model.UserDetail;

import java.util.List;

public interface UserDetailService {
    UserDetail create(UserDetail userDetail);
    UserDetail update(UserDetail userDetail);
    void delete(int id);
    List<UserDetail> findAll();
    UserDetail getUserDetailByEmail(String email);
}
