package com.techmart.service;

import com.techmart.model.Address;

import java.util.List;

public interface AddressService {
    Address create(Address address);
    Address update(Address address);
    void delete(int id);
    List<Address> findAll();
}
