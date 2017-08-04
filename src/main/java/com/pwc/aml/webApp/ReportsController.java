package com.pwc.aml.webApp;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/reports")
public class ReportsController {

    @RequestMapping("/reportSummary")
    public String reportSummary(Model model){
        return "reports/reportSummary";
    }

    @RequestMapping("/reportDetail")
    public String reportDetail(Model model){
        return "reports/reportDetail";
    }
}
