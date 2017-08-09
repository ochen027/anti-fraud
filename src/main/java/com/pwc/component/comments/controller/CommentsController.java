package com.pwc.component.comments.controller;


import com.pwc.component.authorize.users.entity.Users;
import com.pwc.component.comments.entity.Comments;
import com.pwc.component.comments.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("comments")
public class CommentsController {

    @Autowired
    private ICommentService commentService;

    @PostMapping("create")
    public ResponseEntity<Void> CreateComment(@RequestBody Comments c,HttpSession session) throws Exception{
        Map<String, Object> userInfo = (Map<String, Object>) session.getAttribute("UserInfo");
        Users user = (Users) userInfo.get("User");
        commentService.CreateComment(c,user);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @GetMapping("getSingle/{id}")
    public ResponseEntity<Comments> GetSingleComment(@PathVariable String id) throws Exception {
        return new ResponseEntity<Comments>(commentService.getSingleComment(id), HttpStatus.OK);
    }

    @GetMapping("listAll")
    public ResponseEntity<List<Comments>> ListAllComments() throws Exception{
        return new ResponseEntity<List<Comments>>(commentService.listAllComments(), HttpStatus.OK);
    }

    @GetMapping("getByObjId/{id}")
    public ResponseEntity<List<Comments>> GetByObjId(@PathVariable String id) throws Exception{
        return new ResponseEntity<List<Comments>>(commentService.listCommentsByObjId(id), HttpStatus.OK);
    }
}
