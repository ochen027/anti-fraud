package com.pwc.aml.workflow.dao;


import com.pwc.aml.workflow.entity.WorkObj;

import java.util.List;

public interface IWorkObjDao {
    void save(WorkObj workObj) throws Exception;

    WorkObj findWorkObjByWorkObjId(String workObjId) throws Exception;


    List<WorkObj> findWorkObjsByPointId(String flowPointId) throws Exception;
}
