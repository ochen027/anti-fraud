package com.pwc.aml.comments.entity;

import java.time.LocalDateTime;

public class Comments {

    private String commentId;
    private String alertId;
    private String commentContents;
    private String commentCreatedBy;
    private LocalDateTime commentCreatedDate;

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public String getAlertId() {
        return alertId;
    }

    public void setAlertId(String alertId) {
        this.alertId = alertId;
    }

    public String getCommentContents() {
        return commentContents;
    }

    public void setCommentContents(String commentContents) {
        this.commentContents = commentContents;
    }

    public String getCommentCreatedBy() {
        return commentCreatedBy;
    }

    public void setCommentCreatedBy(String commentCreatedBy) {
        this.commentCreatedBy = commentCreatedBy;
    }

    public LocalDateTime getCommentCreatedDate() {
        return commentCreatedDate;
    }

    public void setCommentCreatedDate(LocalDateTime commentCreatedDate) {
        this.commentCreatedDate = commentCreatedDate;
    }
}
