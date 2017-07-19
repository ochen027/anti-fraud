/**
 * Created by ochen027 on 7/18/2017.
 */


app.controller('IndexUsersCtrl', function($scope, $http, $location, $state, $timeout){
    $scope.allUsersData = [];
    $scope.allUsersDataDisplay=[];
    $timeout(function () {

        $http.get("/user/listAllUsers").then(function (res) {
            if (res.status !== 200) {
                console.log(res);
                return;
            }
            $scope.allUsersData=res.data;
            $scope.allUsersDataDisplay=res.data.splice();


        });
    })

});
