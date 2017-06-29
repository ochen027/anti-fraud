
let app = angular.module('AMLapp', ['ngAnimate', 'ui.router', 'anim-in-out']);

app.config(function($stateProvider, $urlRouterProvider) {
    let loginState = {
        name: 'login',
        url: '/login',
        views:{
            content:{
                templateUrl:'/login'
            },
        },
        controller: 'LoginCtrl'
    }

    let homeState = {
        name: 'home',
        url: '/home',
        views:{
            header:{
                templateUrl:'/header'
            },
            content:{
                templateUrl:'/home'
            },
            footer:{
                templateUrl:'/footer',
            }
        },
        controller: 'HomeCtrl'
    }

    $stateProvider.state(loginState);
    //$stateProvider.state(homeState);

    $urlRouterProvider.otherwise('/login');
});

app.controller('LoginCtrl', function ($scope, $http,$location,$state) {

});