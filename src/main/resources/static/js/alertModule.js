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
    // $scope.getMyAlertTotal = function () {
    //     var totalAmt = 0.0;
    //     for (var key in $scope.myAlertData) {
    //         if (parseFloat($scope.myAlertData[key].alerts.totalAmt)) {
    //             totalAmt += parseFloat($scope.myAlertData[key].alerts.totalAmt);
    //         }
    //     }
    //     return totalAmt;
    // }

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
                if ($scope.workflow.flowPoints[i].print.indexOf($stateParams.id) >= 0) {
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
    $scope.alertSearch = {};
    $scope.data = [];
    $scope.checkAll = false; //default false;
    $scope.checkids = [];
    $scope.checkList = [];
    $scope.conf = [];
    $scope.all = function () {

        if ($scope.checkAll) {
            $scope.isChecked = true;
        } else {
            $scope.isChecked = false;
        }
    }


    //combox select

    $scope.select = function (action, record, index) {
        console.log("checkbox select");
        console.log($scope.conf[index]);
        if (action.target.checked) {
            $scope.checkList.push({"record": record, "index": index});
        }
    }


    $scope.reset = function () {
        $scope.data = [];
        $scope.alertSearch = {};
        $scope.suppressData=[];
        $scope.suppressDataDisplay=[];
    }

    //export
    $scope.export = function () {
        window.open("/suppress/export",$scope.alertSearch);
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

    }

    $scope.search = function () {
        $http.post("/suppress/searchSuppress", $scope.alertSearch)
            .then(function success(response) {
                console.log(response);
                $scope.suppressData = response.data;
                $scope.suppressDataDisplay=response.data.slice();
            }, function error() {
                console.log(error);
            });
    }


});

app.controller('ClosedAlertCtrl', function ($scope, $http, $location, $state) {
    $scope.alertSearch = {};
    $scope.data = [];

    $scope.searchClosedAlert = function () {
        $http.post("/alerts/searchClosedAlert", $scope.alertSearch)
            .then(function success(response) {
                console.log(response);
                $scope.data = response.data;
            }, function error() {
                console.log(error);
            });
    }

    $scope.reset = function () {
        $scope.data = [];
        $scope.alertSearch = {};
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
    //export
    $scope.export = function () {
        window.open("/alerts/exportClose",$scope.data);
        console.log("Closed Alert Exported");
    }
});

app.controller('MyAlertInfoCtrl', function ($scope, $http, $location, $state, $stateParams, $timeout, Upload) {
    $scope.alerts = {};
    $scope.customerBase = {};
    $scope.dataFiles = [];
    $scope.itemsByPage = 7;
    $scope.SuspiciousType = "Suspicious 1";
    $scope.commentList = [];
    $scope.comments = {};
    $scope.customerType = 'individual';
    $scope.header = '';

    let showState = {
        available: {
            sar: false,
            document: false,
            comments: false,
            returnButton: false,
            escalate: false,
            close: false,
            suppress: false,
        },
        "L1 review": {
            sar: false,
            document: true,
            comments: true,
            returnButton: false,
            escalate: true,
            close: false,
            suppress: false,
        },
        "l2 review": {
            sar: false,
            document: true,
            comments: true,
            returnButton: false,
            escalate: true,
            close: true,
            suppress: true
        }, "QC review": {
            sar: false,
            document: true,
            comments: true,
            returnButton: false,
            escalate: true,
            close: true,
            suppress: true
        }, "MLRO review": {
            sar: true,
            document: true,
            comments: true,
            returnButton: true,
            escalate: false,
            close: false,
            suppress: true
        }
    };

    $scope.currentState = {};

    $scope.suppress = {
        customerBase: {},
        scenario: {},
        alerts: {},
        permanent: true,
        endDate: new Date(),
    };

    $scope.saveSuppress = function () {

        var otherParams = {
            customerBase: $scope.customerBase,
            scenario: {
                id: $scope.alerts.scenarioId
            },
            alerts: $scope.alerts
        };
        angular.extend($scope.suppress, otherParams);

        $http.post("/suppress/addSuppress", $scope.suppress).then(function () {
            alert("suppress saved!");
            $("#suppressModal").modal("hide");
        });
    }

    $scope.selectSuspiciousType = function (type) {
        $scope.workObj.suspiciousType = type;

        $http.post("/workflow/UpdateSuspiciousType", {workObjId: $stateParams.id, "suspiciousType": type});

    }

    $scope.addComments = function () {
        return new Promise(function (resolve, reject) {
            $scope.comments.objId = $stateParams.id;
            $http.post("/comments/create", $scope.comments).then(function (res) {
                if (res.status != 200) {
                    console.log(res);
                    reject();
                }

                alert("comments add success!");
                $scope.refreshComments();
                $scope.comments = {};
                resolve();
            })
        });
    }

    $scope.refreshComments = function () {
        return new Promise(function (resolve, reject) {
            $http.get("/comments/getByObjId/" + $stateParams.id).then(function (res) {
                if (res.status != 200) {
                    console.log(res);
                    reject();
                }
                ;

                $scope.commentList = res.data;
                resolve();
            });
        });
    }

    $scope.refresh = function () {
        $scope.refreshFile().then(function () {
            return $scope.getAlert();
        }).then(function () {
            return $scope.refreshComments();
        })
    }

    //upload file part
    $scope.uploadFiles = function (files) {
        return new Promise(function (resolve, reject) {
            if (files && files.length) {
                Upload.upload({
                    url: '/documents/upload',
                    data: {file: files[0], workObjId: $stateParams.id}
                }).then(function (res) {
                    alert("file upload success!");
                    $scope.refresh();
                    resolve();
                });
            }
        });
    }

    $scope.fileDownload = function (fileName, filePath) {
        window.location.href = "/documents/download/" + fileName + "/" + filePath;
    }

    $scope.suppressDialog = function () {

        $("#suppressModal").modal("show");

    }


    $scope.refreshFile = function () {
        return new Promise(function (resolve, reject) {
            $http.get("/documents/getByAlertId/" + $stateParams.id).then(function (res) {
                if (res.status !== 200) {
                    console.log(res);
                    reject();
                }
                $scope.dataFiles = res.data;
                resolve();
            });
        });
    }


    $scope.getAlert = function () {
        return new Promise(function (resolve, reject) {
            $http.post("/workflow/getWorkObjById", $stateParams.id).then(function (res) {
                if (res.status != 200) {
                    console.log(res);
                    reject();
                }
                $scope.alerts = res.data.alerts;
                $scope.transList = res.data.alerts.transList;
                $scope.transListDisplay = res.data.alerts.transList.slice();
                $scope.workObj = res.data;
                if ($scope.workObj.currentPoint.name.indexOf("available") >= 0) {
                    $scope.currentState = showState["available"];
                    $scope.header = 'Available Alerts Info:';
                } else {
                    $scope.currentState = showState[$scope.workObj.currentPoint.name];
                    $scope.header = 'My Alerts Info:';
                }
                getCustomer(res.data.alerts.customerId);
                resolve();
            });
        });
    }
    // $scope.goBack = function () {
    //     if ($scope.workObj.currentPoint.name.indexOf("available") >= 0) {
    //         $state.go("available");
    //     } else {
    //         $state.go("myAlert");
    //     }
    // }

    var getCustomer = function (customerId) {
        $http.post("/customer/findByCustId", {"customerId": customerId}).then(function (res) {
            if (res.status != 200) {
                console.log(res);
                return;
            }
            $scope.customer = res.data;
            $scope.customerBase = res.data;
            if (res.data.individual === null) {
                $scope.customerType = 'corporate';
            }
        });
    }

    $timeout(function () {
        $scope.refresh();
    });


    $scope.showUpload = function () {
        let result = false;


        return result;
    }

    $scope.escalate = function () {
        let escalateWorkObjIds = [$stateParams.id];
        $http.post("/workflow/escalate", escalateWorkObjIds).then(function (res) {

            alert("this Alert has been escalated!");
        });
    };
    $scope.sar = function () {
        let escalateWorkObjIds = [$stateParams.id];
        $http.post("/workflow/sar", escalateWorkObjIds).then(function (res) {

            alert("this Alert has been reported to SAR!");
        });
    };

    $scope.close = function () {
        let escalateWorkObjIds = [$stateParams.id];
        $http.post("/workflow/close", escalateWorkObjIds).then(function (res) {

            alert("this Alert has been closed!");
        });
    };

    $scope.returnToQc = function () {
        let escalateWorkObjIds = [$stateParams.id];
        $http.post("/workflow/returnToQc", escalateWorkObjIds).then(function (res) {

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

    // 12345???
    // $http.get("/alert/closedAlertInfo/12345")
    //     .then(function sucess(result) {
    //         console.log(result);
    //         $scope.summary = result.data.summary;
    //         $scope.individual = result.data.individual;
    //         $scope.corporate = result.data.corporate;
    //         $scope.legalRepresentative = result.data.legalRepresentative;
    //         $scope.data = result.data.tableRecords;
    //     }, function error(error) {
    //         console.log(error);
    //     });
    $scope.goBack = function () {
        $state.go("available");
    }
    $scope.itemsByPage = 4;
});


app.controller('CreateAlertCtrl', function ($scope, $http, $location, $state, $timeout) {
    $scope.transSearch = {};
    $scope.transData = [];
    $scope.checkAll = false;
    $scope.checkTrans = [];

    $scope.search = function () {
        $http.post("/transaction/search", $scope.transSearch)
            .then(function success(response) {
                console.log(response);
                $scope.transData = response.data;
            }, function error() {
                console.log(error);
            });
    }


    $scope.resetSearch = function () {
        $scope.transData = [];
        $scope.transSearch = {};
    }

    $scope.getSearchTotal = function () {
        var totalAmt = 0.0;
        for (var key in $scope.transData) {
            if (parseFloat($scope.transData[key].transBaseAmt)) {
                totalAmt += parseFloat($scope.transData[key].transBaseAmt);
            }
        }
        return totalAmt;
    }

    $scope.selectall = function () {
        if ($scope.checkAll) {
            $scope.isChecked = true;
        } else {
            $scope.isChecked = false;
        }
    }


    $scope.clearSelect = function () {
        $scope.checkAll = false;
        if ($scope.conf.length != 0) {
            angular.forEach($scope.conf, function (ele, index) {
                $scope.conf[index] = false;
            })
        }
    }

    //Select trans into List
    $scope.checkList = [];
    $scope.conf = [];
    $scope.select = function (action, record, index) {
        console.log("checkbox select");
        console.log($scope.conf[index]);
        if (action.target.checked) {
            $scope.checkList.push({"record": record, "index": index});
        }
    }

    //Select trans into Remove List
    $scope.checkRemoveList = [];
    $scope.confRemove = [];
    $scope.selectRemove = function (action, record, index) {
        console.log($scope.conf[index]);
        if (action.target.checked) {
            $scope.checkRemoveList.push({"record": record, "index": index});
        }
    }


    $scope.removeFromList = function () {
        if ($scope.checkRemoveList.length != 0) {
            //remove the line
            angular.forEach($scope.checkRemoveList, function (ele, index) {
                $scope.checkTrans.splice($scope.checkTrans.indexOf($scope.checkRemoveList[index].record), 1);
            });
            //uncheck the line
            angular.forEach($scope.confRemove, function (ele, index) {
                $scope.confRemove[index] = false;
            })
        }
        $scope.checkRemoveList = [];
    }

    $scope.addToList = function () {
        if ($scope.checkList.length != 0) {
            //add selected transaction into target array
            angular.forEach($scope.checkList, function (transData) {
                console.log($scope.checkTrans);
                $scope.checkTrans.push(transData.record);
            });
            //remove the line
            angular.forEach($scope.checkList, function (ele, index) {
                $scope.transData.splice($scope.transData.indexOf($scope.checkList[index].record), 1);
            });
            //uncheck the line
            angular.forEach($scope.conf, function (ele, index) {
                $scope.conf[index] = false;
            })

        } else {
            alert("Please select alerts!");
        }
        $scope.checkList = [];
    }

    $scope.getListTotal = function () {
        var totalAmt = 0.0;
        for (var key in $scope.checkTrans) {
            if (parseFloat($scope.checkTrans[key].transBaseAmt)) {
                totalAmt += parseFloat($scope.checkTrans[key].transBaseAmt);
            }
        }
        return totalAmt;
    }

    $scope.scenarioData = [];
    $timeout(function () {
        $http.get("/rules/listAll").then(function sucess(result) {
            console.log(result);
            $scope.scenarioData = result.data
        }, function error(error) {
            console.log(error);
        });
    });

    $scope.scenario = {};
    $scope.getScenarioDetail = function (scenario) {
        $scope.scenario = scenario;
    }

    $scope.create = function () {
        if ($scope.checkTrans.length != 0) {
            var keyCustomer = $scope.checkTrans[0].customerId;
            for (var key in $scope.checkTrans) {
                if (keyCustomer != $scope.checkTrans[key].customerId) {
                    $scope.checkTrans = [];
                    $scope.transSearch = {};
                    $scope.transData = [];
                    $scope.checkAll = false;
                    $scope.checkTrans = [];
                    $scope.scenario = {};
                    alert("Please select transaction with same customer!");
                    return;
                }
            }
        } else {
            alert("Please select at least one transaction to create alert by manually!");
            return;
        }

        $scope.alertCreation = {transList: $scope.checkTrans, scenario: $scope.scenario};
        $http.post("/alerts/createAlert", $scope.alertCreation).then(function success(response) {
            console.log(response);
            $scope.checkTrans = [];
            $scope.transSearch = {};
            $scope.transData = [];
            $scope.checkAll = false;
            $scope.checkTrans = [];
            $scope.scenario = {};
            alert("Create Successfully!");
        }, function error() {
            console.log(error);
            alert("Create Failure, " + error);
        });
    }

});

