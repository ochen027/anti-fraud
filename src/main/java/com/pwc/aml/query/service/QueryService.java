package com.pwc.aml.query.service;

import com.pwc.aml.alert.entity.AlertSearchEntity;
import com.pwc.aml.customers.dao.CustomerBaseDao;
import com.pwc.aml.query.dao.QueryDAO;
import com.pwc.aml.workflow.entity.WorkObj;
import com.pwc.aml.workflow.service.IWorkObjService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QueryService implements IQueryService {

    @Autowired
    CustomerBaseDao customerBaseDAO;

    @Autowired
    QueryDAO queryDAO;

    @Autowired
    IWorkObjService workObjService;

    @Override
    public List<WorkObj> SearchQuery(AlertSearchEntity ase) throws Exception {
        List<String> customerIdList = null;
        if(StringUtils.isEmpty(ase.getCustomerId()) && StringUtils.isEmpty(ase.getCustomerName())){
            ase.setAllCustomer(true);
        }else{
            ase.setAllCustomer(false);
            customerIdList = customerBaseDAO.findByIdAndName(ase.getCustomerId(), ase.getCustomerName());
            ase.setCustomerIdList(customerIdList);
        }

        if(null==customerIdList && !ase.getAllCustomer()){
            return null;
        }

        return workObjService.getWorkObjsByPointId(null,ase);
    }
}
