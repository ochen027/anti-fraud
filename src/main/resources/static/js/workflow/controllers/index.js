/***
 *
 */
app.controller('IndexWorkflowCtrl', function ($scope, $http, $location, $cookieStore, $state, $timeout) {


    $scope.workflows = [];

    $scope.createWorkflow = function () {
        $state.go("EditWorkflow");
    };


    $timeout(function () {

        //get workflows
        $http.get("/workflow/getAllWorkflow").then(function (res) {
            if (res.status !== 200) {
                console.log("get workflow data error");
                return;
            }

            $scope.workflows=res.data;


        });

    });

});
