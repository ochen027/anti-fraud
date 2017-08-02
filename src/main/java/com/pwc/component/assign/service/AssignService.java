package com.pwc.component.assign.service;

import com.pwc.component.assign.dao.IAssignDao;
import com.pwc.component.assign.entity.Assign;
import com.pwc.component.assign.entity.AssignHistory;
import com.pwc.component.authorize.users.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@Service
public class AssignService implements IAssignService {

    @Autowired
    private IAssignDao assignDao;

    @Override
    public void assignTo(Users by,Users to, List<String> ObjectIds, Users currentUser) throws Exception {

        for (String id : ObjectIds) {
            Assign assign = assignDao.findByObjId(id);
            if (assign == null) {
                assign = new Assign();
                assign.setCreationDate(new Date());
                assign.setCreatedBy(currentUser.getUserName());
                assign.setStatus(true);
            } else {
                List<AssignHistory> history=assign.getHistory();
                if(history==null){
                    history=new ArrayList<>();
                }
                AssignHistory assignHistory=new AssignHistory();
                assignHistory.setAssignDate(assign.getLastUpdateDate());
                assignHistory.setById(assign.getById());
                assignHistory.setuObjId(assign.getuObjId());
                history.add(assignHistory);
            }
            assign.setAssignId(UUID.randomUUID().toString());
            assign.setuObjId("" + to.getId());
            assign.setObjId(id);
            assign.setById("" + by.getId());
            assign.setLastUpdatedBy(currentUser.getUserName());
            assign.setLastUpdateDate(new Date());
            assignDao.save(assign);
        }
    }

    @Override
    public List<Assign> getAssignByUser(Users users) throws Exception {

        return assignDao.findByUserId("" + users.getId());
    }

    @Override
    public void unAssign(Users by,List<String> ObjectIds, Users currentUser) throws Exception {
        assignTo( by,null, ObjectIds, currentUser);
    }
}
