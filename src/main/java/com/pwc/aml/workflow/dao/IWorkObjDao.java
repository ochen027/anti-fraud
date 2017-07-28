package com.pwc.aml.workflow.dao;


import com.pwc.aml.workflow.entity.WorkObj;

public interface IWorkObjDao {
    void save(WorkObj workObj) throws Exception;
}
