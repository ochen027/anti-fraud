


// app.controller('MyAlertCtrl', function ($scope, $http, $location, $state, $timeout) {
//
//     $scope.myAlertData = [];
//     $scope.myAlertDataDisplay = [];
//     $timeout(function () {
//
//         $http.get("/alerts/getAllAlerts").then(function (res) {
//             if (res.code !== 200) {
//                 console.log(res);
//                 return;
//             }
//
//             $scope.myAlertData = res.data;
//             $scope.myAlertDataDisplay = res.data.splice();
//
//
//         });
//     })
//
// })

app.controller('MyAlertCtrl', function ($scope, $http, $location, $state) {
    console.log("/alert/myalert");
    $http.get("/alert/myalert")
        .then(function(response){
            console.log(response);
            $scope.myAlertData = response.data.result;


        });


    $scope.itemsByPage = 10;

});


app.controller('AvailableAlertCtrl', function ($scope, $http, $location, $state) {
    console.log("available alert ctrl");
    $scope.checkAll=false; //default false;

    $scope.data = [];
    $scope.dataDisplay=[];

    $http.get("/workflow/getAvailableAlerts")
        .then(function(response){
            $scope.data = response.data;
            $scope.total = response.data.total;
        });

    $scope.itemsByPage = 10;
    //combox check all/reverse
    $scope.all = function(){
        console.log("check all");
        if($scope.checkAll){
            $scope.isChecked = true;
        }else{
            $scope.isChecked = false;
        }
    }

    //clear select
    $scope.clearSelect = function(){
        console.log("clear select");
        $scope.checkAll=false;
        if($scope.conf.length !=0 ){
            angular.forEach($scope.conf,function(ele,index){
                $scope.conf[index]=false;
            })
        }
    }
    //asign to
    $scope.assignTo = function(){
        console.log("assign to somebody");
    }

    //Calculate Total Amount
    $scope.getTotal = function() {
        var totalAmt = 0.0;
        for ( var key in $scope.data) {
            if (parseFloat($scope.data[key].alerts.totalAmt)) {
                totalAmt += parseFloat($scope.data[key].alerts.totalAmt);
            }
        }
        return totalAmt;
    }


});

app.controller('SuppressedAlertCtrl', function ($scope, $http, $location, $state) {
    console.log("suppressed alert ctrl");
    $scope.checkAll=false; //default false;
    $http.get("/alert/suppressedAlert")
        .then(function success(response){
            console.log(response);
            $scope.data = response.data.result;
        }, function error(){
            console.log(error);
        });
    //combox check all/reverse
    $scope.all = function(){
        console.log("check all");

    }
    //combox select
    $scope.checkList = [];
    $scope.conf=[];
    $scope.select = function(action,record,index){
        console.log("checkbox select");
        console.log($scope.conf[index]);
        if(action.target.checked){
            $scope.checkList.push({"record":record,"index":index});
        }
    }
    //export
    $scope.export = function(){
        console.log("export");
    }
    //clear select
    $scope.clearSelect = function(){
        console.log("clear select");
        $scope.checkAll=false;
        if($scope.conf.length !=0 ){
            angular.forEach($scope.conf,function(ele,index){
                $scope.conf[index]=false;
            })
        }

    }
    //remove
    $scope.remove = function(){
        console.log("remove");
        if($scope.checkList.length != 0){
            //$http.delete
            console.log("remove record");
            angular.forEach($scope.checkList,function(ele,index){
                $scope.data.splice($scope.data.indexOf($scope.checkList[index].record), 1);
            });

        }else{
            alert("please choose one record at least!");
        }
        $scope.checkList=[];
        if($scope.conf.length !=0 ){
            console.log("remove");
            angular.forEach($scope.conf,function(ele,index){
                $scope.conf[index]=false;
            })
        }

    }


});

app.controller('ClosedAlertCtrl', function ($scope, $http, $location, $state) {
    console.log("close alert ctrl");
    $http.get("/alert/closeAlert")
        .then(function success(response){
            console.log(response);
            $scope.data = response.data.result;
        }, function error(){
            console.log(error);
        });

});

app.controller('MyAlertInfoCtrl', function ($scope, $http, $location, $state) {
    console.log("myalertinfoctrl");

    $scope.summaryAction = false;
    $scope.customerAction = false;
    $scope.individualAction = false;
    $scope.corporateAction = false;
    $scope.legalRepsAction = false;
    $scope.toggleBlock = function(flag){
        if(flag==="customerSummary"){
            $scope.summaryAction = !$scope.summaryAction;
        }else if(flag === 'customerInfo'){
            $scope.customerAction = !$scope.customerAction;
        }else if(flag === 'individual'){
            $scope.individualAction = !$scope.individualAction;
        }else if(flag === 'corporate'){
            $scope.corporateAction = !$scope.corporateAction;
        }else if(flag === 'legalReps'){
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

});

app.controller('AvailableAlertInfoCtrl', function ($scope, $http, $location, $state) {
    console.log("avaliable alert info ctrl");

    $scope.summaryAction = false;
    $scope.customerAction = false;
    $scope.individualAction = false;
    $scope.corporateAction = false;
    $scope.legalRepsAction = false;
    $scope.toggleBlock = function(flag){
        if(flag==="customerSummary"){
            $scope.summaryAction = !$scope.summaryAction;
        }else if(flag === 'customerInfo'){
            $scope.customerAction = !$scope.customerAction;
        }else if(flag === 'individual'){
            $scope.individualAction = !$scope.individualAction;
        }else if(flag === 'corporate'){
            $scope.corporateAction = !$scope.corporateAction;
        }else if(flag === 'legalReps'){
            $scope.legalRepsAction = !$scope.legalRepsAction;
        }
    }
    $http.get("/alert/availableInfo/12345")
        .then(function(result){
            console.log(result);
            $scope.summary = result.data.summary;
            $scope.individual = result.data.individual;
            $scope.corporate = result.data.corporate;
            $scope.legalRepresentative = result.data.legalRepresentative;
            $scope.data = result.data.tableRecords;
            $scope.total = result.data.total;
        });
    $scope.goBack = function(){
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
    $scope.toggleBlock = function(flag){
        if(flag==="customerSummary"){
            $scope.summaryAction = !$scope.summaryAction;
        }else if(flag === 'customerInfo'){
            $scope.customerAction = !$scope.customerAction;
        }else if(flag === 'individual'){
            $scope.individualAction = !$scope.individualAction;
        }else if(flag === 'corporate'){
            $scope.corporateAction = !$scope.corporateAction;
        }else if(flag === 'legalReps'){
            $scope.legalRepsAction = !$scope.legalRepsAction;
        }
    }
    $http.get("/alert/suppressedInfo/12345")
        .then(function sucess(result){
            console.log(result);
            $scope.summary = result.data.summary;
            $scope.individual = result.data.individual;
            $scope.corporate = result.data.corporate;
            $scope.legalRepresentative = result.data.legalRepresentative;
            $scope.data = result.data.tableRecords;
        },function error(error){
            console.log(error);
        });
    $scope.goBack = function(){
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
    $scope.toggleBlock = function(flag){
        if(flag==="customerSummary"){
            $scope.summaryAction = !$scope.summaryAction;
        }else if(flag === 'customerInfo'){
            $scope.customerAction = !$scope.customerAction;
        }else if(flag === 'individual'){
            $scope.individualAction = !$scope.individualAction;
        }else if(flag === 'corporate'){
            $scope.corporateAction = !$scope.corporateAction;
        }else if(flag === 'legalReps'){
            $scope.legalRepsAction = !$scope.legalRepsAction;
        }
    }
    $http.get("/alert/closedAlertInfo/12345")
        .then(function sucess(result){
            console.log(result);
            $scope.summary = result.data.summary;
            $scope.individual = result.data.individual;
            $scope.corporate = result.data.corporate;
            $scope.legalRepresentative = result.data.legalRepresentative;
            $scope.data = result.data.tableRecords;
        },function error(error){
            console.log(error);
        });
    $scope.goBack = function(){
        $state.go("available");
    }
    $scope.itemsByPage = 4;
});

