package com.pwc.component.comments.dao;

import com.pwc.component.comments.entity.Comments;

import java.util.List;

public interface ICommentDAO {

    void createComment(Comments c) throws Exception;

    Comments getSingleComment(String commentId) throws Exception;

    List<Comments> getAllComments() throws Exception;

    List<Comments> getCommentsListByObjId(String ObjId) throws Exception;
}
