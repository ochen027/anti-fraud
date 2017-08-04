package com.pwc.aml.riskCountry.service;

import au.com.bytecode.opencsv.CSVReader;
import com.pwc.aml.riskCountry.dao.IRiskCountryDao;
import com.pwc.aml.riskCountry.entity.RiskCountry;
import com.pwc.component.authorize.roles.entity.Roles;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@Service
public class RiskCountryService implements IRiskCountryService {

    @Autowired
    private IRiskCountryDao riskCountryDao;

    @Override
    public void importCsvData(MultipartFile file,String userName) throws IOException, ParseException {
        File temp = new File("temp", UUID.randomUUID().toString());
        FileUtils.writeByteArrayToFile(temp, file.getBytes());
        CSVReader reader = new CSVReader(new FileReader(temp));
        List<String[]> riskCountryList = reader.readAll();
        for(int i=1;i<riskCountryList.size();i++){
            RiskCountry riskCountry=new RiskCountry();
            String[] item=riskCountryList.get(i);
            riskCountry.setRegion(item[0]);
            riskCountry.setRisk(item[1]);
            riskCountry.setName(item[2]);
            riskCountry.setCreatedBy(userName);
            riskCountry.setCreationDate(new Date());
            riskCountryDao.save(riskCountry);
        }
        FileUtils.deleteQuietly(temp);
    }

    @Override
    public List<RiskCountry> getAllRiskCountry() {
        return riskCountryDao.findAll();
    }
    @Override
    public RiskCountry findSingleCountry(int ID) {
        return riskCountryDao.findById(ID);
    }
    @Override
    public void deleteCountry(int id){
        riskCountryDao.deleteById(id);
    }

    @Override
    public void delete(RiskCountry rc) {
        riskCountryDao.delete(rc);
    }

    @Override
    public void removeAll(){
        riskCountryDao.removeAll();
    }
    @Override
    public RiskCountry saveOrUpdate(RiskCountry rc) {
        RiskCountry result = null;
        if( rc== null){

            rc.setStatus(true);
            result=riskCountryDao.save(rc);
        }else{
            result = riskCountryDao.update(rc);
        }
        return result;
    }
}
