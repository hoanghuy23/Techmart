package com.techmart.service.impl;

import com.techmart.report.reportInventory;
import com.techmart.repository.ProductRepository;
import com.techmart.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {
    @Autowired
    private ProductRepository dao;

    @Override
    public List<reportInventory> getReportInventories() {
        return dao.getInventory();
    }
}
