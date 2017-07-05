package com.pwc.aml.webApp;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/alert")
public class AlertController {



    @RequestMapping("/myAlert")
    public String myAlert(Model model) {
        return "alert/my_alerts";
    }

    @RequestMapping("/available")
    public String available(Model model) {
        return "alert/available";
    }

    @RequestMapping("/suppress")
    public String suppress(Model model) {
        return "alert/suppress";
    }

    @RequestMapping("/closed")
    public String closed(Model model) {
        return "alert/closed";
    }

    @RequestMapping("/myAlertInfo")
    public String myAlertInfo(Model model) {
        return "alert/my_alert_info";
    }

    @RequestMapping("/availableAlertInfo")
    public String availableAlertInfo(Model model) {
        return "alert/available_alert_info";
    }

    @RequestMapping("/suppressedAlertInfo")
    public String suppressedAlertInfo(Model model) {
        return "alert/suppressed_alert_info";
    }

    @RequestMapping("/closedAlertInfo")
    public String closedAlertInfo(Model model) {
        return "alert/closed_alert_info";
    }


    @RequestMapping("/create")
    public String create(Model model) {
        return "alert/create";
    }



}



