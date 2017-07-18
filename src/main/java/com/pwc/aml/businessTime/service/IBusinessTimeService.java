package com.pwc.aml.businessTime.service;

import java.text.ParseException;
import java.util.Date;

/**
 * Created by whuang072 on 7/18/2017.
 */
public interface IBusinessTimeService {

    String get();
    Date getDate() throws ParseException;
    void set(String businessDate);
    void set(Date businessDate);
    void toNextDay();

}
