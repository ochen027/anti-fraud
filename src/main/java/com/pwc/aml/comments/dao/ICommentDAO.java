package com.pwc.aml.comments.dao;

import com.pwc.aml.comments.entity.Comments;

import java.util.List;

public interface ICommentDAO {

    void createComment(Comments c) throws Exception;

    Comments getSingleComment(String commentId) throws Exception;

    List<Comments> getAllComments() throws Exception;

    List<Comments> getCommentsListByAlert(String alertId) throws Exception;
}
