app.controller('RulesManagerCtrl', function ($scope, $http, $location, $state, $timeout) {

    $scope.goToScenarioManager = function () {
        $state.go("scenarioManager");
    }

    $scope.goToAddRule = function () {
        $state.go("rules");
    }

    $scope.refresh = function () {
        $http.get("/rules/listAll").then(function (res) {
            if (res.status != 200) {
                console.log(res);
                return;
            }

            $scope.rules=res.data;
            $scope.rulesDisplay=res.data.slice(0);
        });
    }

    $timeout(function () {

    });

});

app.controller('RulesCtrl', function ($scope, $http, $location, $state, $timeout) {
    $scope.goToRuleManager = function () {
        $state.go("rulesManager");
    }

    $scope.save = function () {

    }
});

app.controller('ScenarioManagerCtrl', function ($scope, $http, $location, $state, $timeout) {


    $scope.goToRuleManager = function () {
        $state.go("rulesManager");
    }

    $scope.goToAddScenario = function () {
        $state.go("scenario");
    }

});

app.controller('ScenarioCtrl', function ($scope, $http, $location, $state, $timeout) {

    $scope.goToScenarioManager = function () {
        $state.go("scenarioManager");
    }

    $scope.save = function () {

    }

});