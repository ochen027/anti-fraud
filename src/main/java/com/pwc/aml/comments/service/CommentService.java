package com.pwc.aml.comments.service;

import com.pwc.aml.comments.dao.ICommentDAO;
import com.pwc.aml.comments.entity.Comments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService implements ICommentService {

    @Autowired
    private ICommentDAO commentDAO;

    @Override
    public void CreateComment(Comments c) throws Exception{
        commentDAO.createComment(c);
    }

    @Override
    public void DeleteComment(String commentId) throws Exception{
        commentDAO.removeComment(commentId);
    }

    @Override
    public void UpdateComment(Comments c) throws Exception{
        commentDAO.updateComment(c);
    }

    @Override
    public Comments getSingleComment(String commentId) throws Exception{
        return commentDAO.getSingleComment(commentId);
    }

    @Override
    public List<Comments> listAllComments() throws Exception{
        return commentDAO.getAllComments();
    }

    @Override
    public List<Comments> listCommentsByAlert(String alertId) {
        return null;
    }
}
