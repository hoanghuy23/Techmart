package com.techmart.service.impl;

import com.techmart.model.InvoiceDetail;
import com.techmart.repository.InvoiceDetailRepository;
import com.techmart.service.InvoiceDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceDetailServiceImpl implements InvoiceDetailService {
    @Autowired
    InvoiceDetailRepository invoiceDetailRepository;

    @Override
    public InvoiceDetail create(InvoiceDetail invoiceDetail){ return invoiceDetailRepository.save(invoiceDetail);}

    @Override
    public InvoiceDetail update(InvoiceDetail invoiceDetail){ return invoiceDetailRepository.save(invoiceDetail);}

    @Override
    public void delete(int id){ invoiceDetailRepository.deleteById(id);}

    @Override
    public List<InvoiceDetail> findAll(){ return invoiceDetailRepository.findAll();}
}
