app.controller('LoginCtrl', function ($scope,$state, $http, $location, $timeout) {
    $scope.user = {};

    $scope.doLogin = function () {

        $http.post("/user/loginUser",$scope.user).then(function(res){

            if(!(res.status===200)){
                   alert("user name or password error");
                   $("#userName").focus();
                   return;
            }

            window.$$userInfo=res.data;
            $state.go("dashboard");
        });
    }
});

