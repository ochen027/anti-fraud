package com.pwc.component.survey.service;

import com.pwc.component.survey.dao.ISurveyDAO;
import com.pwc.component.survey.entity.Survey;
import com.pwc.component.survey.entity.SurveyObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SurveyService implements ISurveyService {

    @Autowired
    ISurveyDAO surveyDAO;


    @Override
    public List<Survey> getAllSurvey() {
        return surveyDAO.getAllSurvey();
    }

    @Override
    public void saveSurvey(Survey survey) {
        surveyDAO.saveSurvey(survey);
    }

    @Override
    public void deleteSurvey(Survey survey) {
        surveyDAO.deleteSurvey(survey);
    }

    @Override
    public SurveyObj generateSurveyObj(SurveyObj surveyObj) {
        return surveyDAO.generateSurveyObj(surveyObj);
    }

    @Override
    public void saveSurveyObj(SurveyObj surveyObj) {
        surveyDAO.saveSurveyObj(surveyObj);
    }

    @Override
    public SurveyObj getSurveyObj(SurveyObj surveyObj) {
        return surveyDAO.getSurveyObj(surveyObj);
    }

    @Override
    public void submitSurvey(SurveyObj surveyObj) {
        surveyDAO.submitSurvey(surveyObj);
    }
}

