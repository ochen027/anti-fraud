package com.pwc.aml.reports.controller;

import com.pwc.aml.reports.entity.Reports;
import com.pwc.aml.reports.service.IReportsService;
import com.pwc.common.base.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("reports")
public class ReportController extends BaseController {

    @Autowired
    private IReportsService reportsService;

    @GetMapping("search/{startDate}/{endDate}")
    public ResponseEntity<List<Reports>> SearchReports(@PathVariable String startDate, @PathVariable String endDate) throws Exception{
        return new ResponseEntity<List<Reports>>(reportsService.SearchByDate(startDate, endDate), HttpStatus.OK);
    }


}
