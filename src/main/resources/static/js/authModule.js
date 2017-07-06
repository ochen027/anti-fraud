app.controller('LoginCtrl', function ($scope,$state, $http, $location,$cookies,$cookieStore, $timeout) {
    $scope.user = {};

    $scope.doLogin = function () {

        $http.post("/user/loginUser",$scope.user).then(function(res){

            if(!(res.status===200)){
                   alert("user name or password error");
                   $("#userName").focus();
                   return;
            }

            var dt = new Date();
            dt.setMinutes(dt.getMinutes() + 30);
            $cookieStore.put('userInfo', res.data,{ expires: dt });
            $state.go("dashboard");
        });
    }
});

