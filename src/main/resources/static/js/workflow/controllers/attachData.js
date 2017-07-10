app.controller('attachDataController', ['$scope', '$http', function($scope, $http) {

    $scope.pageType = "view";
    $scope.errorMsg = {};
    /**
     * initial project
     */
    $scope.initial = function(UUID) {
        $scope.UUID = UUID;
        $http.get("/rest/getWorkflow/" + UUID).success(function(data) {
            if (data != undefined) {
                $scope.project = data;
                if (data.attachment == undefined) {
                    $scope.codeAreaObject = $("#attachment").codeArea("");
                    $scope.codeAreaObject.edit();
                    $scope.pageType = "edit";
                } else {
                    $scope.codeAreaObject = $("#attachment").codeArea(data.attachment);
                    $scope.pageType = "view";
                }

            } else {
                console.log("data is undefined!");
            }
        }).error(function(data) {
            console.log(data);
        });


        /**
         * save
         */
        $scope.save = function() {

            $scope.codeAreaObject.save();
            var data = $scope.codeAreaObject.data;

            try {
                var JSONData = JSON.parse(data);
                if (Array.isArray(JSONData)) {
                    //doing save
                    $scope.project.attachment = data;
                    $scope.pageType = "view";

                    $http.post("/rest/saveOrUpdateWorkflow", $scope.project).success(function(data) {

                        if (data != undefined) {
                            $scope.errorMsg.title = "Update Success";
                            $scope.errorMsg.content = "update attachment object success!";
                            $("#errorDialog").modal("show");
                        }
                    }).error(function(data) {
                        console.log(data);
                    });
                } else {
                    $scope.codeAreaObject.edit();
                    $scope.errorMsg.title = "Convert Error";
                    $scope.errorMsg.content = "The input data is json but not json array,Please check it again!";
                    $("#errorDialog").modal("show");
                }
            } catch (e) {
                $scope.codeAreaObject.edit();
                $scope.errorMsg.title = "Convert Error";
                $scope.errorMsg.content = "Convert data to json error,Please check it again!";
                $("#errorDialog").modal("show");
            }
        }


        /**
         *  to edit
         */
        $scope.edit = function() {
            $scope.codeAreaObject.edit();
            $scope.pageType = "edit";
        }


        /**
         * to next page
         */
        $scope.next = function() {

        }


        /*
        $scope.format=function(){
            
            var data = $scope.project.attachment;
            try{
                var JSONData = JSON.parse(data);
                $scope.project.attachment= JSON.stringify(JSONData,null,4);

            }catch(e)
            {
                $scope.errorMsg.title = "Convert Error";
                $scope.errorMsg.content = "Convert data to json error,Please check it again!";
                $("#errorDialog").modal("show");
            }
        }
*/

    }


}]);
