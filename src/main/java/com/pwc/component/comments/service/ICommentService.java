package com.pwc.component.comments.service;

import com.pwc.component.authorize.users.entity.Users;
import com.pwc.component.comments.entity.Comments;

import java.util.List;

public interface ICommentService {

    void CreateComment(Comments c, Users users) throws Exception;

    Comments getSingleComment(String commentId) throws Exception;

    List<Comments> listAllComments() throws Exception;

    List<Comments> listCommentsByObjId(String ObjId) throws Exception;
}
