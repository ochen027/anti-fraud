app.controller('ImportDataCtrl', function ($scope, $http, $location, $state, $timeout, Upload) {

    $scope.toggleCustomer = true;
    $scope.toggleAccount = false;
    $scope.navigate = function (flag) {
        if (flag === "customer") {
            $scope.toggleCustomer = true;
            $scope.toggleAccount = false;
        }
        if (flag === "account") {
            $scope.toggleCustomer = false;
            $scope.toggleAccount = true;
        }
    }
    $scope.uploadFiles = function (files, flag) {

        if (files && files.length) {
            Upload.upload({
                url: flag === 'customer' ? '/customer/upload' : '/account/upload',
                data: {file: files[0]}
            }).then(function (res) {
                $scope.refresh(flag);
            });
        }
    }


    $scope.refresh = function (flag) {
        if (flag === 'customer') {
            $http.get("/customer/findAll").then(function (res) {
                $scope.customers = res.data;
                $scope.customersDisplay = res.data.slice();
            });
        }
        if (flag === 'account') {
            $http.get("/account/findAll").then(function (res) {
                $scope.accounts = res.data;
                $scope.accountsDisplay = res.data.slice();
            });
        }

    };


    $scope.deleteAll = function (flag) {
        if (flag === 'customer') {
            $http.get("/customer/removeAll").then(function (res) {
                $scope.refresh(flag);
            });
        }
        if (flag === 'account') {
            $http.get("/account/removeAll").then(function (res) {
                $scope.refresh(flag);
            });
        }

    };


    $timeout(function () {
        $scope.refresh();
    });

});
app.controller('UserManagementCtrl', function ($scope, $http, $location, $state, $timeout) {
    console.log("usermanagement/userlist");
    $scope.users = {};

    $scope.search = function () {
        let userId = $scope.user.search.userId ? $scope.user.search.userId : 0;
        $http.get("/user/searchUsers?userId=" + userId + "&userName=" + $scope.user.search.userName).then(function (res) {
            $scope.users = res.data;
        })
    }
    $scope.reset = function () {
        $scope.user.search.userId = "";
        $scope.user.search.groupId = "";
        $scope.user.search.userName = "";
    }

    $scope.edit = function (user) {
        user.action = "edit";
        $state.go("userManagementInfo", {user: user});
    }
    $scope.delete = function (userId) {
        $http.put("/user/deleteUser/" + userId).then(function (res) {
            console.log(res)
            $scope.refresh();

        })
    }
    $scope.addUser = function () {
        $state.go("userManagementInfo", {user: {"action": "add"}});
    }

    $scope.refresh = function () {
        $http.get("/user/listAllUsers").then(function (res) {
            console.log(res);
            $scope.users = res.data;
        }, function (error) {
            consle.log(error.statusCode() + "-->" + error.statusText);
        })
    }

    $timeout(function () {
        $scope.refresh();
    });

});
app.controller('UserManagementInfoCtrl', function ($scope, $http, $location, $state, $timeout, $stateParams, ngDialog) {
    $scope.user = {};
    $scope.user.groups = {};
    $scope.user.userGroups = {};

    $scope.gotoUserList = function () {
        $state.go("userManagement");
    }
    $scope.save = function () {
        $scope.user.userGroupList = this.setNewSelectedUserGroup($scope.user.groups);
        $http.post("/user/saveOrUpdateUser", $scope.user).then(function (res) {
            if (res.status != 200) {
                console.log(res);
                alert("Save Failed!");
                return;
            }
            //$scope.user = res.data;
            alert("user saved");
        })
    }
    $scope.changePwd = function (user) {
        $scope.user = user;
        ngDialog.open({
            template: 'changePwd',
            width: '40%',
            scope: $scope,
            controller: function ($scope) {
                $scope.savePwd = function () {
                    if ($scope.newPwd != $scope.confirmPwd) {
                        alert("Entered passwords differ! ");
                        return;
                    }
                    $scope.user.userPwd = $scope.newPwd;
                    $http.put("/user/updateUser", $scope.user).then(function (res) {
                        if (res.status != 200) {
                            alert("Password Changed Failed!");
                            return;
                        }
                        alert("Password Changed! ");
                        $scope.closeThisDialog();
                    })

                }
                $scope.cancel = function () {
                    $scope.closeThisDialog();
                }

            }
        });
    }

    $scope.setNewSelectedUserGroup = function (groups) {
        let ugArr = [];
        groups.forEach(function (ele, index) {
            if (ele.isChecked) {
                ugArr.push({"groupId": ele.id});
            }
        })
        return ugArr;
    }

    $scope.refresh = function () {
        $http.get("/group/listAllGroups").then(function (res) {
            $scope.user.groups = res.data;
            if ($scope.user.id) {
                $http.get("/user/listUserGroups/" + $stateParams.user.id).then(function (resp) {
                    $scope.user.userGroups = resp.data;
                    $scope.setCheckedGroups($scope.user.groups, $scope.user.userGroups);
                })
            }
        })
    }
    $scope.setCheckedGroups = function (groups, userGroups) {
        if (userGroups != "undefined" && userGroups != null &&
            groups != "undefined" && groups != null) {

            let ugIdArray = [];
            for (let ug in userGroups) {
                ugIdArray.push(userGroups[ug].groupId);
            }
            for (let g in groups) {
                if (ugIdArray.indexOf(groups[g].id) != -1) {
                    groups[g].isChecked = true;
                } else {
                    groups[g].isChecked = false;
                }
            }
        }
    }
    $timeout(function () {
        $scope.user = $stateParams.user;

        $scope.refresh();

    })

});
app.controller('UserGroupCtrl', function ($scope, $http, $location, $state, $timeout) {
    console.log("usermanagement/userGroup");
    $http.get("/group/listAllGroups").then(function (res) {
        console.log(res);
        $scope.userGroup = res.data;
    }, function (error) {
        console.log(error.statusCode() + "-->" + error.statusText);
    })

    $scope.edit = function (group) {
        group.action = "edit";
        $state.go("userGroupInfo", {group: group});
    }

    $scope.delete = function (groupId) {
        $http.put("/group/deleteGroup/" + groupId).then(function (res) {
            console.log(res);
            $scope.refresh();
        })
    }

    $scope.addGroup = function () {
        $state.go("userGroupInfo", {group: {"action": "add"}});
    }

    $scope.refresh = function () {
        $http.get("/group/listAllGroups").then(function (res) {
            $scope.userGroup = res.data;

        })
    }

    $timeout(function () {
        $scope.refresh();
    });
});
app.controller('UserGroupInfoCtrl', function ($scope, $http, $location, $state, $timeout, $stateParams) {
    $scope.group = {};
    $scope.group.roles = {};
    $scope.group.groupRole = {};

    //save
    $scope.save = function () {
        $scope.group.groupRolesList = $scope.setNewSelectedGroupRole($scope.group.roles);
        $http.post("/group/saveOrUpdateGroup", $scope.group).then(function (res) {
            if (res.status !== 200) {
                console.log(res);
                return;
            }
            // $scope.group = res.data;
            alert("group saved! ");
        })
    }
    //go back
    $scope.gotoGroupList = function () {
        $state.go("userGroup");
    }
    $scope.setNewSelectedGroupRole = function (roles) {
        let grArr = [];
        roles.forEach(function (ele, index) {
            if (ele.isChecked) {
                grArr.push({"roleId": ele.id});
            }
        })
        return grArr;
    }
    //refresh
    $scope.refresh = function () {
        $http.get("/roles/listAll").then(function (res) {
            $scope.group.roles = res.data;
            if ($scope.group.id) {
                $http.get("/group/listGroupRoles/" + $stateParams.group.id).then(function (resp) {
                    $scope.group.groupRole = resp.data;
                    $scope.setRoles($scope.group.roles, $scope.group.groupRole);
                })
            } else {
                $scope.group.groupRole = [];
            }

        })
    }
    $scope.setRoles = function (roles, groupRoles) {
        if (groupRoles != "undefined" && groupRoles != null &&
            roles != "undefined" && roles != null) {
            let grIdArray = [];
            for (let gr in groupRoles) {
                grIdArray.push(groupRoles[gr].roleId)
            }
            for (let r in roles) {
                if (grIdArray.indexOf(roles[r].id) != -1) {
                    roles[r].isChecked = true;
                } else {
                    roles[r].isChecked = false;
                }
            }

        }
    }


    $timeout(function () {
        $scope.group = $stateParams.group;

        $scope.refresh();

    })
});
app.controller('RoleListCtrl', function ($scope, $http, $location, $state, $timeout) {
    console.log("roles/roleList");
    $http.get("/roles/listAll").then(function (res) {
        console.log(res);
        $scope.rolesList = res.data;
    }, function (error) {
        consle.log(error.statusCode() + "-->" + error.statusText);
    })

    $scope.edit = function (role) {
        role.action = true;
        $state.go("roleInfo", {role: role});
    }
    $scope.delete = function (roleId) {
        console.log("delete role");
        $http.post("/roles/deleteRole/" + roleId).then(function (res) {
            console.log(res)
            $scope.refresh();
        })
    }
    $scope.addRole = function () {
        $state.go("roleInfo", {role: {"action": true}});
    }

    $scope.refresh = function () {
        $http.get("/roles/listAll").then(function (res) {
            $scope.rolesList = res.data;

        })
    }

    $timeout(function () {
        $scope.refresh();
    });

});
app.controller('RoleInfoCtrl', function ($scope, $http, $location, $state, $timeout, $stateParams) {
    $scope.role = {};

    $scope.save = function () {
        $http.post("roles/createRole", $scope.role).then(function (res) {
            if (res.status !== 200) {
                console.log(res);
                return;
            }

            $scope.role = res.data;
            alert("role saved! ");
        })
    }
    $scope.gotoRoleList = function () {
        $state.go("roleList");
    }

    $timeout(function () {
        $scope.role = $stateParams.role;
    })

});
app.controller('RiskCountryCtrl', function ($scope, $http, $location, $state, $timeout, Upload) {
    $scope.toggleRiskCountry = true;
    $scope.uploadFiles = function (files) {

        if (files && files.length) {
            Upload.upload({
                url: '/riskCountry/upload',
                data: {file: files[0]}
            }).then(function (res) {
                $scope.refresh();
            });
        }
    }
    $scope.edit = function (riskCountry) {
        riskCountry.action =true;
        $state.go("riskCountryInfo", {riskCountry: riskCountry});
    }
    $scope.delete = function (riskCountry) {
        $http.post("/riskCountry/delete" , riskCountry).then(function (res) {
            console.log(res);
            $scope.refresh();
        })
    }


    $scope.refresh = function () {
        $http.get("/riskCountry/getAll").then(function (res) {
            $scope.riskCountry = res.data;
            $scope.riskCountryDisplay = res.data.slice();
        });

    };


    $scope.deleteAll = function () {
        $http.get("/riskCountry/removeAll").then(function (res) {
            $scope.refresh();
        });


    };

    $timeout(function () {
        $scope.refresh();
    });

});
app.controller('riskCountryInfoCtrl', function ($scope, $http, $location, $state, $timeout, $stateParams) {
    $scope.riskCountry = {};

    $scope.save = function () {
        $http.post("riskCountry/saveOrUpdate", $scope.riskCountry).then(function (res) {
                if (res.status !== 200) {
                console.log(res);
                return;
            }
            alert("RiskCountry Saved! ");
            //$scope.riskCountry = res.data;

        })
    }
    $scope.goback = function () {
        $state.go("riskCountry");
    }

    $timeout(function () {
        $scope.riskCountry = $stateParams.riskCountry;
    })

});
app.controller('MenuListCtrl', function($scope, $http, $location, $state, $timeout){
    console.log("menus/menuList");
    $http.get("/menus/findAll").then(function(res){
        console.log(res);
        $scope.menusList=res.data;
        $scope.menusListDisplay=res.data.slice(0);
    },function(error){
        consle.log(error.statusCode()+"-->"+error.statusText);
    })
    $scope.edit=function(menu){
        menu.action=true;
        $state.go("menuInfo",{menu:menu});
    }
    $scope.delete=function(id){
        console.log("delete menu");
        $http.post("/menus/deleteMenus/"+id).then(function(res){
            console.log(res)
            $scope.refresh();
        })
    }
    $scope.addMenu=function(){
        $state.go("menuInfo",{menu:{"action":true}});
    }

    $scope.refresh = function () {
        $http.get("/menus/findAll").then(function (res) {
            $scope.menusList = res.data;
            $scope.menusListDisplay = res.data.slice();
        });
    }
    });
app.controller('MenuInfoCtrl',function($scope,$http,$location,$state,$timeout,$stateParams){
    $scope.menu={};

    $scope.save=function(){
        $http.post("menus/createMenus",$scope.menu).then(function(res){
            if(res.status!==200){
                console.log(res);
                return;
            }

        //    $scope.menu=res.data;
            alert("menu saved!");
        })
    }
    $scope.gotoMenuList=function(){
        $state.go("menuList");
    }

    $timeout(function(){
        $scope.menu=$stateParams.menu;
    })

});
app.controller('RoleMenuCtrl', function ($scope, $http, $location, $state, $timeout) {
    console.log("rolemanagement/roleMenu");
    $scope.edit = function (role) {
        role.action = "false";
        $state.go("roleMenuInfo", {role: role});
    }
    $scope.delete = function (roleId) {
        $http.post("/roles/deleteRole/" + roleId).then(function (res) {
            console.log(res);
            $scope.refresh();
        })
    }
    $scope.addRole = function () {
        $state.go("roleMenuInfo", {role: {"action": "false"}});
    }
    $scope.refresh = function () {
        $http.get("/roles/listAll").then(function (res) {
            $scope.roleMenu = res.data;
        })
    }
    $timeout(function () {
        $scope.refresh();
    });
});
app.controller('RoleMenuInfoCtrl', function ($scope, $http, $location, $state, $timeout, $stateParams) {
    $scope.roles = {};
    $scope.roles.menus = {};
    $scope.roles.roleMenu = {};
    $scope.save = function () {
        $http.post("roles/createRole", $scope.role).then(function (res) {
            if (res.status !== 200) {
                console.log(res);
                return;
            }

            $scope.role = res.data;
            alert("role saved! ");
        })
    }
    $scope.gotoRoleList = function () {
        $state.go("roleMenu");
    }
    $scope.refresh = function () {
        $http.get("/roles/getRoleMenuByRole").then(function(res){
            console.log(res);
            $scope.roleMenu=res.data;
            $scope.roleMenuDisplay=res.data.slice(0);
        },function(error){
            consle.log(error.statusCode()+"-->"+error.statusText);
        })}
    $scope.delete=function(id, roleName){
        console.log("delete menu");
        $http.post("/roles/deleteMenuFromRole/"+id,+roleName).then(function(res){
            console.log(res)
            $scope.refresh();
        })
    }
    $scope.refresh = function () {
        $http.get("/roles/getRoleMenuByRole").then(function (res) {
            $scope.roleMenu = res.data;
            roleMenuDisplay        });
    }
    $timeout(function () {
        $scope.roles = $stateParams.roles;
        $scope.refresh();
    })
});
