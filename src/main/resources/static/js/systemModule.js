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
    $http.get("/userManagement/userGroup").then(function(res){
        console.log(res);
        $scope.userGroup = res.data.data;
    },function(error){
        consle.log(error.statusCode() + "-->" + error.statusText);
    })

});
app.controller('UserGroupInfoCtrl', function ($scope, $http, $location, $state, $timeout,$stateParams) {


});
app.controller('RoleListCtrl', function ($scope, $http, $location, $state, $timeout) {
    console.log("usermanagement/roleList");
    $http.get("/userManagement/roleList").then(function(res){
        console.log(res);
        $scope.rolesList = res.data.data;
    },function(error){
        consle.log(error.statusCode() + "-->" + error.statusText);
    })

});
app.controller('RoleInfoCtrl', function ($scope, $http, $location, $state, $timeout) {


});


