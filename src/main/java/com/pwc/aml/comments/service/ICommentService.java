package com.pwc.aml.comments.service;

import com.pwc.aml.comments.entity.Comments;

import java.util.List;

public interface ICommentService {

    void CreateComment(Comments c) throws Exception;

    void DeleteComment(String commentId)  throws Exception;

    void UpdateComment(Comments c) throws Exception;

    Comments getSingleComment(String commentId) throws Exception;

    List<Comments> listAllComments() throws Exception;

    List<Comments> listCommentsByAlert(String alertId) throws Exception;
}
