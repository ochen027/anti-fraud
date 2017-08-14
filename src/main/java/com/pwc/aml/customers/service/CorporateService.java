package com.pwc.aml.customers.service;

import au.com.bytecode.opencsv.CSVReader;
import com.pwc.aml.customers.dao.ICorporateDao;
import com.pwc.aml.customers.dao.IRepresentativeDao;
import com.pwc.aml.customers.entity.Corporate;
import com.pwc.aml.customers.entity.Corporate;
import com.pwc.aml.customers.entity.Representative;
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
public class CorporateService implements ICorporateService {
    @Autowired
    private ICorporateDao corporateDao;

    @Autowired
    private IRepresentativeDao representativeDao;

    @Override
    public Corporate findByCustId(String custId) {

        Corporate corporate= corporateDao.findByCustId(custId);

        Representative representative= representativeDao.findByCustId(custId);

        corporate.setRepresentative(representative);

        return corporate;
    }

    @Override
    public List<Corporate> findAll() {
        return corporateDao.findAll();
    }

    @Override
    public void removeAll() {
        corporateDao.removeAll();

    }

    @Override
    public void importCsvData(MultipartFile file) throws IOException, ParseException {
        File temp = new File("temp", UUID.randomUUID().toString());
        FileUtils.writeByteArrayToFile(temp, file.getBytes());
        CSVReader reader = new CSVReader(new FileReader(temp));
        List<String[]> corporateList = reader.readAll();
        for (int i = 1; i < corporateList.size(); i++) {
            Corporate corporate = new Corporate();
            String[] item = corporateList.get(i);
            corporate.setCustomerId(item[0]);
            corporate.setCustomerCertificateNumberCorporate(item[1]);
            corporate.setCustLineOfBusiness(item[2]);
            corporate.setCustomerPhone(item[3]);
            corporate.setCustomerCity(item[4]);
            corporate.setCustomerCountry(item[5]);
            boolean IsAMLSuspectCorporate = item[6].equals("Y") ? true : false;
            corporate.setAMLSuspectCorporate(IsAMLSuspectCorporate);

            corporateDao.save(corporate);
        }
        FileUtils.deleteQuietly(temp);

    }
}
