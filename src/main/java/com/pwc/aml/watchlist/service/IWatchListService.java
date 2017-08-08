package com.pwc.aml.watchlist.service;

import com.pwc.aml.watchlist.entity.WatchList;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public interface IWatchListService {
    List<WatchList> getAllWatchList();
    WatchList findSingleCountry(int ID);
    void deleteCountry(int id);
    void delete(WatchList rc);
    void importCsvData(MultipartFile file,String userName) throws IOException, ParseException;
    void removeAll();
    public WatchList saveOrUpdate(WatchList wl);
}
