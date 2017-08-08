package com.pwc.aml.documents.dao;

import com.pwc.aml.documents.entity.Documents;

import java.util.List;

public interface IDocumentDAO {

    void saveFile(String localPath, String HDFSPath) throws Exception;

    void create(Documents doc) throws Exception;

    List<Documents> getDocByAlertId(String workObjId) throws Exception;


}
