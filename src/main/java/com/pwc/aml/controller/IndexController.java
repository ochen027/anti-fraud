package com.pwc.aml.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;
import java.util.Map;



@Controller
public class IndexController {


    @RequestMapping("/greeting")
    public String greeting(Model model) {
        //model.addAttribute("name", name);
        return "index";
    }

}
