let app = angular.module('AMLapp', ['ngAnimate','ngCookies', 'ui.router', 'anim-in-out', 'ui.bootstrap']);

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

    let transExample = {
        name: 'transExample',
        url: '/trans',
        views: {
            header: {
                templateUrl: '/header'
            },
            content: {
                templateUrl: '/trans'
            },
            footer: {
                templateUrl: '/footer',
            }
        },
        controller: 'TransCtrl'
    }
    $stateProvider.state(transExample);

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
            controller: 'MyAlertInfoCtrl',
            params: { id: null }
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
            name: "CreateAlert",
            url: "/alert/create",
            views: {
                header: {
                    templateUrl: '/header'
                },
                content: {
                    templateUrl: '/alert/create'
                },
                footer: {
                    templateUrl: '/footer',
                }
            },
            controller: 'CreateAlertCtrl'
        }
    ]


    for (var i = 0, len = alertModule.length; i < len; i++) {
        $stateProvider.state(alertModule[i]);
    }

    $urlRouterProvider.otherwise('/login');
});


app.controller('HeaderCtrl', function ($scope, $http, $location,$cookies,$cookieStore, $state, $timeout,$loginService) {

    /**
     * initial header
     */
    $timeout(function(){
        let user=$cookieStore.get('user');
        if(!user) {
            alert("login expired! please login again!");
            $state.go('login');
            return;
        }

        if(!window.$$userInfo){
            $loginService(user,function(res){
                $scope.user = window.$$userInfo.User;
                $scope.menu=window.$$userInfo.Menus;
            });
        }else{
            $scope.user = window.$$userInfo.User;
            $scope.menu=window.$$userInfo.Menus;
        }
    });

    $scope.hasChildren=function(menu){
        return (menu.childList&&menu.childList.length!==0)?true:false;
    }

    $scope.logout=function(){
        $cookieStore.remove('user');
        $state.go('login');
    }
});



