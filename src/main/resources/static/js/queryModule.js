
app.controller('AlertQueryCtrl', function($scope, $http, $location, $state, $timeout){
    $scope.alertSearch = {};
    $scope.data = [];

    $scope.searchQueryAlert = function () {
        $http.post("/querys/search", $scope.alertSearch)
            .then(function success(response) {
                $scope.data = response.data;
            }, function error() {
                console.log(error);
            });
    }

    $scope.reset = function () {
        $scope.data = [];
        $scope.alertSearch = {};
    }

    $scope.getTotal = function () {
        var totalAmt = 0.0;
        for (var key in $scope.data) {
            if (parseFloat($scope.data[key].alerts.totalAmt)) {
                totalAmt += parseFloat($scope.data[key].alerts.totalAmt);
            }
        }
        return totalAmt;
    }
});

app.controller('CaseQueryCtrl', function($scope, $http, $location, $state, $timeout){

});
