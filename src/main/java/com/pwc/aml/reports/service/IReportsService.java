package com.pwc.aml.reports.service;

import com.pwc.aml.reports.entity.Reports;

import java.util.List;

public interface IReportsService {

    List<Reports> SearchByDate(String startDate, String endDate) throws Exception;
}
