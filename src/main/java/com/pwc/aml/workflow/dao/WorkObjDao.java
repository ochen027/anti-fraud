package com.pwc.aml.workflow.dao;

import com.pwc.aml.common.hbase.IHbaseDao;
import com.pwc.aml.workflow.entity.WorkObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class WorkObjDao implements IWorkObjDao {

    @Autowired
    private IHbaseDao hbaseDao;

    @Override
    public void save(WorkObj workObj) {

    }
}
