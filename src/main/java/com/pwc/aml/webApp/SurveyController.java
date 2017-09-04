package com.pwc.aml.webApp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("survey")
public class SurveyController {

    @RequestMapping("/doSurvey")
    public String doSurvey(Model model) {
        return "survey/doSurvey";
    }

    @RequestMapping("/surveyEditor")
    public String surveyEdit(Model model) {
        return "survey/surveyEditor";
    }

    @RequestMapping("/surveyManager")
    public String surveyManager(Model model) {
        return "survey/surveyManager";
    }

}
