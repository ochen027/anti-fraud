package com.pwc.aml.query.controller;

import com.pwc.aml.alert.entity.AlertSearchEntity;
import com.pwc.aml.query.service.IQueryService;
import com.pwc.aml.workflow.entity.WorkObj;
import com.pwc.common.base.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("querys")
public class QuerysController extends BaseController {

    @Autowired
    IQueryService queryService;

    @PostMapping("search")
    public ResponseEntity<List<WorkObj>> SearchQuery(@RequestBody AlertSearchEntity ase) throws Exception{
        return new ResponseEntity<List<WorkObj>>(queryService.SearchQuery(ase), HttpStatus.OK);
    }

}
