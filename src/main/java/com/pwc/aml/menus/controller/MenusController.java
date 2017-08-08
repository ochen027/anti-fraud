package com.pwc.aml.menus.controller;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.pwc.aml.menus.entity.Menus;
import com.pwc.aml.menus.service.IMenusService;

@Controller
@RequestMapping("menus")
public class MenusController {

    @Autowired
    private IMenusService menusService;
    @GetMapping("listUserMenus/{userId}")
    public ResponseEntity<List<Menus>> ListUserMenus(@PathVariable("userId") int userId){
        return new ResponseEntity<List<Menus>>(menusService.listUserMenus(userId), HttpStatus.OK);
    }
    @GetMapping("listUserChildMenus/{userId,menuId}")
    public ResponseEntity<List<Menus>>ListUserChildMenus(@PathVariable("userId") int userId,int menuId){
        return new ResponseEntity<List<Menus>>(menusService.listUserChildMenus(userId,menuId),HttpStatus.OK);
    }
    @PostMapping("deleteMenus/{menuId}")
    public ResponseEntity<Void> DeleteMenus(@PathVariable int menuId){
        Menus menus = menusService.getSingleMenus(menuId);
        menusService.deleteMenus(menus);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("createMenus")
    public ResponseEntity<Void> CreateMenus(@RequestBody Menus m){
        m.setCreationDate(new Date());
        menusService.createMenus(m);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @PutMapping("updateMenus")
    public ResponseEntity<Void> UpdateMenus(@RequestBody Menus m ){
        menusService.updateMenus(m);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @GetMapping("findAll")
    public ResponseEntity<List<Menus>> findAll(){
        return new ResponseEntity<List<Menus>>(menusService.findAll(), HttpStatus.OK);
    }
}
