app.controller('RulesManagerCtrl', function ($scope, $http, $location, $state, $timeout) {

    $scope.goToScenarioManager = function () {
        $state.go("scenarioManager");
    }

    $scope.goToAddRule = function () {
        $state.go("rules");
    }

    $scope.edit=function(rule){
        $state.go("rules",{rule:rule});
    }

    $scope.refresh = function () {
        $http.get("/rules/listAllRules").then(function (res) {
            if (res.status != 200) {
                console.log(res);
                return;
            }

            $scope.rules = res.data;
            $scope.rulesDisplay = res.data.slice(0);
        });
    }

    $timeout(function () {
        $scope.refresh();
    });

});

app.controller('RulesCtrl', function ($scope, $http, $location, $state, $timeout,$stateParams) {

    $scope.rules = {};
    $scope.scenariosSelected = [];
    $scope.scenarios = [];
    $scope.goToRuleManager = function () {
        $state.go("rulesManager");
    }

    $scope.save = function () {

        var selected = [];
        for (let i = 0; i < $scope.scenariosSelected.length; i++) {
            selected.push($scope.scenariosSelected[i].id);
        }
        $scope.rules.scenarios = selected;

        $http.post("/rules/saveOrUpdateRules", $scope.rules).then(function (res) {

            $scope.rules = res.data;
            alert("saved!");
        });
    }

    $scope.removeScenario = function (item) {
        var index = $scope.scenariosSelected.indexOf(item);
        $scope.scenariosSelected.splice(index, 1);
        $scope.scenariosSelectedDisplay = $scope.scenariosSelected.slice(0);
        $scope.scenarios.push(item);
        $scope.scenariosDisplay = $scope.scenarios.slice(0);
    }

    $scope.addScenario = function (item) {
        var index = $scope.scenarios.indexOf(item);
        $scope.scenarios.splice(index, 1);
        $scope.scenariosDisplay = $scope.scenarios.slice(0);
        $scope.scenariosSelected.push(item);
        $scope.scenariosSelectedDisplay = $scope.scenariosSelected.slice(0);
    }

    var findRuleScenarioByRuleId=function(rules){

        $http.post("/rules/listRuleScenarioByRuleId",rules).then(function(res){

            let scenarios=res.data;

            for(let k in scenarios){
                for(let key in $scope.scenarios){
                   if(scenarios[k].scenarioId===$scope.scenarios[key].id){
                       $scope.addScenario($scope.scenarios[key]);
                       break;
                   }
                }
            }

        });
    }

    $scope.refresh = function () {
        $http.get("/rules/listAll").then(function (res) {
            $scope.scenarios = res.data;
            $scope.scenariosDisplay = res.data.slice(0);
            if($scope.rules){
                findRuleScenarioByRuleId($scope.rules);
            }
        });
    }

    $timeout(function () {
        $scope.rules=$stateParams.rule;
        $scope.refresh();
    });

});

app.controller('ScenarioManagerCtrl', function ($scope, $http, $location, $state, $timeout) {


    $scope.scenarios = [];

    $scope.goToRuleManager = function () {
        $state.go("rulesManager");
    }

    $scope.goToAddScenario = function () {
        $state.go("scenario");
    }

    $scope.refresh = function () {
        $http.get("/rules/listAll").then(function (res) {
            $scope.scenarios = res.data;
            $scope.scenariosDisplay = res.data.slice(0);
        })
    }

    $scope.editScenario = function (scenario) {
        $state.go("scenario", {scenario: scenario});
    }

    $timeout(function () {
        $scope.refresh();
    });

});

app.controller('ScenarioCtrl', function ($scope, $http, $location, $state, $timeout, $stateParams) {

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

            $scope.scenario = res.data;
            alert("scenario saved! ");

        })
    }

    $timeout(function () {
        $scope.scenario = $stateParams.scenario;
    })


});