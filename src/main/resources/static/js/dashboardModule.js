/**
 * Created by whuang072 on 7/3/2017.
 */
app.controller('DashboardCtrl', function ($scope, $http, $location, $state) {
    $scope.labels = ["January", "February", "March", "April", "May", "June", "July"];
    $scope.series = ['Series A', 'Series B'];
    $scope.data = [
        [65, 59, 80, 81, 56, 55, 40],
        [28, 48, 40, 19, 86, 27, 90]
    ];
    $scope.onClick = function (points, evt) {
        console.log(points, evt);
    };
    $scope.datasetOverride = [{ yAxisID: 'y-axis-1' }, { yAxisID: 'y-axis-2' }];
    $scope.options = {
        scales: {
            yAxes: [
                {
                    id: 'y-axis-1',
                    type: 'linear',
                    display: true,
                    position: 'left'
                },
                {
                    id: 'y-axis-2',
                    type: 'linear',
                    display: true,
                    position: 'right'
                }
            ]
        }
    };

    $scope.labels3 = ["Download Sales", "In-Store Sales", "Mail-Order Sales"];
    $scope.data3 = [300, 500, 100];


    $scope.labels4 =["Eating", "Drinking", "Sleeping", "Designing", "Coding", "Cycling", "Running"];

    $scope.data4 = [
        [65, 59, 90, 81, 56, 55, 40],
        [28, 48, 40, 19, 96, 27, 100]
    ];


    $scope.labels5 = ["Download Sales", "In-Store Sales", "Mail-Order Sales", "Tele Sales", "Corporate Sales"];
    $scope.data5 = [300, 500, 100, 40, 120];

    $scope.series6 = ['Series A', 'Series B'];

    $scope.data6 = [
        [{
            x: 40,
            y: 10,
            r: 20
        }],
        [{
            x: 10,
            y: 40,
            r: 50
        }]
    ];

});

