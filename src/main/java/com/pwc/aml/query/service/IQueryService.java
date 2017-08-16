package com.pwc.aml.query.service;


import com.pwc.aml.alert.entity.AlertSearchEntity;
import com.pwc.aml.workflow.entity.WorkObj;

import java.util.List;

public interface IQueryService {

    List<WorkObj> SearchQuery(AlertSearchEntity ase) throws Exception;
}
