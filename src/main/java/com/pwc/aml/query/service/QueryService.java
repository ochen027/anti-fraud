package com.pwc.aml.query.service;

import com.pwc.aml.alert.entity.AlertSearchEntity;
import com.pwc.aml.customers.dao.CustomerDAO;
import com.pwc.aml.query.dao.QueryDAO;
import com.pwc.aml.workflow.entity.WorkObj;
import com.pwc.aml.workflow.service.IWorkObjService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QueryService implements IQueryService {

    @Autowired
    CustomerDAO customerDAO;

    @Autowired
    QueryDAO queryDAO;

    @Autowired
    IWorkObjService workObjService;

    @Override
    public List<WorkObj> SearchQuery(AlertSearchEntity ase) throws Exception {
        List<String> customerIdList = customerDAO.findByIdAndName(ase.getCustomerId(), ase.getCustomerName());
        if(null == customerIdList){
            return null;
        }else{
            ase.setCustomerIdList(customerIdList);
            return workObjService.getWorkObjsByPointId(null,ase);
        }
    }
}
