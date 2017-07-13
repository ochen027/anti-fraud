package com.pwc.aml.webApp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("document")
public class DocumentController {


    @RequestMapping("/index")
    public String index(Model model) {
        return "document";
    }



}
