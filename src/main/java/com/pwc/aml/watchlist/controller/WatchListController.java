package com.pwc.aml.watchlist.controller;

import com.pwc.aml.watchlist.entity.WatchList;
import com.pwc.aml.watchlist.service.IWatchListService;
import com.pwc.common.base.controller.BaseController;
import com.pwc.component.authorize.users.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("watchList")
public class WatchListController  extends BaseController {

    @Autowired
    private IWatchListService watchListService;


    @GetMapping("getAll")
    public ResponseEntity<List<WatchList>> getAll() {

        List<WatchList> RiskCountries = watchListService.getAllWatchList();

        return new ResponseEntity<List<WatchList>>(RiskCountries, HttpStatus.OK);
    }


    @GetMapping("getSingleCountry/{id}")
    public ResponseEntity<WatchList> getSingleCountry(@PathVariable("id") int id){
        WatchList wl = watchListService.findSingleCountry(id);
        return new ResponseEntity<WatchList>(wl, HttpStatus.OK);
    }

    @PostMapping("deleteCountry/{id}")
    public ResponseEntity<Void> deleteCountry(@PathVariable("id") int id){

        watchListService.deleteCountry(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
    @PostMapping("delete")
    public ResponseEntity<Void> delete(@RequestBody WatchList wl){
        watchListService.delete(wl);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file, HttpSession session) throws IOException, ParseException {


        //assign to me
        Map<String, Object> userInfo =( Map<String, Object>) session.getAttribute("UserInfo");
        Users user = (Users)userInfo.get("User");

        if (!file.isEmpty()) {
            watchListService.importCsvData(file,user.getUserName());
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping ("removeAll")
    public ResponseEntity<Void> removeAll() {
        watchListService.removeAll();
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @PostMapping("saveOrUpdate")
    public ResponseEntity<Void> saveOrUpdate(@RequestBody WatchList wl){
        watchListService.saveOrUpdate(wl);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }



}