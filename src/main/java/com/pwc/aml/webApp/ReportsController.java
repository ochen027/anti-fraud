package com.pwc.aml.webApp;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ReportsController {

    @RequestMapping("/reports")
    public String reports(Model model) {
        return "reports/reports";
    }


}
