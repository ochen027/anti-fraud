package com.pwc.aml.comments.entity;

public class Comments {

    private String commentId;
    private String alertId;
    private String commentContents;
    private String commentCreatedBy;
    private String commentCreatedDate;

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

    public String getCommentCreatedDate() {
        return commentCreatedDate;
    }

    public void setCommentCreatedDate(String commentCreatedDate) {
        this.commentCreatedDate = commentCreatedDate;
    }
}
