package com.pwc.aml.documents.controller;

import com.pwc.aml.common.util.Constants;
import com.pwc.aml.documents.entity.Documents;
import com.pwc.aml.documents.service.IDocumentService;
import com.pwc.common.base.controller.BaseController;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;


@Controller
@RequestMapping("documents")
public class DocumentsController extends BaseController {


    @Autowired
    private IDocumentService documentService;

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> uploadDocs(@RequestParam("file") MultipartFile file,@RequestParam("workObjId") String workObjId) throws Exception{
        if (!file.isEmpty()) {
            if(StringUtils.isEmpty(workObjId)){
                workObjId = "201708071058455875519638759332";
            }
            documentService.UploadDocument(file, userName, workObjId);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @GetMapping("getByAlertId/{id}")
    public ResponseEntity<List<Documents>> getByAlertId(@PathVariable String id) throws Exception{
        return new ResponseEntity<List<Documents>>(documentService.ListDocumentByAlert(id), HttpStatus.OK);
    }

    @GetMapping("download/{fileName}/{filePath:.+}")
    public ResponseEntity<Void> downloadFile(@PathVariable String fileName,
                                             @PathVariable String filePath,
                                             HttpServletResponse response) throws Exception{
        InputStream in = documentService.DownloadFile(Constants.HDFS_FILE_PATH+fileName);
        OutputStream os = response.getOutputStream();
        response.setCharacterEncoding("utf-8");
        response.setContentType("multipart/form-data");
        response.setHeader("Content-Disposition",
                "attachment;fileName="+new String(filePath.getBytes("utf-8"), "iso-8859-1"));
        byte[] b = new byte[4096];
        int length;
        while ((length = in.read(b)) > 0) {
            os.write(b, 0, length);
        }
        os.close();
        in.close();
        return new ResponseEntity<Void>(HttpStatus.OK);
    }



}
