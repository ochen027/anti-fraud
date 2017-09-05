package com.pwc.component.survey.dao;

import com.pwc.component.survey.entity.Survey;
import com.pwc.component.survey.entity.SurveyObj;

import java.util.List;

public interface ISurveyDAO {

    List<Survey> getAllSurvey();

    void saveSurvey(Survey survey);

    void deleteSurvey(Survey survey);

    SurveyObj generateSurveyObj(SurveyObj surveyObj);

    void saveSurveyObj(SurveyObj surveyObj);

    SurveyObj getSurveyObj(SurveyObj surveyObj);

    void submitSurvey(SurveyObj surveyObj);

}
