package com.pwc.aml.webApp;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("workflow")
public class WorkflowController {

    @RequestMapping("/index")
    public String index(Model model) {
        return "workflow/index";
    }


    @RequestMapping("/edit")
    public String edit(Model model) {
        return "workflow/edit";
    }


}



