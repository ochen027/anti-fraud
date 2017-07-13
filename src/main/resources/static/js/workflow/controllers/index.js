/***
 *
 */
app.controller('IndexWorkflowCtrl', function ($scope, $http, $location, $cookieStore, $state, $timeout) {


    $scope.workflows = [];
    $scope.workflowsDisplay=[];

    $scope.createWorkflow = function () {
        $state.go("EditWorkflow");
    };

    $scope.delete=function(row){
        $http.post("/workflow/delete",row).then(function(res){
            refreshPageData();
        });
    }

    $scope.editWorkflow=function(row){
        $state.go("EditWorkflow",{flowId:row.flowId});
    }

    var refreshPageData=function (){

        $http.get("/workflow/getAllWorkflow").then(function (res) {
            if (res.status !== 200) {
                console.log("get workflow data error");
                return;
            }

            $scope.workflows=res.data;
            $scope.workflowsDisplay=res.data.slice();
        });
    }

    $timeout(function () {

        refreshPageData();

    });

});
