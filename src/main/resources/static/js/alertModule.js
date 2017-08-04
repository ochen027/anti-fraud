

app.controller('MyAlertCtrl', function ($scope, $http, $location, $state) {
    console.log("/alert/myAlert");

    $scope.myAlertData = [];
    $scope.myAlertDataDisplay = [];
    $scope.conf = [];
    $scope.select = [];

    $http.get("/workflow/getMyAlerts")
        .then(function (response) {
            console.log(response);
            $scope.myAlertData = response.data;
            $scope.myAlertDataDisplay = response.data.splice();
        });

    //clear select
    $scope.clearSelect = function () {
        console.log("my alert clear select");
        $scope.checkAll = false;
        if ($scope.conf.length != 0) {
            angular.forEach($scope.conf, function (ele, index) {
                $scope.conf[index] = false;
            })
        }
    }

    //combox select
    $scope.checkList = [];
    $scope.conf = [];
    $scope.select = function (action, record, index) {
        console.log("my alert checkbox select");
        console.log($scope.conf[index]);
        if (action.target.checked) {
            $scope.checkList.push({"record": record, "index": index});
        }
    }

    //Calculate Total Amount
    $scope.getMyAlertTotal = function () {
        var totalAmt = 0.0;
        for (var key in $scope.myAlertData) {
            if (parseFloat($scope.myAlertData[key].alerts.totalAmt)) {
                totalAmt += parseFloat($scope.myAlertData[key].alerts.totalAmt);
            }
        }
        return totalAmt;
    }

    $scope.itemsByPage = 10;

});


app.controller('AvailableAlertCtrl', function ($scope, $http, $location, $state, $timeout, $stateParams) {

    $scope.checkAll = false; //default false;

    $scope.data = [];

    $scope.itemsByPage = 10;


    $scope.refresh = function () {
        $http.get("/workflow/getDefaultWorkflow").then(function (res) {

            $scope.workflow = res.data;
            for (let i = 0; i < $scope.workflow.flowPoints.length; i++) {
                if ($scope.workflow.flowPoints[i].print.indexOf($stateParams.id)>=0) {
                    $scope.flowPointId = $scope.workflow.flowPoints[i].flowPointId;
                    $http.get("/workflow/getWorkObjsByPointId?flowPointId=" + $scope.flowPointId)
                        .then(function (response) {
                            $scope.data = response.data;
                        });
                }
            }

        });
    };

    $timeout(function () {

        $scope.refresh();
    })


    $scope.all = function () {

        if ($scope.checkAll) {
            $scope.isChecked = true;
        } else {
            $scope.isChecked = false;
        }
    }


    $scope.clearSelect = function () {
        console.log("clear select");
        $scope.checkAll = false;
        if ($scope.conf.length != 0) {
            angular.forEach($scope.conf, function (ele, index) {
                $scope.conf[index] = false;
            })
        }
    }

    //combox select
    $scope.checkList = [];
    $scope.conf = [];
    $scope.select = function (action, record, index) {
        console.log("checkbox select");
        console.log($scope.conf[index]);
        if (action.target.checked) {
            $scope.checkList.push({"record": record, "index": index});
        }
    }


    //asign to me
    $scope.assignToMe = function () {
        console.log("assign to me");
        if ($scope.checkList.length != 0) {
            $scope.checkids = [];
            //Create Ids Array
            angular.forEach($scope.checkList, function (data) {
                $scope.checkids.push(data.record.workObjId);
            });
            //remove the line
            angular.forEach($scope.checkList, function (ele, index) {
                $scope.data.splice($scope.data.indexOf($scope.checkList[index].record), 1);
            });
            //uncheck the line
            angular.forEach($scope.conf, function (ele, index) {
                $scope.conf[index] = false;
            })
            //Call REST API
            $http.post("/workflow/assignToMe", $scope.checkids).then(function (res) {
                if (res.status !== 200) {
                    console.log(res);
                    return;
                }
                alert("Alerts have been assigned to you! ");
            });
        } else {
            alert("Please select alerts!");
        }
        $scope.checkids = [];
        $scope.checkList = [];
    }

    //Calculate Total Amount
    $scope.getTotal = function () {
        var totalAmt = 0.0;
        for (var key in $scope.data) {
            if (parseFloat($scope.data[key].alerts.totalAmt)) {
                totalAmt += parseFloat($scope.data[key].alerts.totalAmt);
            }
        }
        return totalAmt;
    }


});

app.controller('SuppressedAlertCtrl', function ($scope, $http, $location, $state) {
    console.log("suppressed alert ctrl");
    $scope.checkAll = false; //default false;
    $http.get("/alert/suppressedAlert")
        .then(function success(response) {
            console.log(response);
            $scope.data = response.data.result;
        }, function error() {
            console.log(error);
        });
    //combox check all/reverse
    $scope.all = function () {
        console.log("check all");

    }
    //combox select
    $scope.checkList = [];
    $scope.conf = [];
    $scope.select = function (action, record, index) {
        console.log("checkbox select");
        console.log($scope.conf[index]);
        if (action.target.checked) {
            $scope.checkList.push({"record": record, "index": index});
        }
    }
    //export
    $scope.export = function () {
        console.log("export");
    }
    //clear select
    $scope.clearSelect = function () {
        console.log("clear select");
        $scope.checkAll = false;
        if ($scope.conf.length != 0) {
            angular.forEach($scope.conf, function (ele, index) {
                $scope.conf[index] = false;
            })
        }

    }
    //remove
    $scope.remove = function () {
        console.log("remove");
        if ($scope.checkList.length != 0) {
            //$http.delete
            console.log("remove record");
            angular.forEach($scope.checkList, function (ele, index) {
                $scope.data.splice($scope.data.indexOf($scope.checkList[index].record), 1);
            });

        } else {
            alert("please choose one record at least!");
        }
        $scope.checkList = [];
        if ($scope.conf.length != 0) {
            console.log("remove");
            angular.forEach($scope.conf, function (ele, index) {
                $scope.conf[index] = false;
            })
        }

    }


});

app.controller('ClosedAlertCtrl', function ($scope, $http, $location, $state) {
    console.log("close alert ctrl");
    $http.get("/alert/closeAlert")
        .then(function success(response) {
            console.log(response);
            $scope.data = response.data.result;
        }, function error() {
            console.log(error);
        });

});

app.controller('MyAlertInfoCtrl', function ($scope, $http, $location, $state,$stateParams) {
    console.log("myalertinfoctrl");

    $scope.summaryAction = false;
    $scope.customerAction = false;
    $scope.individualAction = false;
    $scope.corporateAction = false;
    $scope.legalRepsAction = false;

    $scope.toggleBlock = function (flag) {
        if (flag === "customerSummary") {
            $scope.summaryAction = !$scope.summaryAction;
        } else if (flag === 'customerInfo') {
            $scope.customerAction = !$scope.customerAction;
        } else if (flag === 'individual') {
            $scope.individualAction = !$scope.individualAction;
        } else if (flag === 'corporate') {
            $scope.corporateAction = !$scope.corporateAction;
        } else if (flag === 'legalReps') {
            $scope.legalRepsAction = !$scope.legalRepsAction;
        }
    }

    $http.get("/alert/myAlertInfo/12345")
        .then(function (result) {
            console.log(result);
            $scope.summary = result.data.summary;
            $scope.individual = result.data.individual;
            $scope.corporate = result.data.corporate;
            $scope.legalRepresentative = result.data.legalRepresentative;
            $scope.data = result.data.tableRecords;
        })
    $scope.itemsByPage = 4;



    $scope.escalate=function(){
       let escalateWorkObjIds=[$stateParams.id];
       $http.post("/workflow/escalate",escalateWorkObjIds).then(function(res){
            
            alert("this Alert has been escalated!");
       });
    };

    $scope.close=function(){
        let escalateWorkObjIds=[$stateParams.id];
        $http.post("/workflow/close",escalateWorkObjIds).then(function(res){
            
            alert("this Alert has been closed!");
        });
    };

    $scope.returnToQc=function(){
        let escalateWorkObjIds=[$stateParams.id];
        $http.post("/workflow/returnToQc",escalateWorkObjIds).then(function(res){
            
            alert("this Alert has been return to QC!");
        });
    };

});

app.controller('AvailableAlertInfoCtrl', function ($scope, $http, $location, $state) {
    console.log("avaliable alert info ctrl");

    $scope.summaryAction = false;
    $scope.customerAction = false;
    $scope.individualAction = false;
    $scope.corporateAction = false;
    $scope.legalRepsAction = false;
    $scope.toggleBlock = function (flag) {
        if (flag === "customerSummary") {
            $scope.summaryAction = !$scope.summaryAction;
        } else if (flag === 'customerInfo') {
            $scope.customerAction = !$scope.customerAction;
        } else if (flag === 'individual') {
            $scope.individualAction = !$scope.individualAction;
        } else if (flag === 'corporate') {
            $scope.corporateAction = !$scope.corporateAction;
        } else if (flag === 'legalReps') {
            $scope.legalRepsAction = !$scope.legalRepsAction;
        }
    }
    $http.get("/alert/availableInfo/12345")
        .then(function (result) {
            console.log(result);
            $scope.summary = result.data.summary;
            $scope.individual = result.data.individual;
            $scope.corporate = result.data.corporate;
            $scope.legalRepresentative = result.data.legalRepresentative;
            $scope.data = result.data.tableRecords;
            $scope.total = result.data.total;
        });
    $scope.goBack = function () {
        $state.go("available");
    }
    $scope.itemsByPage = 4;

});

app.controller('SuppressedAlertInfoCtrl', function ($scope, $http, $location, $state) {
    console.log("avaliable alert info ctrl");

    $scope.summaryAction = false;
    $scope.customerAction = false;
    $scope.individualAction = false;
    $scope.corporateAction = false;
    $scope.legalRepsAction = false;
    $scope.toggleBlock = function (flag) {
        if (flag === "customerSummary") {
            $scope.summaryAction = !$scope.summaryAction;
        } else if (flag === 'customerInfo') {
            $scope.customerAction = !$scope.customerAction;
        } else if (flag === 'individual') {
            $scope.individualAction = !$scope.individualAction;
        } else if (flag === 'corporate') {
            $scope.corporateAction = !$scope.corporateAction;
        } else if (flag === 'legalReps') {
            $scope.legalRepsAction = !$scope.legalRepsAction;
        }
    }
    $http.get("/alert/suppressedInfo/12345")
        .then(function sucess(result) {
            console.log(result);
            $scope.summary = result.data.summary;
            $scope.individual = result.data.individual;
            $scope.corporate = result.data.corporate;
            $scope.legalRepresentative = result.data.legalRepresentative;
            $scope.data = result.data.tableRecords;
        }, function error(error) {
            console.log(error);
        });
    $scope.goBack = function () {
        $state.go("available");
    }
    $scope.itemsByPage = 4;
});

app.controller('ClosedAlertInfoCtrl', function ($scope, $http, $location, $state) {
    console.log("avaliable alert info ctrl");

    $scope.summaryAction = false;
    $scope.customerAction = false;
    $scope.individualAction = false;
    $scope.corporateAction = false;
    $scope.legalRepsAction = false;
    $scope.toggleBlock = function (flag) {
        if (flag === "customerSummary") {
            $scope.summaryAction = !$scope.summaryAction;
        } else if (flag === 'customerInfo') {
            $scope.customerAction = !$scope.customerAction;
        } else if (flag === 'individual') {
            $scope.individualAction = !$scope.individualAction;
        } else if (flag === 'corporate') {
            $scope.corporateAction = !$scope.corporateAction;
        } else if (flag === 'legalReps') {
            $scope.legalRepsAction = !$scope.legalRepsAction;
        }
    }
    $http.get("/alert/closedAlertInfo/12345")
        .then(function sucess(result) {
            console.log(result);
            $scope.summary = result.data.summary;
            $scope.individual = result.data.individual;
            $scope.corporate = result.data.corporate;
            $scope.legalRepresentative = result.data.legalRepresentative;
            $scope.data = result.data.tableRecords;
        }, function error(error) {
            console.log(error);
        });
    $scope.goBack = function () {
        $state.go("available");
    }
    $scope.itemsByPage = 4;
});

