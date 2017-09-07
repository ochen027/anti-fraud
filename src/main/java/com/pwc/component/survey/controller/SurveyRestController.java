package com.pwc.component.survey.controller;

import com.pwc.common.base.controller.BaseController;
import com.pwc.component.survey.entity.Survey;
import com.pwc.component.survey.entity.SurveyObj;
import com.pwc.component.survey.service.ISurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("survey")
public class SurveyRestController extends BaseController{

    @Autowired
    ISurveyService surveyService;

    @GetMapping("getAllSurvey")
    public ResponseEntity<List<Survey>> getAllSurvey() {
        return new ResponseEntity<List<Survey>>(surveyService.getAllSurvey(), HttpStatus.OK);
    }


    /***
     * save or update survey
     * @return
     */
    @PostMapping("saveSurvey")
    public ResponseEntity<Survey> saveOrUpdateSurvey(@RequestBody Survey survey) {
        survey.setCreatedBy(userName);
        survey.setLastUpdatedBy(userName);
        surveyService.saveSurvey(survey);
        return new ResponseEntity<Survey>(survey, HttpStatus.OK);
    }


    /***
     * delete survey
     * @return
     */
    @PostMapping("delete")
    public ResponseEntity<Void> deleteSurvey(@RequestBody Survey survey) {
        survey.setLastUpdatedBy(userName);
        surveyService.deleteSurvey(survey);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }


    //---------------------------end of survey; start with survey obj-----------------------------------------


    /***
     * generate surveyObj
     * generate a link or send the link to someone's email
     *
     * @return
     */
    @PostMapping("generateSurveyObj")
    public ResponseEntity<SurveyObj> generateLink(@RequestBody SurveyObj surveyObj) {
        surveyObj.setCreatedBy(userName);
        surveyObj.setLastUpdatedBy(userName);
        SurveyObj so = surveyService.generateSurveyObj(surveyObj);
        return new ResponseEntity<SurveyObj>(so, HttpStatus.OK);
    }


    /***
     * save a draft
     * @param surveyObj
     * @return
     */
    @PostMapping("saveSurveyObj")
    public ResponseEntity<Void> saveSurveyObj(@RequestBody SurveyObj surveyObj) {
        surveyObj.setLastUpdatedBy(userName);
        surveyService.saveSurveyObj(surveyObj);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    /**
     * get saved survey
     * @param surveyObj
     * @return
     */
    @GetMapping("getSurveyObj")
    public ResponseEntity<SurveyObj> getSurveyObj(@RequestBody SurveyObj surveyObj) {
        return new ResponseEntity<SurveyObj>(surveyService.getSurveyObj(surveyObj), HttpStatus.OK);
    }

    /***
     * finish this survey
     * @param surveyObj
     * @return
     */
    @PostMapping("submitSurvey")
    public ResponseEntity<Void> submitSurvey(@RequestBody SurveyObj surveyObj) {
        surveyService.submitSurvey(surveyObj);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}
