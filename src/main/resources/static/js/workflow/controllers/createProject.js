app.registerCtrl('createProjectController', ['$scope', '$http', function($scope, $http) {

    var baseRoute = clientConfig.workflowBasePath;
    $scope.project = {};

    //refresh for the structure
    $scope.refresh = function() {
        $http.get(baseRoute + "/rest/getWorkflows").success(function(data) {
            $scope.workflows = data;
        }).error(function(data) {
            console.log(data);
        });
    };

    $scope.refresh();

    $scope.select = function(UUID) {
        $scope.project.UUID = UUID;
    }

    $scope.saveProject = function() {

        //  $http.post("",$scope.project).success().error
    }

    // //edit
    // $scope.edit = function (UUID) {
    //     location.href = "/configuration/attachData/"+UUID;
    // }

}]);
