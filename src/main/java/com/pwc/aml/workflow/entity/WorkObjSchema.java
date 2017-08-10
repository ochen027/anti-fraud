package com.pwc.aml.workflow.entity;

import com.pwc.aml.alert.entity.Alerts;
import com.pwc.component.workflow.entity.FlowEvent;

import javax.persistence.Column;
import java.util.Date;
import java.util.List;

/**
 * Created by whuang072 on 7/28/2017.
 */
public class WorkObjSchema {

    public static final String workObjectId="workObjectId";

    public static final String flowId="flowId";

    public static final String currentPointId="currentPointId";

    public static final String historyEvents="historyEvents";

    public static final String alertId="alertId";

    public static final String roleId="roleId";

    public static final String createdBy="createdBy";
    public static final String createdDate="createdDate";
    public static final String updateBy="updateBy";
    public static final String updateDate="updateDate";
    public static final String isActive="isActive";
    public static final String customerId = "customerId";
}
