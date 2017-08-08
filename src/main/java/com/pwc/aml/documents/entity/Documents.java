package com.pwc.aml.documents.entity;

public class Documents {

    private String docId;
    private String docPath;
    private String workObjId;
    private String docCreatedBy;
    private String docCreatedDate;

    public String getDocId() {
        return docId;
    }

    public void setDocId(String docId) {
        this.docId = docId;
    }

    public String getDocPath() {
        return docPath;
    }

    public void setDocPath(String docPath) {
        this.docPath = docPath;
    }

    public String getWorkObjId() {
        return workObjId;
    }

    public void setWorkObjId(String workObjId) {
        this.workObjId = workObjId;
    }

    public String getDocCreatedBy() {
        return docCreatedBy;
    }

    public void setDocCreatedBy(String docCreatedBy) {
        this.docCreatedBy = docCreatedBy;
    }

    public String getDocCreatedDate() {
        return docCreatedDate;
    }

    public void setDocCreatedDate(String docCreatedDate) {
        this.docCreatedDate = docCreatedDate;
    }
}
