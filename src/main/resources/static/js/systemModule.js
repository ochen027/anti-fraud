app.controller('ImportDataCtrl', function ($scope, $http, $location, $state, $timeout, Upload) {


    $scope.uploadFiles = function (files) {

        if (files && files.length) {
            Upload.upload({
                url: '/customer/upload',
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
