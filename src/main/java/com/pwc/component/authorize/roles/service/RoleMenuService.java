package com.pwc.component.authorize.roles.service;
import com.pwc.component.authorize.roles.dao.IRoleMenuDAO;
import com.pwc.component.authorize.roles.entity.RoleMenu;
import com.pwc.component.authorize.roles.entity.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class RoleMenuService implements IRoleMenuService {

    @Autowired
    IRoleMenuDAO roleMenuDAO;

    @Override
    public List<RoleMenu> getRoleMenuByRole(int roleId) {
        return roleMenuDAO.getRoleMenuByRole(roleId);
    }

    @Override
    public RoleMenu createRoleMenu(RoleMenu rm, String roleName) {
        return roleMenuDAO.createRoleMenu(rm);
    }

    @Override
    public void createRoleMenu(Roles r, List<RoleMenu> lrm,String roleName) {
        for(int i=0; i<lrm.size(); i++){
            RoleMenu rm = (RoleMenu) lrm.get(i);
            RoleMenu tempRm = roleMenuDAO.getRoleMenu(rm.getRoleId(),rm.getMenuId());

            if(tempRm == null){
                rm.setRoleId(r.getId());
                rm.setCreatedBy(roleName);
                rm.setCreationDate(new Date());
                rm.setLastUpdateDate(new Date());
                rm.setLastUpdatedBy(roleName);
                rm.setStatus(true);
                this.createRoleMenu(rm,roleName);
            }else{
                //if exist, do nothing
            }

        }

    }

    @Override
    public RoleMenu RemoveRoleMenu(RoleMenu rm, String roleName) {
        return roleMenuDAO.RemoveRoleMenu(rm);
    }

    @Override
    public List<RoleMenu> getRoleMenu(){
        return roleMenuDAO.getRoleMenu();
    }

}
