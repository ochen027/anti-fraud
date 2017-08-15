package com.pwc.aml.reports.service;

import com.pwc.aml.reports.dao.IReportsDAO;
import com.pwc.aml.reports.entity.Reports;
import com.pwc.aml.transation.dao.ITransactionDAO;
import com.pwc.aml.workflow.dao.IWorkObjDao;
import com.pwc.aml.workflow.service.IWorkflowExService;
import com.pwc.common.util.FormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReportsService implements IReportsService {

    @Autowired
    private IReportsDAO reportsDAO;

    @Override
    public List<Reports> SearchByDate(String startDate, String endDate) throws Exception{
        List<Reports> rList = null;
        if(!StringUtils.equals(startDate, endDate)){
            LocalDate sDate = FormatUtils.StringToLocalDate(startDate);
            LocalDate eDate = FormatUtils.StringToLocalDate(endDate);
            Period period = Period.between (sDate, eDate);
            rList = new ArrayList<Reports>(period.getDays());
            for(int i=0; i < period.getDays()+1; i++){
                Reports r = reportsDAO.searchReport(FormatUtils.LocalDateToString(sDate.plusDays((long)i)));
                rList.add(r);
            }
        }else{
            rList = new ArrayList<Reports>(1);
            rList.add(reportsDAO.searchReport(startDate));
        }
        return rList;

    }
}
