package com.pwc.aml.webApp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("users")
public class UserController {

    @RequestMapping("/list")
    public String listUsers(Model model){
        return "user/list_all";
    }
}
