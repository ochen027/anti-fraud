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
    $scope.alertSearch = {};
    $scope.data = [];
    console.log("Close Alert Ctrl");
    $scope.searchClosedAlert = function () {
        $http.post("/workflow/getClosedAlerts", $scope.alertSearch)
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
});

app.controller('MyAlertInfoCtrl', function ($scope, $http, $location, $state, $stateParams, $timeout, Upload) {
    $scope.alerts = {};
    $scope.customer = {};
    $scope.dataFiles = [];
    $scope.itemsByPage = 7;
    $scope.SuspiciousType = "Suspicious 1";
    $scope.commentList = [];
    $scope.comments = {};
    $scope.customerType = 'individual';

    let showState = {
        available:{
            document:false,
            comments:false,
            returnButton:false,
            escalate:false,
            close:false,
            suppress:false,
        },
        "L1 review":{
            document:true,
            comments:true,
            returnButton:false,
            escalate:true,
            close:false,
            suppress:false,
        },
        "l2 review":{
            document:true,
            comments:true,
            returnButton:false,
            escalate:true,
            close:true,
            suppress:true
        },"QC review":{
            document:true,
            comments:true,
            returnButton:false,
            escalate:true,
            close:true,
            suppress:true
        },"MLRO review":{
            document:true,
            comments:true,
            returnButton:false,
            escalate:true,
            close:true,
            suppress:true
        }
    };

    $scope.currentState={};


    $scope.selectSuspiciousType = function (type) {
        $scope.SuspiciousType = type;
    }

    $scope.addComments = function () {
        $scope.comments.objId = $stateParams.id;
        $http.post("/comments/create", $scope.comments).then(function (res) {
            alert("comments add success!");
            $scope.refreshComments();
            $scope.comments = {};
        })
    }

    $scope.refreshComments = function () {
        $http.get("/comments/getByObjId/" + $stateParams.id).then(function (res) {
            $scope.commentList = res.data;
        });
    }

    $scope.refresh = function () {
        $scope.refreshFile();
        getAlert();
        $scope.refreshComments();
    }

    //upload file part
    $scope.uploadFiles = function (files) {
        if (files && files.length) {
            Upload.upload({
                url: '/documents/upload',
                data: {file: files[0], workObjId: $stateParams.id}
            }).then(function (res) {
                alert("file upload success!");
                $scope.refresh();
            });
        }
    }

    $scope.fileDownload = function (fileName, filePath) {
        window.location.href = "/documents/download/" + fileName + "/" + filePath;
    }

    $scope.refreshFile = function () {
        $http.get("/documents/getByAlertId/" + $stateParams.id).then(function (res) {
            if (res.status !== 200) {
                console.log(res);
                return;
            }
            $scope.dataFiles = res.data;
        });
    }



    var getAlert = function () {
        $http.post("/workflow/getWorkObjById", $stateParams.id).then(function (res) {

            if (res.status != 200) {
                console.log(res);
                return;
            }
            $scope.alerts = res.data.alerts;
            $scope.transList=res.data.alerts.transList;
            $scope.transListDisplay=res.data.alerts.transList.slice();
            $scope.workObj = res.data;
            if($scope.workObj.currentPoint.name.indexOf("available")>=0){
                $scope.currentState=showState["available"];
            }else{
                $scope.currentState=showState[$scope.workObj.currentPoint.name];
            }
            getCustomer(res.data.alerts.customerId);
        });
    }
    $scope.goBack = function () {
        if($scope.workObj.currentPoint.name.indexOf("available")>=0){
            $state.go("available");
        }else{
            $state.go("myAlert");
        }
    }

    var getCustomer = function (customerId) {
        $http.post("/customer/findByCustId", {"customerId": customerId}).then(function (res) {
            if (res.status != 200) {
                console.log(res);
                return;
            }
            $scope.customer = res.data;
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

