package com.pwc.component.assign.service;


import com.pwc.component.assign.entity.Assign;
import com.pwc.component.authorize.users.entity.Users;

import java.util.List;

public interface IAssignService {

    void assignTo(Users by, Users to, List<String> ObjectId, Users currentUser) throws Exception;

    List<Assign> getAssignByUser(Users users) throws Exception;

    void unAssign(Users by, List<String> ObjectIds, Users currentUser) throws Exception;
}
