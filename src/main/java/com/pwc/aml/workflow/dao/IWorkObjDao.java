package com.pwc.aml.workflow.dao;


import com.pwc.aml.alert.entity.AlertSearchEntity;
import com.pwc.aml.workflow.entity.FlowPointEx;
import com.pwc.aml.workflow.entity.WorkObj;

import java.io.IOException;
import java.util.List;

public interface IWorkObjDao {
    void save(WorkObj workObj) throws Exception;

    WorkObj findWorkObjByWorkObjId(String workObjId) throws Exception;


    List<WorkObj> findWorkObjsByPointId(String flowPointId) throws Exception;

    void truncateTable() throws IOException;

    List<WorkObj> searchClosedAlertWorkObject(String flowPointId, AlertSearchEntity ase) throws Exception;
}
