package com.pwc.aml.webApp;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/query")
public class QueryController {

    @RequestMapping("/alertQuery")
    public String alertQuery(Model model){
        return "query/alertQuery";
    }

    @RequestMapping("/caseQuery")
    public String caseQuery(Model model){
        return "query/caseQuery";
    }
}
