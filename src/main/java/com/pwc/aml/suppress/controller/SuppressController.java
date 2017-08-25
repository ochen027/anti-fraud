package com.pwc.aml.suppress.controller;



import com.pwc.aml.suppress.entity.RemoveSuppress;
import com.pwc.aml.suppress.entity.Suppress;
import com.pwc.aml.suppress.service.ISuppressService;
import com.pwc.common.base.controller.BaseController;
import com.pwc.component.authorize.users.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("suppress")
public class SuppressController extends BaseController {

    @Autowired
    private ISuppressService suppressService;


    @GetMapping("listAll")
    public ResponseEntity<List<Suppress>> listAll() throws Exception {
        List<Suppress> list= suppressService.findAll();
        return new ResponseEntity<List<Suppress>>(list, HttpStatus.OK);
    }


    @GetMapping("listAllActive")
    public ResponseEntity<List<Suppress>> listAllActive() throws Exception {
        List<Suppress> list= suppressService.findAllActive();
        return new ResponseEntity<List<Suppress>>(list, HttpStatus.OK);
    }


    @PostMapping("addSuppress")
    public ResponseEntity<Void> addSuppress(@RequestBody Suppress suppress, HttpSession session) throws Exception {

        Map<String, Object> userInfo = (Map<String, Object>) session.getAttribute("UserInfo");
        Users user = (Users) userInfo.get("User");

        suppressService.addSuppress(suppress,user);

        return new ResponseEntity<Void>( HttpStatus.OK);
    }



    @PostMapping("removeSuppress")
    public ResponseEntity<Void> removeSuppress(@RequestBody RemoveSuppress removeSuppress, HttpSession session) throws Exception {

        Map<String, Object> userInfo = (Map<String, Object>) session.getAttribute("UserInfo");
        Users user = (Users) userInfo.get("User");

//        suppressService.InActive(removeSuppress.getIds(),user);

        return new ResponseEntity<Void>( HttpStatus.OK);
    }

}
