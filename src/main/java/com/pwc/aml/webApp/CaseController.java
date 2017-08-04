package com.pwc.aml.webApp;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/case")
public class CaseController {

    @RequestMapping("/myCase")
    public String myCase(Model model){
        return "case/myCase";
    }

    @RequestMapping("/closedCase")
    public String closedCase(Model model){
        return "case/closedCase";
    }
}
