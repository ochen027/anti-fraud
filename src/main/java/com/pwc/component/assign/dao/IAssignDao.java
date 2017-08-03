package com.pwc.component.assign.dao;


import com.pwc.component.assign.entity.Assign;

import java.io.IOException;
import java.util.List;

public interface IAssignDao {

    void save(Assign assign) throws Exception;
    List<Assign> findByUserId(String userId) throws Exception;

    Assign findByObjId(String objId) throws Exception;

    void truncateTable() throws IOException;
}
