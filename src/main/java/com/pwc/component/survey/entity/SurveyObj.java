package com.pwc.component.survey.entity;

import com.pwc.common.base.entity.BaseEntity;
import com.pwc.component.authorize.users.entity.Users;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "SurveyObj")
public class SurveyObj extends BaseEntity {


    @Column(name="surveyId")
    public int surveyId;

    @Column(name="answer")
    public String answer;


    @Column(name="sendUser")
    public Users sendUser;

    /***
     * something to describe which user has been send to
     *
     */
    @Column(name="targetInfo")
    public String targetInfo;

    @Column(name="surveyStatues")
    public String surveyStatues;

    public int getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(int surveyId) {
        this.surveyId = surveyId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
