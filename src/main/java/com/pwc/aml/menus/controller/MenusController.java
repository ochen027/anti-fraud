package com.pwc.aml.menus.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pwc.aml.menus.entity.Menus;
import com.pwc.aml.menus.service.IMenusService;

@Controller
@RequestMapping("menus")
public class MenusController {

    @Autowired
    private IMenusService menusService;

    @GetMapping("loginMenus/{userId}")
    public ResponseEntity<List<Menus>> LoginUser(@PathVariable("userId") int userId) {
        return new ResponseEntity<List<Menus>>(menusService.listUserMenus(userId),HttpStatus.OK);
    }
}
