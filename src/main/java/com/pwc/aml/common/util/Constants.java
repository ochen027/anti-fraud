package com.pwc.aml.common.util;

public class Constants {

    //For Public
    public static final String CREATED_BY = "createdBy";
    public static final String CREATED_DATE = "createdDate";
    public static final String F1= "f1";
    public static final String TEMP = "temp";

    //For Document
    public static final String HBASE_TABLE_DOCUMENTS = "aml:documents";
    public static final String HDFS_FILE_PATH = "/user/hadoop/tmp/sampleData/";
    public static final String COLUMN_DOCUMENT_FILE_PATH = "filePath";

    //For Comments
    public static final String HBASE_TABLE_COMMENTS = "aml:comments";
    public static final String COLUMN_COMMENTS_CONTENTS = "commentContents";
    public static final String COLUMN_COMMENTS_CREATED_BY = "commentCreatedBy";
    public static final String COLUMN_COMMENTS_CREATED_DATE = "commentCreatedDate";

    //For Alerts
    public static final String COLUMN_ALERT_ID = "alertId";
    public static final String HBASE_TABLE_ALERT= "aml:alerts";

    //For Work Object
    public static final String COLUMN_WORK_OBJECT_ID = "workObjId";

}
