app.controller('LoginCtrl', function ($scope,$state, $http, $location, $timeout) {


    $scope.user = {username: "test1"};

    $scope.doLogin = function () {

        console.log("test");
        $state.go("dashboard");
    }
});

