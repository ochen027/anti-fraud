let app = angular.module('AMLapp', ['ngAnimate','ngCookies', 'ui.router', 'anim-in-out',
    'ui.bootstrap','chart.js','ngFileUpload','angularUUID2','smart-table']);

app.run(['$templateCache', function ($templateCache){
    $templateCache.remove("template/smart-table/pagination.html");
    $templateCache.put('template/smart-table/pagination.html',
        '<nav ng-if="numPages && pages.length >= 2"><ul class="pagination">' +
        '<li class="page-item" ng-repeat="page in pages" ng-class="{active: page==currentPage}"><a class="page-link" href="javascript: void(0);" ng-click="selectPage(page)">{{page}}</a></li>' +
        '</ul></nav>');

}]);

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

    let batch = {
        name: 'transBatch',
        url: '/batch',
        views: {
            header: {
                templateUrl: '/header'
            },
            content: {
                templateUrl: '/batch/trans'
            },
            footer: {
                templateUrl: '/footer',
            }
        },
        controller: 'TransBatchCtrl'
    }
    $stateProvider.state(batch);

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
    ];


    for (var i = 0, len = alertModule.length; i < len; i++) {
        $stateProvider.state(alertModule[i]);
    }


    let document={
            name: "documentExample",
            url: "/document/index",
            views: {
                header: {
                    templateUrl: '/header'
                },
                content: {
                    templateUrl: '/document/index'
                },
                footer: {
                    templateUrl: '/footer',
                }
            },
            controller: 'DocumentCtrl'
        };

    $stateProvider.state(document);

    let workflow =[
        {
            name: "IndexWorkflow",
            url: "/workflow/index",
            views: {
                header: {
                    templateUrl: '/header'
                },
                content: {
                    templateUrl: '/workflow/index'
                },
                footer: {
                    templateUrl: '/footer',
                }
            },
            controller: 'IndexWorkflowCtrl'
        },
        {
            name: "EditWorkflow",
            url: "/workflow/edit",
            params: {
                flowId: null
            },
            views: {
                header: {
                    templateUrl: '/header'
                },
                content: {
                    templateUrl: '/workflow/edit'
                },
                footer: {
                    templateUrl: '/footer',
                }
            },
            controller: 'EditWorkflowCtrl'
        }
    ];

    for (var i = 0, len = workflow.length; i < len; i++) {
        $stateProvider.state(workflow[i]);
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




