package com.pwc.aml.webApp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("batch")
public class BatchController {


    @RequestMapping("/trans")
    public String trans(Model model) {
        return "trans";
    }



}
