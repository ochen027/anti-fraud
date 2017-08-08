package com.pwc.aml.watchlist.service;

import au.com.bytecode.opencsv.CSVReader;
import com.pwc.aml.watchlist.dao.IWatchListDao;
import com.pwc.aml.watchlist.entity.WatchList;
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
public class WatchListService implements IWatchListService {

    @Autowired
    private IWatchListDao watchListDao;

    @Override
    public void importCsvData(MultipartFile file, String userName) throws IOException, ParseException {
        File temp = new File("temp", UUID.randomUUID().toString());
        FileUtils.writeByteArrayToFile(temp, file.getBytes());
        CSVReader reader = new CSVReader(new FileReader(temp));
        List<String[]> watchListList = reader.readAll();
        for(int i=1;i<watchListList.size();i++){
            WatchList watchList=new WatchList();
            String[] item=watchListList.get(i);
            watchList.setWatchList(item[0]);
            watchList.setCreatedBy(userName);
            watchList.setCreationDate(new Date());
            watchList.setLastUpdatedBy(userName);
            watchList.setLastUpdateDate(new Date());
            watchListDao.save(watchList);
        }
        FileUtils.deleteQuietly(temp);
    }

    @Override
    public List<WatchList> getAllWatchList() {
        return watchListDao.findAll();
    }
    @Override
    public WatchList findSingleCountry(int ID) {
        return watchListDao.findById(ID);
    }
    @Override
    public void deleteCountry(int id){
        watchListDao.deleteById(id);
    }

    @Override
    public void delete(WatchList wl) {
        watchListDao.delete(wl);
    }

    @Override
    public void removeAll(){
        watchListDao.removeAll();
    }
    @Override
    public WatchList saveOrUpdate(WatchList wl) {
        WatchList result = null;
        if( wl== null){

            wl.setStatus(true);
            result=watchListDao.save(wl);
        }else{
            result = watchListDao.update(wl);
        }
        return result;
    }
}
