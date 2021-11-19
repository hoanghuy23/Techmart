package com.techmart.service;

import com.techmart.model.Invoice;

import java.util.List;

public interface InvoiceService {
    Invoice create(Invoice invoice);
    Invoice update(Invoice invoice);
    void delete(int id);
    List<Invoice> findAll();
}
