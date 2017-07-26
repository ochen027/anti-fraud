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

            $scope.rules = res.data;
            $scope.rulesDisplay = res.data.slice(0);
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


    $scope.scenarios=[];

    $scope.goToRuleManager = function () {
        $state.go("rulesManager");
    }

    $scope.goToAddScenario = function () {
        $state.go("scenario");
    }

    $scope.refresh=function(){
        $http.get("/rules/listAll").then(function(res){
            $scope.scenarios=res.data;
            $scope.scenariosDisplay=res.data.slice(0);
        })
    }

    $timeout(function () {
        $scope.refresh();
    });

});

app.controller('ScenarioCtrl', function ($scope, $http, $location, $state, $timeout) {

    $scope.scenario = {};

    $scope.goToScenarioManager = function () {
        $state.go("scenarioManager");
    }

    $scope.save = function () {
        $http.post("/rules/saveOrUpdate", $scope.scenario).then(function (res) {
            if (res.status !== 200) {
                console.log(res);
                return;
            }

            $scope.scenario=res.data;
            alert("scenario saved! ");

        })
    }


});