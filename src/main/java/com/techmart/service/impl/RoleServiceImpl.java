package com.techmart.service.impl;

import com.techmart.model.Role;
import com.techmart.repository.RoleRepository;
import com.techmart.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleRepository roleRepository;

    @Override
    public Role create(Role role){ return roleRepository.save(role);}

    @Override
    public Role update(Role role){ return roleRepository.save(role);}

    @Override
    public void delete(int id){ roleRepository.deleteById(id);}

    @Override
    public List<Role> findAll(){return roleRepository.findAll();}
}
