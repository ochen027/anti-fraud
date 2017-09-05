package com.pwc.component.survey.controller;

import com.pwc.component.survey.entity.Survey;
import com.pwc.component.survey.entity.SurveyObj;
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
public class SurveyRestController {

    @GetMapping("getAllSurvey")
    public ResponseEntity<List<Survey>> getAllSurvey() {

        List<Survey> surveys=new ArrayList<>();

        return new ResponseEntity<List<Survey>>(surveys, HttpStatus.OK);
    }


    /***
     * save or update survey
     * @return
     */
    @PostMapping("saveSurvey")
    public ResponseEntity<Void> saveOrUpdateSurvey(@RequestBody Survey survey) {



        return new ResponseEntity<Void>( HttpStatus.OK);
    }


    /***
     * delete survey
     * @return
     */
    @PostMapping("delete")
    public ResponseEntity<Void> deleteSurvey(@RequestBody Survey survey) {



        return new ResponseEntity<Void>( HttpStatus.OK);
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

        //surveyObj=generateSomeInfo

        return new ResponseEntity<SurveyObj>(surveyObj, HttpStatus.OK);
    }


    /***
     * save a draft
     * @param surveyObj
     * @return
     */
    @PostMapping("saveSurveyObj")
    public ResponseEntity<Void> saveSurveyObj(@RequestBody SurveyObj surveyObj) {

        //surveyObj=generateSomeInfo

        return new ResponseEntity<Void>( HttpStatus.OK);
    }

    /**
     * get saved survey
     * @param surveyObj
     * @return
     */
    @GetMapping("getSurveyObj")
    public ResponseEntity<SurveyObj> getSurveyObj(@RequestBody SurveyObj surveyObj) {

        //surveyObj=generateSomeInfo

        return new ResponseEntity<SurveyObj>(surveyObj, HttpStatus.OK);
    }

    /***
     * finish this survey
     * @param surveyObj
     * @return
     */
    @PostMapping("submitSurvey")
    public ResponseEntity<Void> submitSurvey(@RequestBody SurveyObj surveyObj) {

        //surveyObj=generateSomeInfo

        return new ResponseEntity<Void>( HttpStatus.OK);
    }

}
