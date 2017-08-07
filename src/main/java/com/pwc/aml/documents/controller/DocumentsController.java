package com.pwc.aml.documents.controller;

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

import java.util.List;


@Controller
@RequestMapping("documents")
public class DocumentsController extends BaseController {


    @Autowired
    private IDocumentService documentService;

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> uploadDocs(@RequestParam("file") MultipartFile file, String workObjId) throws Exception{
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





}
