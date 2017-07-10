app.registerCtrl("attachController", ['$scope', '$http', '$timeout', function($scope, $http, $timeout) {
    var baseRoute = clientConfig.workflowBasePath;

    $scope.workflows = [];
    $scope.resData = {
        user_id: "1",
        project_id: "1"
    };
    $scope.showFlag = true;


    $scope.objectList = ["1", "2", "3"];
    // $scope.workflows=[
    //     {flowUID:1,name:"1"},
    //     {flowUID:2,name:"2"},
    //     {flowUID:3,name:"3"}
    //
    // ]


    $http.get(baseRoute + "/getWorkflows").success(function(data) {
        console.log(data);
        $scope.workflows = data;

    }).error(function(data) {
        console.log(data);
    });


    $scope.attach = function() {
        $scope.resData.objects = $scope.objectList;
        attachWorkflow($scope.resData);
    };


    $scope.addEvent = function(event, objectid) {

        var postData = {};
        if (event == null || event == undefined) {
            return;
        }
        postData.event_id = event.uid //$("#event_"+objectid).val();
        postData.object_id = objectid;
        postData.flow_id = $scope.resData.workflow_id;
        // postData.project_id=$scope.resData.project_id;

        if (postData.event_id != undefined) {
            $http.post(baseRoute + "/addEvent", postData).success(function(data) {

                $.each($scope.objects, function(k, v) {
                    if (v.objectId == data.objectId && v.project_id == data.project_id) {
                        console.log(data);
                        $scope.objects[k] = data;
                    }
                });

            }).error(function(data) {
                console.log(data)
            });
        }
    }


    function attachWorkflow(data) {
        $http.post(baseRoute + "/attachWorkflow", data).success(function(data) {
            $scope.objects = data;
            $scope.showFlag = !$scope.showFlag;
        }).error(function(data) {
            console.log(data)
        });
    }


    $scope.reset = function() {
        $http.post(baseRoute + "/reset").success(function(data) {

            location.reload();

        }).error(function(data) {
            console.log(data)
        });

    };

}]);
