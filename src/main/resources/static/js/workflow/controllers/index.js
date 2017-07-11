/***
 *
 */
app.controller('EditWorkflowCtrl', function ($scope, $http, $location, $cookieStore, $state, $timeout) {

    $scope.createWorkflow = function () {
        $state.go("EditWorkflow");
    };

    $timeout(function () {

        //get workflows


    })

});
