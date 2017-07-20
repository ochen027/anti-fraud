

app.controller('TransBatchCtrl', function ($scope, $http, $location, $state,$timeout) {

    $scope.transactions=[];
    $scope.transactionsDisplay=[];
    $timeout(function(){
        update();
    });

    function update(){
        $http.get("/transaction/listAllTrans").then(function(res){
            $scope.transactions=res.data;
            $scope.transactionsDisplay=res.data.slice();
        });
    }

    $scope.refresh=update();
    $scope.deleteAll=function(){
        $http.get("/transaction/truncateTrans").then(function(res){
           if(res.status===200){
               alert("transactions table has been delete");
               update();
           }
        });
    };

    $scope.import=function(){
        $http.get("/transaction/importTrans").then(function(res){
            if(res.status===200){
                alert("import success!");
                update();
            }
        });
    };

});