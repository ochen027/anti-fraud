let app = angular.module('AMLapp', ['ngAnimate', 'ui.router', 'anim-in-out', 'ui.bootstrap']);

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
        },
        {
            name: "myAlert",
            url: "/alert/myAlert",
            views: {
                header: {
                    templateUrl: '/header'
                },
                content: {
                    templateUrl: '/alert/myAlert'
                },
                footer: {
                    templateUrl: '/footer',
                }
            },
            controller: 'MyAlertCtrl'
        },
        {
            name: "myAlertInfo",
            url: "/alert/myAlertInfo",
            views: {
                header: {
                    templateUrl: '/header'
                },
                content: {
                    templateUrl: '/alert/myAlertInfo'
                },
                footer: {
                    templateUrl: '/footer',
                }
            },
            controller: 'MyAlertInfoCtrl'
        },
        {
            name: "availableAlertInfo",
            url: "/alert/availableAlertInfo",
            views: {
                header: {
                    templateUrl: '/header'
                },
                content: {
                    templateUrl: '/alert/availableAlertInfo'
                },
                footer: {
                    templateUrl: '/footer',
                }
            },
            controller: 'AvailableAlertInfoCtrl'
        },
        {
            name: "suppressedAlertInfo",
            url: "/alert/suppressedAlertInfo",
            views: {
                header: {
                    templateUrl: '/header'
                },
                content: {
                    templateUrl: '/alert/suppressedAlertInfo'
                },
                footer: {
                    templateUrl: '/footer',
                }
            },
            controller: 'SuppressedAlertInfoCtrl'
        },
        {
            name: "closedAlertInfo",
            url: "/alert/closedAlertInfo",
            views: {
                header: {
                    templateUrl: '/header'
                },
                content: {
                    templateUrl: '/alert/closedAlertInfo'
                },
                footer: {
                    templateUrl: '/footer',
                }
            },
            controller: 'ClosedAlertInfoCtrl'
        },
        {
            name: "suppressedAlert",
            url: "/alert/suppress",
            views: {
                header: {
                    templateUrl: '/header'
                },
                content: {
                    templateUrl: '/alert/suppress'
                },
                footer: {
                    templateUrl: '/footer',
                }
            },
            controller: 'SuppressedAlertCtrl'
        },
        {
            name: "closedAlert",
            url: "/alert/closed",
            views: {
                header: {
                    templateUrl: '/header'
                },
                content: {
                    templateUrl: '/alert/closed'
                },
                footer: {
                    templateUrl: '/footer',
                }
            },
            controller: 'ClosedAlertCtrl'
        }
    ]


    for (var i = 0, len = alertModule.length; i < len; i++) {
        $stateProvider.state(alertModule[i]);
    }

    $urlRouterProvider.otherwise('/login');
});


app.controller('HeaderCtrl', function ($scope, $http, $location, $state, $timeout) {


    $scope.user = {name: "Default User"};
    $scope.activeMenu = "dashboard";
    $scope.menu = {
        dashboard: {
            name: "Dashboard",
            url: "/#!/dashboard",
        },
        alert: {
            name: "Alerts",
            children: [{
                name: "My Alerts",
                url: "/#!/alert/myAlert",
            }, {
                name: "Available Alerts",
                url: "/#!/alert/available",
            }, {
                name: "Suppressed Alerts",
                url: "/#!/alert/suppress",
            }, {
                name: "Closed Alerts",
                url: "/#!/alert/closed",
            }
            ]
        },
        query: {
            name: "Query",
            children: [{
                name: "My Query",
                url: "/#!/alert/my_alerts",
            }, {
                name: "Available Query",
                url: "/#!/alert/available_alerts",
            }]
        },
        reports: {
            name: "Reports",
            children: [{
                name: "My Reports",
                url: "/#!/alert/my_alerts",
            }, {
                name: "Available Reports",
                url: "/#!/alert/available_alerts",
            }]
        },
    }


});



