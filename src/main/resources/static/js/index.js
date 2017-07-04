let app = angular.module('AMLapp', ['ngAnimate', 'ui.router', 'anim-in-out','ui.bootstrap']);

app.config(function ($stateProvider, $urlRouterProvider) {

    let dashboard = {
        name: 'dashboard',
        url: '/dashboard',
        views: {
            header: {
                templateUrl: '/header'
            },
            content: {
                templateUrl: '/dashboard'
            },
            footer: {
                templateUrl: '/footer',
            }
        },
        controller: 'DashboardCtrl'
    }
    $stateProvider.state(dashboard);

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

    for (var i = 0, len = authModule.length; i < len; i++) {
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


    for (var i = 0, len = alertModule.length; i < len; i++) {
        $stateProvider.state(alertModule[i]);
    }

    $urlRouterProvider.otherwise('/login');
});


app.controller('HeaderCtrl', function ($scope, $http, $location, $state, $timeout) {


        $scope.user = {name: "Default User"};
        $scope.activeMenu="dashboard";
        $scope.menu = {
            dashboard: {
                name: "Dashboard",
                url: "/#!/dashboard",
            },
            alert: {
                name: "Alerts",
                children: [{
                    name: "My Alerts",
                    url: "/#!/alert/my_alerts",
                }, {
                    name: "Available Alerts",
                    url: "/#!/alert/available_alerts",
                }]
            },
            query: {
                name: "Query",
                children: [{
                    name: "My Alerts",
                    url: "/#!/alert/my_alerts",
                }, {
                    name: "Available Alerts",
                    url: "/#!/alert/available_alerts",
                }]
            },
            query: {
                name: "Query",
                children: [{
                    name: "My Alerts",
                    url: "/#!/alert/my_alerts",
                }, {
                    name: "Available Alerts",
                    url: "/#!/alert/available_alerts",
                }]
            },
            reports: {
                name: "Reports",
                children: [{
                    name: "My Alerts",
                    url: "/#!/alert/my_alerts",
                }, {
                    name: "Available Alerts",
                    url: "/#!/alert/available_alerts",
                }]
            },
        }

});



