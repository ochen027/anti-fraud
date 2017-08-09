package com.pwc.aml.comments.controller;


import com.pwc.aml.comments.entity.Comments;
import com.pwc.aml.comments.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("comments")
public class CommentsController {

    @Autowired
    private ICommentService commentService;

    @PostMapping("create")
    public ResponseEntity<Void> CreateComment(@RequestBody Comments c) throws Exception{
        commentService.CreateComment(c);
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

    @GetMapping("getByAlert/{id}")
    public ResponseEntity<List<Comments>> GetByAlert(@PathVariable String id) throws Exception{
        return new ResponseEntity<List<Comments>>(commentService.listCommentsByAlert(id), HttpStatus.OK);
    }
}
