package com.techmart.service;

import com.techmart.model.InvoiceDetail;

import java.util.List;

public interface InvoiceDetailService {
    InvoiceDetail create(InvoiceDetail invoiceDetail);
    InvoiceDetail update(InvoiceDetail invoiceDetail);
    void delete(int id);
    List<InvoiceDetail> findAll();
}
