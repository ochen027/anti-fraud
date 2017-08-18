package com.pwc.aml.customers.service;


import au.com.bytecode.opencsv.CSVReader;
import com.pwc.aml.customers.dao.ICorporateDao;
import com.pwc.aml.customers.dao.IIndividualDao;
import com.pwc.aml.customers.dao.IRepresentativeDao;
import com.pwc.aml.customers.entity.Corporate;
import com.pwc.aml.customers.entity.CustomerBase;
import com.pwc.aml.customers.dao.ICustomerBaseDao;
import com.pwc.aml.customers.entity.Individual;
import com.pwc.aml.customers.entity.Representative;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.UUID;

@Service
public class CustomerBaseService implements ICustomerBaseService {

    @Autowired
    private ICustomerBaseDao customerBaseDAO;

    @Autowired
    private IIndividualDao individualDao;

    @Autowired
    private ICorporateService corporateService;




    @Override
    public void saveOrUpdateCustomerBase(CustomerBase customerBase) {

        CustomerBase target = customerBaseDAO.findByCustId(customerBase.getCustomerId());

        if (target == null) {
            customerBaseDAO.save(customerBase);
        } else {
            customerBaseDAO.update(customerBase);
        }
    }

    @Override
    public CustomerBase findByCustId(CustomerBase customerBase) {

        CustomerBase target=customerBaseDAO.findByCustId(customerBase.getCustomerId());

        Individual individual= individualDao.findByCustId(customerBase.getCustomerId());
        if (individual!=null)

        target.setIndividual(individual);
        else{
            Corporate corporate= corporateService.findByCustId(customerBase.getCustomerId());
            target.setCorporate(corporate);
        }
//        Corporate corporate= corporateService.findByCustId(customerBase.getCustomerId());
//        if (corporate!=null)
//
//        target.setCorporate(corporate);

        return target;
    }



    @Override
    public List<CustomerBase> findAll() {
        return customerBaseDAO.findAll();
    }

    @Override
    public void removeAll() {
        customerBaseDAO.removeAll();
    }


    @Override
    public void importCsvData(MultipartFile file) throws IOException, ParseException {

        File temp = new File("temp", UUID.randomUUID().toString());
        FileUtils.writeByteArrayToFile(temp, file.getBytes());
        CSVReader reader = new CSVReader(new FileReader(temp));
        List<String[]> customerBaseList = reader.readAll();
        for(int i=1;i<customerBaseList.size();i++){
            CustomerBase customerBase=new CustomerBase();
            String[] item=customerBaseList.get(i);
            customerBase.setCustomerId(item[0]);
            customerBase.setCustomerType(item[1]);
            customerBase.setCustomerFirstName(item[2]);
            customerBase.setCustomerMiddleName(item[3]);
            customerBase.setCustomerLastName(item[4]);
            customerBase.setCustomerFullName(item[5]);
            customerBase.setCustomerOpenBranch(item[6]);
            customerBase.setCustomerLastUpdatedBy(item[7]);
            customerBase.setCustomerRiskLevel(item[8]);
            customerBaseDAO.save(customerBase);
//            int age=item[7].equals("")?0:Integer.parseInt(item[7]);
//            customerBase.setCustomerBaseAge(age);
//            Date birthday=item[8].equals("")?null:df.parse(item[8]);
//            customerBase.setCustomerBaseBirthDate(birthday);// research
//            customerBase.setCustomerBaseCity(item[9]);
//            customerBase.setCustomerBaseCountry(item[10]);
//            customerBase.setCustomerBaseOccupation(item[11]);
//            customerBase.setCustomerBasePhone1(item[12]);
//            customerBase.setLastUpdatedBy(item[13]);
//            //Date lastUpdated=item[14].equals("")?null:df.parse(item[14]);
//            //customerBase.setLastUpdateDate(lastUpdated);//
//            boolean isPEP=item[14].equals("Y")?true:false;
//            customerBase.setPEP(isPEP);
//            boolean isResident=item[15].equals("Y")?true:false;
//            customerBase.setNonResident(isResident);
//            boolean isAMLSupport=item[16].equals("Y")?true:false;
//            customerBase.setAMLSuspect(isAMLSupport);
//            customerBase.setCustomerBaseType(item[17]);
//            customerBase.setRepresentative(item[18]);
//            customerBase.setRepresentativeId(item[19]);
//            customerBase.setRepresentativeIdType(item[20]);
//            customerBase.setCustomerBaseCertificateNumberCorporate(item[21]);
//            customerBase.setCustlineOfBusiness(item[22]);
//            boolean isAMLSupportCorporate=item[23].equals("Y")?true:false;
//            customerBase.setAMLSuspectCorporate(isAMLSupportCorporate);

        }
        FileUtils.deleteQuietly(temp);
    }


}
