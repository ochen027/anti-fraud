package com.pwc.aml.customers.service;

import au.com.bytecode.opencsv.CSVReader;
import com.pwc.aml.customers.dao.IRepresentativeDao;
import com.pwc.aml.customers.entity.Representative;
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
public class RepresentativeService implements IRepresentativeService {
    @Autowired
    private IRepresentativeDao representativeDao;
    private DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
    @Override
    public Representative findByCustId(Representative representative) {
        return representativeDao.findByCustId(representative.getCustomerId());
    }

    @Override
    public List<Representative> findAll() {
        return representativeDao.findAll();
    }

    @Override
    public void removeAll() {
        representativeDao.removeAll();

    }

    @Override
    public void importCsvData(MultipartFile file) throws IOException, ParseException {
        File temp = new File("temp", UUID.randomUUID().toString());
        FileUtils.writeByteArrayToFile(temp, file.getBytes());
        CSVReader reader = new CSVReader(new FileReader(temp));
        List<String[]> representativeList = reader.readAll();
        for(int i=1;i<representativeList.size();i++) {
            Representative representative = new Representative();
            String[] item = representativeList.get(i);
            representative.setCustomerId(item[0]);
            representative.setRepresentativeName(item[1]);
            int age=item[1].equals("")?0:Integer.parseInt(item[2]);
            representative.setRepresentativeAge(age);
            Date birthday=item[3].equals("")?null:df.parse(item[3]);
            representative.setRepresentativeBirthDate(birthday);
            representative.setRepresentativeId(item[4]);
            representative.setRepresentativeIdType(item[5]);
            representative.setRepresentativeOccupation(item[6]);
            representative.setRepresentativeCity(item[7]);
            representative.setRepresentativeCountry(item[8]);
            representative.setRepresentativePhone(item[9]);
            boolean isPEP=item[10].equals("Y")?true:false;
            representative.setPEP(isPEP);
            boolean isResident=item[11].equals("Y")?true:false;
            representative.setNonResident(isResident);
            boolean isAMLSupport=item[12].equals("Y")?true:false;
            representative.setAMLSuspect(isAMLSupport);
            representativeDao.save(representative);
        }
        FileUtils.deleteQuietly(temp);

    }
}
