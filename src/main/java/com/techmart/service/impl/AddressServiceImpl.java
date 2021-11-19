package com.techmart.service.impl;

import com.techmart.model.Address;
import com.techmart.repository.AddressRepository;
import com.techmart.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    AddressRepository addressRepository;

    @Override
    public Address create(Address address){ return addressRepository.save(address);}

    @Override
    public Address update(Address address){ return addressRepository.save(address);}

    @Override
    public void delete(int id){ addressRepository.deleteById(id);}

    @Override
    public List<Address> findAll(){ return addressRepository.findAll();}
}
