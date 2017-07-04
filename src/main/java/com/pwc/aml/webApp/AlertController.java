package com.pwc.aml.webApp;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/alert")
public class AlertController {

    @RequestMapping("/available")
    public String available(Model model) {
        return "alert/available";
    }

    @RequestMapping("/myAlert")
    public String myAlert(Model model) {
        return "alert/my_alerts";
    }


}



