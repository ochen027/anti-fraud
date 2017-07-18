package com.pwc.aml.businessTime.service;

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
    private final static DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

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
                date = (Date) formatter.parse(target);
            } catch (ParseException e) {
                e.printStackTrace();
            } finally {
                return date;
            }
        }


    }

    @Override
    public void set(String businessDate) {
        keyValueDao.put(BUSINESS_DAY, businessDate);
    }

    @Override
    public void set(Date businessDate) {
        keyValueDao.put(BUSINESS_DAY, formatter.format(businessDate));
    }

    @Override
    public void toNextDay() {
        Date date = getDate();
        if (date != null) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.add(Calendar.DATE, 1);
            Date newDate=cal.getTime();
            set(newDate);
        }
    }
}
