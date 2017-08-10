app.controller('RulesManagerCtrl', function ($scope, $http, $location, $state, $timeout) {

    $scope.goToScenarioManager = function () {
        $state.go("scenarioManager");
    }

    $scope.goToAddRule = function () {
        $state.go("rules");
    }

    $scope.edit = function (rule) {
        $state.go("rules", {rule: rule});
    }

    $scope.defaultRule = 0;

    $scope.select = function (row) {
        $scope.defaultRule = row.id;
        $http.post("/rules/setDefaultRuleId", row).then(function (res) {
            //$scope.defaultRule=res.data;
        });
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

    $scope.run = function () {
        $http.get("/rules/runRule/" + $scope.defaultRule).then(function (res) {
            alert("running rules");
        });

    }

    $timeout(function () {
        $scope.refresh();

        //get default rule

        $http.get("/rules/getDefaultRuleId").then(function (res) {
            $scope.defaultRule = res.data;
        });
    });

});

app.controller('RulesCtrl', function ($scope, $http, $location, $state, $timeout, $stateParams) {

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

    var findRuleScenarioByRuleId = function (rules) {

        $http.post("/rules/listRuleScenarioByRuleId", rules).then(function (res) {

            let scenarios = res.data;

            for (let k in scenarios) {
                for (let key in $scope.scenarios) {
                    if (scenarios[k].scenarioId === $scope.scenarios[key].id) {
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
            if ($scope.rules) {
                findRuleScenarioByRuleId($scope.rules);
            }
        });
    }

    $timeout(function () {
        $scope.rules = $stateParams.rule;
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
    $scope.currentTab = "table";
    $scope.checkList = {
        // condition: {
        //     reg: ,
        //     values: [],
        //     contain: false
        // },
        alertDesc: {
            reg: /hBaseDAO.putData\(table, alertId, \"f1\", \"alertDesc\", \"(.)*\"\);/,
            content:"hBaseDAO.putData(table, alertId, \"f1\", \"alertDesc\", \"$value\");",
            values: "",
            contain: false
        }, alertName: {
            reg: /hBaseDAO.putData\(table, alertId, \"f1\", \"alertName\", \"(.)*\"\);/,
            content:"hBaseDAO.putData(table, alertId, \"f1\", \"alertName\", \"$value\");",
            value: "",
            contain: false
        }, alertContent: {
            reg:/hBaseDAO.putData\(table, alertId, \"f1\", \"alertContent\", \"(.)*\"\);/,
            content: "hBaseDAO.putData(table, alertId, \"f1\", \"alertContent\", \"$value\");",
            value: "",
            contain: false
        }
    };

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
        $scope.scenario.scenarioContent = $scope.scenario.scenarioContent.replace(/\\n/g, "\n").replace(/\\"/g, '"');
        $scope.tableLine = [];
        $scope.scenario.scenarioTable = $scope.scenario.scenarioContent.split("\n");
        runCheckList($scope.scenario.scenarioTable);
    });

    $scope.writeBack=function(){
        let list=$scope.scenario.scenarioTable;
        for (let i = 0; i < list.length; i++) {
            for (let key in $scope.checkList) {
                let result=list[i].match($scope.checkList[key].reg);
                if(result!== null){
                    var line=$scope.checkList[key].content.replace("$value",$scope.checkList[key].value);
                    list[i]=list[i].replace($scope.checkList[key].reg,line);
                }
            }
        }
        $scope.scenario.scenarioContent=$scope.scenario.scenarioTable.join("\n");
    }

    function runCheckList(list) {
        for (let i = 0; i < list.length; i++) {
            for (let key in $scope.checkList) {
                let result=list[i].match($scope.checkList[key].reg);
                if(result!== null){
                    $scope.checkList[key].contain=true;
                    let emptyString=$scope.checkList[key].content.split("$value");
                    let tempValue=result[0];
                    for(let j=0;j<emptyString.length;j++ ){
                        tempValue=tempValue.replace(emptyString[j],"");
                    }
                    $scope.checkList[key].value= tempValue;
                }
            }
        }
    }

});