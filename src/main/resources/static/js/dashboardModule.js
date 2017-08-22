app.controller('DashboardCtrl', function ($scope, $http, $location, $state, $timeout) {

    $scope.currentTab="day"

    $scope.navigate = function (flag) {
        $scope.currentTab = flag;
    }

    $scope.workflow = {};

    $scope.chartOption = {
        legend: {
            display: true
        }
    };

    $scope.getWorkflow = function () {

        return new Promise(function (resolve, reject) {
            //$http.get

            $scope.dashboard = [{label: ["point1","others"], data:[20,80], pointName:"point1"},
                {label: ["point2","total"], data:[30,80], pointName:"point2"},
                {label: ["point3","total"], data:[40,80], pointName:"point3"},
                {label: ["point4","total"], data:[10,80], pointName:"point4"},
                {label: ["point5","total"], data:[10,80], pointName:"point5"},
               ];

            resolve();
        });
    }

    //
    $timeout(function () {
        $scope.getWorkflow();
    });



});


