package com.pwc.component.comments.service;

import com.pwc.common.util.FormatUtils;
import com.pwc.component.authorize.users.entity.Users;
import com.pwc.component.comments.dao.ICommentDAO;
import com.pwc.component.comments.entity.Comments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class CommentService implements ICommentService {

    @Autowired
    private ICommentDAO commentDAO;

    @Override
    public void CreateComment(Comments c, Users users) throws Exception{
        c.setCommentId(UUID.randomUUID().toString());
        c.setCommentCreatedBy(users.getUserName());
        c.setCommentCreatedDate(FormatUtils.DateToString(new Date()));
        commentDAO.createComment(c);
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
    public List<Comments> listCommentsByObjId(String ObjId) throws Exception{
        return commentDAO.getCommentsListByObjId(ObjId);
    }
}
