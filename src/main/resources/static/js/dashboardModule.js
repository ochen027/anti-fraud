/**
 * Created by whuang072 on 7/3/2017.
 */
app.controller('DashboardCtrl', function ($scope, $http, $location, $state) {
    $scope.navigate = function (flag) {
        $scope.currentTab=flag;
    }

    $scope.datasetOverride = [{yAxisID: 'y-axis-1'}, {yAxisID: 'y-axis-2'}];
    $scope.optionsL1 = {
        legend: {
            display: true

        }
        // scales: {
        //     yAxes: [
        //         {
        //             id: 'y-axis-1',
        //             type: 'linear',
        //             display: true,
        //             position: 'left'
        //         },
        //         {
        //             id: 'y-axis-2',
        //             type: 'linear',
        //             display: true,
        //             position: 'right'
        //         }
        //     ]
        // }
    };
    $scope.optionsL2 = {
        legend: {
            display: true

        }
    }

    $scope.optionsQC = {
        legend: {
            display: true

        }
    }
    $scope.optionsMLRO = {
        legend: {
            display: true

        }
    }
    $scope.optionsClosed = {
        legend: {
            display: true

        }
    }
    $scope.labelsL1= ["Pending", "Finished"];
    $scope.dataL1 = [25, 75];
    $scope.labelsL2= ["Pending", "Finished"];
    $scope.dataL2 = [20, 80];
    $scope.labelsQC= ["Pending", "Finished"];
    $scope.dataQC = [20, 70];
    $scope.labelsMLRO= ["Pending", "Finished"];
    $scope.dataMLRO = [10, 60];
    $scope.labelsClosed= ["Pending", "Finished"];
    $scope.dataClosed = [5, 75];
});


