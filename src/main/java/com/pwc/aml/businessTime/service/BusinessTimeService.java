package com.pwc.aml.businessTime.service;

import com.pwc.common.util.FormatUtils;
import com.pwc.component.systemConfig.dao.IKeyValueDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Service
public class BusinessTimeService implements IBusinessTimeService {

    @Autowired
    private IKeyValueDao keyValueDao;
    private final static String BUSINESS_DAY = "BUSINESS_DAY";

    @Override
    public String get() {
        return keyValueDao.get(BUSINESS_DAY);
    }

    @Override
    public Date getDate() {
        String target = keyValueDao.get(BUSINESS_DAY);
        if (null == target) {
            return null;
        } else {
            Date date = null;
            try {
                date = FormatUtils.StringToDate(target);
            } catch (ParseException e) {
                e.printStackTrace();
            } finally {
                return date;
            }
        }


    }

    @Override
    public void set(String businessDate, String userName) {
        keyValueDao.put(BUSINESS_DAY, businessDate, userName);
    }

    @Override
    public void set(Date businessDate, String userName) {
        try {
            keyValueDao.put(BUSINESS_DAY, FormatUtils.DateToString(businessDate), userName);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void toNextDay(String userName) {
        Date date = getDate();
        if (date != null) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.add(Calendar.DATE, 1);
            Date newDate=cal.getTime();
            set(newDate, userName);
        }
    }
}
