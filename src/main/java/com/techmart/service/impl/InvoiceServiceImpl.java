package com.techmart.service.impl;

import com.techmart.model.Invoice;
import com.techmart.repository.InvoiceRepository;
import com.techmart.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceServiceImpl implements InvoiceService {
    @Autowired
    InvoiceRepository invoiceRepository;

    @Override
    public Invoice create(Invoice invoice){ return invoiceRepository.save(invoice);}

    @Override
    public Invoice update(Invoice invoice){ return invoiceRepository.save(invoice);}

    @Override
    public void delete(int id){ invoiceRepository.deleteById(id);}

    @Override
    public List<Invoice> findAll(){ return invoiceRepository.findAll();}
}
