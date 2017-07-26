package com.pwc.aml.accounts.service;

import au.com.bytecode.opencsv.CSVReader;
import com.pwc.aml.accounts.dao.IAccountDAO;
import com.pwc.aml.accounts.entity.Accounts;
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
import java.util.List;
import java.util.UUID;

@Service
public class AccountService implements IAccountService{

    @Autowired
    private IAccountDAO accountDAO;

    private DateFormat df = new SimpleDateFormat("MM/dd/yyyy");

    @Override
    public void saveOrUpdateAccount(Accounts account) {

        Accounts target = accountDAO.findByAcctId(account.getAccountId());

        if (target == null) {
            accountDAO.save(account);
        } else {
            accountDAO.update(account);
        }
    }

    @Override
    public Accounts findByAcctId(Accounts account) {
        return accountDAO.findByAcctId(account.getAccountId());
    }

    @Override
    public List<Accounts> findByCustId(Accounts account) {
        return accountDAO.findByCustId(account.getCustomerId());
    }

    @Override
    public List<Accounts> findAll() {
        return accountDAO.findAll();
    }

    @Override
    public void removeAll() {
        accountDAO.removeAll();
    }


    @Override
    public void importCsvData(MultipartFile file) throws IOException, ParseException {

        File temp = new File("temp", UUID.randomUUID().toString());
        FileUtils.writeByteArrayToFile(temp, file.getBytes());
        CSVReader reader = new CSVReader(new FileReader(temp));
        List<String[]> accountList = reader.readAll();
        for(int i=1;i<accountList.size()-1;i++){
            Accounts accounts=new Accounts();
            String[] item=accountList.get(i);
            accounts.setAccountId(item[0]);
            accounts.setCustomerId(item[1]);
            accounts.setAccountOpenDate(item[2].equals("")?null:df.parse(item[2]));
            accounts.setAccountOpenBr(item[3]);
            accounts.setAccountStatus(item[4]);
            accounts.setAccountCloseDate(item[5].equals("")?null:df.parse(item[5]));
            accounts.setAccountAmount(item[6].equals("") ? 0.00:Double.parseDouble(item[6]));
            accounts.setLastUpdateBr(item[7]);
            accounts.setLastUpdateDate(item[8].equals("") ? null:df.parse(item[8]));
            accountDAO.save(accounts);
        }
        FileUtils.deleteQuietly(temp);
    }



}
