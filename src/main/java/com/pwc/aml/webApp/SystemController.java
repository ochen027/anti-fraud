package com.pwc.aml.webApp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("system")
public class SystemController {

    @RequestMapping("/importData")
    public String importData(Model model) {
        return "system/importData";
    }


    @RequestMapping("/userManagement")
    public String userManagement(Model model) {
        return "system/userManagement";
    }

    @RequestMapping("/riskCountry")
    public String riskCountry(Model model) {
        return "system/riskCountry";
    }
    @RequestMapping("/riskCountryInfo")
    public String riskCountryInfo(Model model){return "system/riskCountry_edit";}
    @RequestMapping("/watchList")
    public String watchList(Model model) {
        return "system/watchList";
    }
    @RequestMapping("/watchListInfo")
    public String watchListInfo(Model model){return "system/watchList_edit";}


    @RequestMapping("/userManagementInfo")
    public String userManagementInfo(Model model) {
        return "system/userManagement_edit";
    }

    @RequestMapping("/userGroup")
    public String userGroup(Model model) {
        return "system/user_group";
    }
    @RequestMapping("/userGroupInfo")
    public String userGroupInfo(Model model) {
        return "system/user_group_edit";
    }
    @RequestMapping("/roleList")
    public String roleList(Model model) {
        return "system/role_list";
    }
    @RequestMapping("/roleInfo")
    public String roleInfo(Model model) {
        return "system/role_edit";
    }
    @RequestMapping("/menuList")
    public String menuList(Model model) {
        return "system/menu_list";
    }
    @RequestMapping("/roleMenu")
    public String roleMenu(Model model) {
        return "system/roleMenu";
    }
    @RequestMapping("/menuInfo")
    public String menuInfo(Model model) {
        return "system/menu_edit";
    }
    @RequestMapping("/roleMenuInfo")
    public String roleMenuInfo(Model model) {
        return "system/roleMenuEdit";
    }



}
