package com.pwc.aml.customers.service;

import au.com.bytecode.opencsv.CSVReader;
import com.pwc.aml.customers.dao.IIndividualDao;
import com.pwc.aml.customers.entity.Individual;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;
@Service
public class IndividualService implements IIndividualService{
    @Autowired
    private IIndividualDao individualDao;
    private DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
    @Override
    public void saveOrUpdateIndividual(Individual individual) {
        Individual target = individualDao.findByCustId(individual.getCustomerId());

        if (target == null) {
            individualDao.save(individual);
        } else {
            individualDao.update(individual);
        }
    }

    @Override
    public List<Individual> findAll() {
        return individualDao.findAll();
    }

    @Override
    public void removeAll() {
    individualDao.removeAll();
    }

    @Override
    public void importCsvData(MultipartFile file) throws IOException, ParseException {
        File temp = new File("temp", UUID.randomUUID().toString());
        FileUtils.writeByteArrayToFile(temp, file.getBytes());
        CSVReader reader = new CSVReader(new FileReader(temp));
        List<String[]> individualList = reader.readAll();
        for(int i=1;i<individualList.size();i++) {
            Individual individual = new Individual();
            String[] item = individualList.get(i);
            individual.setCustomerId(item[0]);
            int age=item[1].equals("")?0:Integer.parseInt(item[1]);
            individual.setCustomerAge(age);
            Date birthday=item[2].equals("")?null:df.parse(item[2]);
            individual.setCustomerBirthDate(birthday);
            individual.setCustomerCertificateNumber(item[3]);
            individual.setCustomerOccupation(item[4]);
            individual.setCustomerCity(item[5]);
            individual.setCustomerCountry(item[6]);
            individual.setCustomerPhone(item[7]);
            boolean isPEP=item[8].equals("Y")?true:false;
            individual.setPEP(isPEP);
            boolean isResident=item[9].equals("Y")?true:false;
            individual.setNonResident(isResident);
            boolean isAMLSupport=item[10].equals("Y")?true:false;
            individual.setAMLSuspect(isAMLSupport);
            individualDao.save(individual);
        }
        FileUtils.deleteQuietly(temp);
    }
}
