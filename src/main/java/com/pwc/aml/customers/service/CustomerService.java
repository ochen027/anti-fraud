package com.pwc.aml.customers.service;


import au.com.bytecode.opencsv.CSVReader;
import com.pwc.aml.customers.dao.ICustomerDAO;
import com.pwc.aml.customers.entity.Customers;
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
public class CustomerService implements ICustomerService {

    @Autowired
    private ICustomerDAO customerDAO;

    private DateFormat df = new SimpleDateFormat("MM/dd/yyyy");

    @Override
    public void saveOrUpdateCustomer(Customers customer) {

        Customers target = customerDAO.findByCustId(customer.getCustomerId());

        if (target == null) {
            customerDAO.save(customer);
        } else {
            customerDAO.update(customer);
        }
    }

    @Override
    public Customers findByCustId(Customers customer) {
        return customerDAO.findByCustId(customer.getCustomerId());
    }

    @Override
    public Customers findByCustCtNo(Customers customer) {
        return customerDAO.findByCustCtNo(customer.getCustomerCertificateNumber());
    }

    @Override
    public List<Customers> findAll() {
        return customerDAO.findAll();
    }

    @Override
    public void removeAll() {
        customerDAO.removeAll();
    }


    @Override
    public void importCsvData(MultipartFile file) throws IOException, ParseException {

        File temp = new File("temp", UUID.randomUUID().toString());
        FileUtils.writeByteArrayToFile(temp, file.getBytes());
        CSVReader reader = new CSVReader(new FileReader(temp));
        List<String[]> customerList = reader.readAll();
        for(int i=1;i<customerList.size();i++){
            Customers customers=new Customers();
            String[] item=customerList.get(i);
            customers.setCustomerId(item[0]);
            customers.setCustomerCertificateNumber(item[1]);
            customers.setCustomerFirstName(item[2]);
            customers.setCustomerMiddleName(item[3]);
            customers.setCustomerLastName(item[4]);
            customers.setCustomerFullName(item[5]);
            customers.setCustomerOpenBranch(item[6]);
            int age=item[7].equals("")?0:Integer.parseInt(item[7]);
            customers.setCustomerAge(age);
            Date birthday=item[8].equals("")?null:df.parse(item[8]);
            customers.setCustomerBirthDate(birthday);// research
            customers.setCustomerCity(item[9]);
            customers.setCustomerCountry(item[10]);
            customers.setCustomerOccupation(item[11]);
            customers.setCustomerPhone1(item[12]);
            customers.setLastUpdatedBy(item[13]);
            //Date lastUpdated=item[14].equals("")?null:df.parse(item[14]);
            //customers.setLastUpdateDate(lastUpdated);//
            boolean isPEP=item[14].equals("Y")?true:false;
            customers.setPEP(isPEP);
            boolean isResident=item[15].equals("Y")?true:false;
            customers.setNonResident(isResident);
            boolean isAMLSupport=item[16].equals("Y")?true:false;
            customers.setAMLSuspect(isAMLSupport);
            customers.setCustomerType(item[17]);
            customers.setRepresentative(item[18]);
            customers.setRepresentativeId(item[19]);
            customers.setRepresentativeIdType(item[20]);
            customers.setCustomerCertificateNumberCorporate(item[21]);
            customers.setCustlineOfBusiness(item[22]);
            boolean isAMLSupportCorporate=item[23].equals("Y")?true:false;
            customers.setAMLSuspectCorporate(isAMLSupportCorporate);
            customerDAO.save(customers);
        }
        FileUtils.deleteQuietly(temp);
    }


}
