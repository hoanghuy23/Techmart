package com.techmart.service;

import com.techmart.model.Role;

import java.util.List;

public interface RoleService {
    Role create(Role role);
    Role update(Role role);
    void delete(int id);
    List<Role> findAll();
}
