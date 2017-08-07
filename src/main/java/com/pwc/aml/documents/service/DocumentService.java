package com.pwc.aml.documents.service;

import au.com.bytecode.opencsv.CSVReader;
import com.pwc.aml.common.util.Constants;
import com.pwc.aml.common.util.HdfsAPI;
import com.pwc.aml.documents.dao.IDocumentDAO;
import com.pwc.aml.documents.entity.Documents;
import com.pwc.common.util.FormatUtils;
import org.apache.commons.io.FileUtils;
import org.apache.hadoop.fs.Hdfs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileReader;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class DocumentService implements IDocumentService {

    @Autowired
    private IDocumentDAO documentDAO;

    @Override
    public void UploadDocument(MultipartFile file, String userName, String workObjId) throws Exception{
        String fileId = FormatUtils.GenerateId();
        File tempFile = new File(Constants.TEMP, fileId);
        FileUtils.writeByteArrayToFile(tempFile, file.getBytes());
        documentDAO.saveFile(tempFile.getAbsolutePath(), Constants.HDFS_FILE_PATH + fileId);
        Documents doc = new Documents();
        doc.setDocId(fileId);
        doc.setDocPath(file.getOriginalFilename());
        doc.setWorkObjId(workObjId);
        doc.setDocCreatedDate(FormatUtils.LocalDateToString(LocalDate.now()));
        doc.setDocCreatedBy(userName);
        documentDAO.create(doc);
    }

    @Override
    public List<Documents> ListDocumentByAlert(String workObjId) throws Exception {
        return documentDAO.getDocByAlertId(workObjId);
    }


}
