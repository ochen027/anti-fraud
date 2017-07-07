/***
 * user:{}
 */
app.factory('$loginService', function ($state, $cookieStore, $http) {
    return function (user, callback) {
        $http.post("/user/loginUser", user).then(function (res) {
            if (res.status === 200) {
                var dt = new Date();
                dt.setMinutes(dt.getMinutes() + 30);
                window.$$userInfo = res.data;
                $cookieStore.put('user', user, {expires: dt});
            }
            callback(res);
        });
    }
});


app.controller('LoginCtrl', function ($scope, $state, $http, $location, $cookies, $cookieStore, $timeout, $loginService) {
    $scope.user = {};

    $scope.doLogin = function () {
        $loginService($scope.user, function (res) {
            if (res.status === 200) {
                $state.go("dashboard");
            } else {
                alert("user name or password error");
                $("#userName").focus();
            }
        });
    };

    // $scope.doLogin = function () {
    //     $http.post("/user/loginUser", $scope.user).then(function (res) {
    //         if (!(res.status === 200)) {
    //             alert("user name or password error");
    //             $("#userName").focus();
    //             return;
    //         }
    //
    //         var dt = new Date();
    //         dt.setMinutes(dt.getMinutes() + 30);
    //         window.$$userInfo = res.data;
    //         $cookieStore.put('user', $scope.user, {expires: dt});
    //         $state.go("dashboard");
    //     });
    // }
});

