package com.pwc.aml.webApp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("system")
public class SystemController {

    @RequestMapping("/importData")
    public String importData(Model model) {
        return "system/importData";
    }


    @RequestMapping("/userManagement")
    public String userManagement(Model model) {
        return "system/userManagement";
    }



}