package com.pwc.aml.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.Map;



@Controller
@RequestMapping("/index")
public class IndexController {


    @GetMapping("/login")
    public String welcome(Map<String, Object> model) {

        return "index";
    }

}
