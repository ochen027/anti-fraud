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
                url: flag === 'customer' ? '/customer/upload' : '/account/upload',
                data: {file: files[0]}
            }).then(function (res) {
                $scope.refresh();
            });
        }
    }


    $scope.refresh=function(){
        $http.get("/customer/findAll").then(function(res){
            $scope.customers=res.data;
            $scope.customersDisplay=res.data.slice();
        });
    };


    $scope.deleteAll=function(){
        $http.get("/customer/removeAll").then(function(res){
            $scope.refresh();
        });
    };


    $timeout(function(){
        $scope.refresh();
    });

});

app.controller('UserManagementCtrl', function ($scope, $http, $location, $state, $timeout) {

});
