package com.techmart.service;


import com.techmart.model.Authorities;

import java.util.List;

public interface AuthoritiesService {
    Authorities create(Authorities authorities);
    Authorities update(Authorities authorities);
    void delete(Integer id);
    List<Authorities> findAll();
    List<Authorities> findAuthoritiesOfAdministrators();
}
