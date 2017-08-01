let app = angular.module('AMLapp', ['ngAnimate', 'ngCookies', 'ui.router', 'anim-in-out',
    'ui.bootstrap', 'chart.js', 'ngFileUpload', 'angularUUID2', 'smart-table','ngDialog']);

app.run(['$templateCache', function ($templateCache) {
    $templateCache.remove("template/smart-table/pagination.html");
    $templateCache.put('template/smart-table/pagination.html',
        '<nav ng-if="numPages && pages.length >= 2"><ul class="pagination">' +
        '<li class="page-item" ng-repeat="page in pages" ng-class="{active: page==currentPage}"><a class="page-link" href="javascript: void(0);" ng-click="selectPage(page)">{{page}}</a></li>' +
        '</ul></nav>');

}]);

app.config(function ($stateProvider, $urlRouterProvider) {

    let app = {};

    app.dashboard = {
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


    app.batch = {
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

    app.authModule = [
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



    app.alertModule = [
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
            params: {id: null}
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




    app.document = {
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


    app.workflow = [
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




    app.users = [
        {
            name: "IndexUsers",
            url: "/users/list",
            views: {
                header: {
                    templateUrl: '/header'
                },
                content: {
                    templateUrl: '/users/list'
                },
                footer: {
                    templateUrl: '/footer',
                }
            },
            controller: 'IndexUsersCtrl'
        },
        {
            name: "EditUsers",
            url: "/users/update",
            views: {
                header: {
                    templateUrl: '/header'
                },
                content: {
                    templateUrl: '/users/update'
                },
                footer: {
                    templateUrl: '/footer',
                }
            },
            controller: 'EditUsersCtrl'
        }
    ];

    app.system = [{
        name: "ImportData",
        url: "/system/importData",
        views: {
            header: {
                templateUrl: '/header'
            },
            content: {
                templateUrl: '/system/importData'
            },
            footer: {
                templateUrl: '/footer',
            }
        },
        controller: 'ImportDataCtrl'
    },
     {
        name: "userManagement",
        url: "/system/userManagement",
        views: {
            header: {
                templateUrl: '/header'
            },
            content: {
                templateUrl: '/system/userManagement'
            },
            footer: {
                templateUrl: '/footer',
            }
        },
        controller: 'UserManagementCtrl'
    },
    {
              name: "riskCountry",
              url: "/system/riskCountry",
              views: {
                  header: {
                      templateUrl: '/header'
                  },
                  content: {
                      templateUrl: '/system/riskCountry'
                  },
                  footer: {
                      templateUrl: '/footer',
                  }
              },
              controller: 'riskCountryCtrl'
    },
    {
                   name: "watchList",
                   url: "/system/watchList",
                   views: {
                       header: {
                           templateUrl: '/header'
                       },
                       content: {
                           templateUrl: '/system/watchList'
                       },
                       footer: {
                           templateUrl: '/footer',
                       }
                   },
                   controller: 'watchListCtrl'
         }
    ,{
        name: "userManagementInfo",
        url: "/system/userManagementInfo",
        views: {
            header: {
                templateUrl: '/header'
            },
            content: {
                templateUrl: '/system/userManagementInfo'
            },
            footer: {
                templateUrl: '/footer',
            }
        },
        controller: 'UserManagementInfoCtrl',
        params: {user: null}
    },
    {
        name: "userGroup",
        url: "/system/userGroup",
        views: {
            header: {
                templateUrl: '/header'
            },
            content: {
                templateUrl: '/system/userGroup'
            },
            footer: {
                templateUrl: '/footer',
            }
        },
        controller: 'UserGroupCtrl'
    },
    {
        name: "userGroupInfo",
        url: "/system/userGroupInfo",
        views: {
            header: {
                templateUrl: '/header'
            },
            content: {
                templateUrl: '/system/userGroupInfo'
            },
            footer: {
                templateUrl: '/footer',
            }
        },
        controller: 'UserGroupInfoCtrl',
        params: {group: null}
    },
    {
        name: "roleList",
        url: "/system/roleList",
        views: {
            header: {
                templateUrl: '/header'
            },
            content: {
                templateUrl: '/system/roleList'
            },
            footer: {
                templateUrl: '/footer',
            }
        },
        controller: 'RoleListCtrl'
    },
    {
        name: "roleInfo",
        url: "/system/roleInfo",
        views: {
            header: {
                templateUrl: '/header'
            },
            content: {
                templateUrl: '/system/roleInfo'
            },
            footer: {
                templateUrl: '/footer',
            }
        },
        controller: 'RoleInfoCtrl',
        params: {role: null}
    }];

    app.scenario=[
        {
            name: "scenario",
            url: "/scenario/scenario",
            views: {
                header: {
                    templateUrl: '/header'
                },
                content: {
                    templateUrl: '/scenario/scenario'
                },
                footer: {
                    templateUrl: '/footer',
                }
            },
            params: {scenario: null},
            controller: 'ScenarioCtrl'
        },
        {
            name: "scenarioManager",
            url: "/scenario/scenarioManager",
            views: {
                header: {
                    templateUrl: '/header'
                },
                content: {
                    templateUrl: '/scenario/scenarioManager'
                },
                footer: {
                    templateUrl: '/footer',
                }
            },

            controller: 'ScenarioManagerCtrl'

        },
        {
            name: "rules",
            url: "/scenario/rules",
            views: {
                header: {
                    templateUrl: '/header'
                },
                content: {
                    templateUrl: '/scenario/rules'
                },
                footer: {
                    templateUrl: '/footer',
                }
            },
            params: {rule: null},
            controller: 'RulesCtrl'
        },
        {
            name: "rulesManager",
            url: "/scenario/rulesManager",
            views: {
                header: {
                    templateUrl: '/header'
                },
                content: {
                    templateUrl: '/scenario/rulesManager'
                },
                footer: {
                    templateUrl: '/footer',
                }
            },
            controller: 'RulesManagerCtrl'
        }
    ]


    /***
     * for supporting muti-level
     * @param obj
     */
    function loadingRouter(obj) {
        if (Array.isArray(obj)) {
            for(let i=0;i<obj.length;i++){
                loadingRouter(obj[i]);
            }
        } else {
            $stateProvider.state(obj);
        }
    }

    for(let key in app){
        loadingRouter(app[key]);
    }

    $urlRouterProvider.otherwise('/login');
});


app.controller('HeaderCtrl', function ($scope, $http, $location, $cookies, $cookieStore, $state, $timeout, $loginService) {

    /**
     * initial header
     */
    $timeout(function () {
        let user = $cookieStore.get('user');
        if (!user) {
            alert("login expired! please login again!");
            $state.go('login');
            return;
        }

        if (!window.$$userInfo) {
            $loginService(user, function (res) {
                $scope.user = window.$$userInfo.User;
                $scope.menu = window.$$userInfo.Menus;
            });
        } else {
            $scope.user = window.$$userInfo.User;
            $scope.menu = window.$$userInfo.Menus;
        }
    });

    $scope.hasChildren = function (menu) {
        return (menu.childList && menu.childList.length !== 0) ? true : false;
    }

    $scope.logout = function () {
        $cookieStore.remove('user');
        $state.go('login');
    }
});




