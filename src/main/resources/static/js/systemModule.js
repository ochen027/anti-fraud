app.controller('ImportDataCtrl', function ($scope, $http, $location, $state, $timeout, Upload) {

    $scope.toggleCustomer = true;
    $scope.toggleAccount = false;
    $scope.navigate = function(flag){
        if(flag === "customer"){
            $scope.toggleCustomer = true;
            $scope.toggleAccount = false;
        }
        if(flag === "account"){
            $scope.toggleCustomer = false;
            $scope.toggleAccount = true;
        }
    }
    $scope.uploadFiles = function (files,flag) {

        if (files && files.length) {
            Upload.upload({
                url: flag==='customer' ?'/customer/upload' : '/account/upload',
                data: {file: files[0]}
            }).then(function (res) {
                $scope.refresh(flag);
            });
        }
    }


    $scope.refresh=function(flag){
        if(flag === 'customer'){
            $http.get("/customer/findAll").then(function(res){
                $scope.customers=res.data;
                $scope.customersDisplay=res.data.slice();
            });
        }
        if(flag === 'account') {
            $http.get("/account/findAll").then(function(res){
                $scope.accounts=res.data;
                $scope.accountsDisplay=res.data.slice();
            });
        }

    };


    $scope.deleteAll=function(flag){
        if(flag === 'customer') {
            $http.get("/customer/removeAll").then(function(res){
                         $scope.refresh(flag);
                     });
                 }
        if(flag === 'account') {
            $http.get("/account/removeAll").then(function(res){
                $scope.refresh(flag);
            });
        }

    };


    $timeout(function(){
        $scope.refresh();
    });

});

app.controller('UserManagementCtrl', function ($scope, $http, $location, $state, $timeout) {
    console.log("usermanagement/userlist");
    $http.get("/userManagement/userList").then(function(res){
        console.log(res);
        $scope.users = res.data.data;
    },function(error){
        consle.log(error.statusCode() + "-->" + error.statusText);
    })

});
app.controller('UserManagementInfoCtrl', function ($scope, $http, $location, $state, $timeout) {


});
app.controller('UserGroupCtrl', function ($scope, $http, $location, $state, $timeout) {
    console.log("usermanagement/userGroup");
    $http.get("/group/listAllGroups").then(function(res){
        console.log(res);
        $scope.userGroup = res.data;
    },function(error){
        console.log(error.statusCode() + "-->" + error.statusText);
    })

    $scope.edit = function(group) {
        group.action="edit";
        $state.go("userGroupInfo",{group:group});
    }

    $scope.delete = function(groupId){
        $http.put("/group/deleteGroup/"+groupId).then(function(res){
            console.log(res)
            $scope.refresh();
        })
    }

    $scope.addGroup = function(){
        $state.go("userGroupInfo",{group:{"action":"add"}});
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
app.controller('UserGroupInfoCtrl', function ($scope, $http, $location, $state, $timeout,$stateParams) {
    $scope.group = {};
    $scope.group.groupRole = {};

    //checkbox selected
    $scope.updateSelected = function(action, roleId,groupId){
          let idx = -1;
        if($scope.group.groupRole && $scope.group.groupRole.length != 0){

            for(let i =0; i< $scope.group.groupRole.length; i++){
                if(roleId === $scope.group.groupRole[i].roleId){
                    idx = i;
                    break;
                }
            }
        }

        if(action.target.checked && idx == -1){
            $scope.group.groupRole.push({"roleId":roleId,"groupId":groupId});
        }
        if(!action.target.checked && idx !=-1) {
            $scope.group.groupRole.splice(idx,1);
        }
    }

    //save
    $scope.save = function(){
        $scope.group.groupRolesList = $scope.group.groupRole;
        $http.post("/group/saveOrUpdateGroup", $scope.group).then(function(res){
            if (res.status !== 200) {
                console.log(res);
                return;
            }

            $scope.role = res.data;
            alert("role saved! ");
        })
    }
    //go back
    $scope.gotoGroupList = function(){
        $state.go("userGroup");
    }
    //refresh
    $scope.refresh = function () {
        $http.get("/roles/listAll").then(function(res){
            $scope.group.roles = res.data;
            if($scope.group.id){
                $http.get("/group/listGroupRoles/"+$stateParams.group.id).then(function (resp) {
                    $scope.group.groupRole = resp.data;
                    $scope.setRoles($scope.group.roles,$scope.group.groupRole);
                })
            }else{
                $scope.group.groupRole=[];
            }

        })
    }
    $scope.setRoles = function(roles,groupRoles){
        if(groupRoles != "undefined" && groupRoles != null &&
            roles != "undefined" && roles != null){
            for(let role in roles){
                for(let groupRole in groupRoles){
                    if(groupRoles[groupRole].roleId === roles[role].id){
                        roles[role].isChecked = true;
                    }else{
                        roles[role].isChecked ?  roles[role].isChecked = true : roles[role].isChecked = false;
                    }

                }
            }

        }
    }


    $timeout(function () {
        $scope.group = $stateParams.group;

        $scope.refresh();


        //

    })

});
app.controller('RoleListCtrl', function ($scope, $http, $location, $state, $timeout) {
    console.log("roles/roleList");
    $http.get("/roles/listAll").then(function(res){
        console.log(res);
        $scope.rolesList = res.data;
    },function(error){
        consle.log(error.statusCode() + "-->" + error.statusText);
    })

    $scope.edit = function(role) {
        role.action=true;
        $state.go("roleInfo",{role:role});
    }
    $scope.delete = function(roleId){
        console.log("delete role");
        $http.post("/roles/deleteRole/"+roleId).then(function(res){
            console.log(res)
            $scope.refresh();
        })
    }
    $scope.addRole = function(){
        $state.go("roleInfo",{role:{"action":true}});
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

    $scope.save = function(){
        $http.post("roles/createRole",$scope.role).then(function(res){
            if (res.status !== 200) {
                console.log(res);
                return;
            }

            $scope.role = res.data;
            alert("role saved! ");
        })
    }
    $scope.gotoRoleList = function(){
        $state.go("roleList");
    }

    $timeout(function () {
        $scope.role = $stateParams.role;
    })

});



app.controller('riskCountryCtrl', function ($scope, $http, $location, $state, $timeout) {

});

