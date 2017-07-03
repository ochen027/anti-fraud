package com.pwc.aml.webApp;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping("/")
    public String index(Model model) {
        return "index";
    }

    @RequestMapping("/footer")
    public String footer(Model model) {
        return "footer";
    }

    @RequestMapping("/header")
    public String header(Model model) {
        return "header";
    }




}



