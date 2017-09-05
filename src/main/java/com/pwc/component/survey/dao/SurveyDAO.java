package com.pwc.component.survey.dao;
import com.pwc.component.survey.entity.Survey;
import com.pwc.component.survey.entity.SurveyObj;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Transactional
@Repository
public class SurveyDAO implements ISurveyDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Survey> getAllSurvey() {
        String hql = "FROM Survey where status = 1 ORDER BY id";
        return (List<Survey>) entityManager.createQuery(hql).getResultList();
    }

    @Override
    public void saveSurvey(Survey survey) {
        if(0 == survey.getId()){
            entityManager.persist(survey);
        }else{
            Survey s = this.getSurveyById(survey.getId());
            s.setContent(survey.getContent());
            s.setDescription(survey.getDescription());
            s.setName(survey.getName());
            s.setLastUpdatedBy(survey.getLastUpdatedBy());
            s.setLastUpdateDate(new Date());
            entityManager.flush();
        }
    }

    @Override
    public void deleteSurvey(Survey survey) {
        Survey s = this.getSurveyById(survey.getId());
        s.setStatus(false);
    }

    @Override
    public SurveyObj generateSurveyObj(SurveyObj surveyObj) {
        surveyObj.setSurveyId(UUID.randomUUID().toString());
        surveyObj.setCreationDate(new Date());
        surveyObj.setLastUpdateDate(new Date());
        entityManager.persist(surveyObj);
        return surveyObj;
    }

    @Override
    public void saveSurveyObj(SurveyObj surveyObj) {
        SurveyObj so = this.getSurveyObj(surveyObj);
        so.setLastUpdateDate(new Date());
        so.setAnswer(surveyObj.getAnswer());
        so.setLastUpdatedBy(surveyObj.getLastUpdatedBy());
        entityManager.flush();
    }

    @Override
    public SurveyObj getSurveyObj(SurveyObj surveyObj) {
        String hql = "FROM SurveyObj where surveyId = ?";
        List<SurveyObj> list = entityManager.createQuery(hql).setParameter(1, surveyObj.getSurveyId()).getResultList();
        return list.get(0);
    }

    @Override
    public void submitSurvey(SurveyObj surveyObj) {
        SurveyObj so = this.getSurveyObj(surveyObj);
        so.setStatus(true);
        so.setLastUpdateDate(new Date());
        so.setLastUpdatedBy(surveyObj.getLastUpdatedBy());
        entityManager.flush();
    }

    private Survey getSurveyById(int Id){
        String hql = "FROM Survey where status = 1 and id = ? ORDER BY id";
        List<Survey> list = entityManager.createQuery(hql).setParameter(1, Id).getResultList();
        return list.get(0);
    }



}
