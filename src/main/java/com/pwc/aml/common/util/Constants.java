package com.pwc.aml.common.util;

public class Constants {

    //For Public
    public static final String CREATED_BY = "createdBy";
    public static final String CREATED_DATE = "createdDate";
    public static final String F1= "f1";
    public static final String TEMP = "temp";
    public static final String COMMA = ",";
    public static final String LAST_UPDATE_BY = "lastUpdatedBy";
    public static final String LAST_UPDATE_DATE = "lastUpdatedDate";

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
    public static final String COLUMN_ALERT_CONTENT = "alertContent";
    public static final String COLUMN_ALERT_CREATED_DATE = "alertCreatedDate";
    public static final String COLUMN_ALERT_DESC = "alertDesc";
    public static final String COLUMN_ALERT_NAME = "alertName";
    public static final String COLUMN_BUSINESS_DATE = "businessDate";
    public static final String COLUMN_TOTAL_AMOUNT = "totalAmt";
    public static final String COLUMN_TRANS_ID_ARRAY = "transIdArray";
    public static final String HBASE_TABLE_ALERT= "aml:alerts";

    //For Work Object
    public static final String COLUMN_WORK_OBJECT_ID = "workObjId";
    public static final String COLUMN_CURRENT_POINT_ID = "currentPointId";


    //For Customer
    public static final String COLUMN_CUSTOMER_ID = "customerId";

    //For Scenario
    public static final String COLUMN_SCENARIO_ID = "scenarioId";

    //For Transactions
    public static final String HBASE_TABLE_TRANS = "aml:trans";
    public static final String COLUMN_TRANS_DT = "trans_dt";
    public static final String COLUMN_TRANS_TYPE = "trans_type";
    public static final String COLUMN_TRANS_ID = "trans_id";




    //For Accoount
    public static final String COLUMN_ACCT_ID = "acct_id";
    public static final String COLUMN_ACCOUNT_ID = "accountId";
}
