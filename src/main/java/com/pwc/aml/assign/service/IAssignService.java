package com.pwc.aml.assign.service;


import com.pwc.aml.assign.entity.Assign;
import com.pwc.component.authorize.users.entity.Users;

import java.util.List;

public interface IAssignService {

    void AssignTo(Users users, String[] ObjectId,Users currentUser) throws Exception;

    List<Assign> getAssignByUser(Users users) throws Exception;
}
