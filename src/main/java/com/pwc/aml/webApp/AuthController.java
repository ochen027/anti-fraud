package com.pwc.aml.webApp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class AuthController {


    @RequestMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @RequestMapping("/user/index")
    public String users(Model model){
        return "user/list_users";
    }

}
