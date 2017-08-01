package com.pwc.aml.assign.service;

import com.pwc.aml.assign.dao.IAssignDao;
import com.pwc.aml.assign.entity.Assign;
import com.pwc.component.authorize.users.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;


@Service
public class AssignService implements IAssignService {

    @Autowired
    private IAssignDao assignDao;

    @Override
    public void AssignTo(Users users, List<String> ObjectIds,Users currentUser) throws Exception {

        for(String id : ObjectIds){
            Assign assign= new Assign();
            assign.setAssignId(UUID.randomUUID().toString());
            assign.setUserId(""+users.getId());
            assign.setObjectId(id);
            assign.setCreationDate(new Date());
            assign.setCreatedBy(currentUser.getUserName());
            assign.setStatus(true);
            assignDao.save(assign);
        }
    }

    @Override
    public List<Assign> getAssignByUser(Users users) throws Exception {

       return  assignDao.findByUserId(""+users.getId());
    }
}
