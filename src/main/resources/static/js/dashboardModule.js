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
        //$scope.getWorkflow();
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

    /**
    $scope.getSARData = function () {
        $scope.sarData = {
            labels:["Jan", "Feb", "Mar", "Apr", "May"],
            data:[
                ["10","10","30","70","98"],
                ["12","90","80","26","19"],
                ["19","28","34","18","87"]
            ]
        }
    }
    **/


    $scope.dsOverride = [
        {
            label: "SAR",
            yAxisID: "bar-stacked"
        },
        {
            label: "Total Alerts",
            yAxisID: "bar-stacked"
        },
        {
            label: "High Risk Customer Alerts",
            hoverBackgroundColor: "rgba(255,99,132,0.4)",
            hoverBorderColor: "rgba(255,99,132,1)",
            type: 'line'
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
                        stacked: false,
                        position: "right",
                        display: true,
                        ticks: {
                            beginAtZero: true
                        }
                    }, {
                        id: "bar-stacked",
                        stacked: true,
                        position: "left",
                        display: true, //optional if both yAxes use the same scale
                        ticks: {
                            beginAtZero: true
                        },
                        type: 'linear'
                    }
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


