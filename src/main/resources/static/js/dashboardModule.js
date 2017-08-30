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
        $scope.getStatusData();
        $scope.getSARData();
        $scope.getDueData();

    });


    $scope.barDataOptions = {
        options: {
            scales: {
                xAxes: [{
                    stacked: true
                }],
                yAxes: [{
                    stacked: true
                }]
            }
        }
    }

    $scope.statusData = {};
    $scope.getStatusData = function () {
            $http.get("/dashboard/assign").then(function sucess(result) {
                console.log(result);
                $scope.statusData = result.data
            }, function error(error) {
                console.log(error);
    });
    }

    $scope.colors = ['#45b7cd', '#ff6384', '#ff8e72'];

    $scope.sarData = {};
    $scope.getSARData = function () {
        $http.get("/dashboard/sar").then(function sucess(result) {
            console.log(result);
            $scope.sarData = result.data
        }, function error(error) {
            console.log(error);
        });
    }

    $scope.datasetOverride = [
        {
            label: "SAR",
            borderWidth: 1,
            type: 'bar',
            yAxisID: 'y-axis-1'
        },
        {
            label: "Total Alerts",
            borderWidth: 1,
            type: 'bar',
            yAxisID: 'y-axis-2'
        },
        {
            label: "High Risk Customer Alerts",
            borderWidth: 3,
            hoverBackgroundColor: "rgba(255,99,132,0.4)",
            hoverBorderColor: "rgba(255,99,132,1)",
            type: 'line',
            yAxisID: 'y-axis-3'
        }
    ];


    $scope.mixOptions = {
        options: {
            scales: {
                xAxes: [{
                    stacked: true
                }],
                yAxes: [
                {
                    id: 'y-axis-1',
                    display: false,
                    position: 'left',
                    stacked: true
                },
                {
                    id: 'y-axis-2',
                    display: true,
                    position: 'left',
                    stacked: true
                },
                {
                    id: 'y-axis-3',
                    type: 'linear',
                    display: true,
                    position: 'right'
                },

                ]
            }
        }
    }

    $scope.dueData = {};
    $scope.getDueData = function () {
        $http.get("/dashboard/due").then(function sucess(result) {
            console.log(result);
            $scope.dueData = result.data
        }, function error(error) {
            console.log(error);
        });
    }


});


