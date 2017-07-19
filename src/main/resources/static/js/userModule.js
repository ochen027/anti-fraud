/**
 * Created by ochen027 on 7/18/2017.
 */


app.controller('AllUsersCtrl', function($scope, $http, $location, $state, $timeout){
    $scope.allUsersData = [];
    $scope.allUsersDataDisplay=[];
    $timeout(function () {

        $http.get("/user/listAllUsers").then(function (res) {
            if (res.code !== 200) {
                console.log(res);
                return;
            }

            $scope.allUsersData=res.data;
            $scope.allUsersDataDisplay=res.data.splice();


        });
    })

});
