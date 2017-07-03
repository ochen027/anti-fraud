let app = angular.module('AMLapp', ['ngAnimate', 'ui.router', 'anim-in-out']);

app.config(function ($stateProvider, $urlRouterProvider) {

    let authModule = [
        {
            name: 'login',
            url: '/login',
            views: {
                content: {
                    templateUrl: '/login'
                },
                footer: {
                    templateUrl: '/footer',
                }
            },
            controller: 'LoginCtrl'
        }
    ];

    for(var i = 0, len = authModule.length; i < len; i++){
        $stateProvider.state(authModule[i]);
    }

    let alertModule = [
        {
            name: "available",
            url: "/alert/available",
            views: {
                header: {
                    templateUrl: '/header'
                },
                content: {
                    templateUrl: '/alert/available'
                },
                footer: {
                    templateUrl: '/footer',
                }
            },
            controller: 'AvailableAlertCtrl'
        }
    ]


    for(var i = 0, len = alertModule.length; i < len; i++){
        $stateProvider.state(alertModule[i]);
    }

    $urlRouterProvider.otherwise('/login');
});



app.controller('HeaderCtrl', function ($scope, $http, $location, $state,$timeout) {

    $timeout(function(){
        $scope.user={name:"default user"};

    });
});