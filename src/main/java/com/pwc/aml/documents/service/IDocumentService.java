package com.pwc.aml.documents.service;

import com.pwc.aml.documents.entity.Documents;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.InputStream;
import java.util.List;

public interface IDocumentService {

    void UploadDocument(MultipartFile file, String userName, String workObjId) throws Exception;

    List<Documents> ListDocumentByAlert(String workObjId) throws Exception;

    InputStream DownloadFile(String fileName) throws Exception;
}
