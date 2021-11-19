package com.techmart.rest.controller;

import com.techmart.report.reportInventory;
import com.techmart.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@CrossOrigin("*")
@RestController
public class ReportRestController {
    @Autowired
    ReportService reportService;

//    @GetMapping("/rest/revenue")
//    public List<ReportDoanhThu> getAll(){
//        return reportService.getAll();
//    }

    @GetMapping("/rest/inventory")
    public List<reportInventory> getInventory(){
        return reportService.getReportInventories();
    }
}