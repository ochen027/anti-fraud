package com.pwc.aml.webApp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("scenario")
public class ScenarioManagementController {


    @RequestMapping("rules")
    public String rules(Model model) {
        return "scenario/rules";
    }

    @RequestMapping("rulesManager")
    public String rulesManager(Model model) {
        return "scenario/rulesManager";
    }


    @RequestMapping("scenario")
    public String scenario(Model model) {
        return "scenario/scenario";
    }


    @RequestMapping("scenarioManager")
    public String scenarioManager(Model model) {
        return "scenario/scenarioManager";
    }




}
