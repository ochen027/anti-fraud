package com.pwc.aml.reports.dao;

import com.pwc.aml.reports.entity.Reports;

public interface IReportsDAO {

    Reports searchReport(String startDate) throws Exception;

}
