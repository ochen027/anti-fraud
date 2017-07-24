

app.controller('ImportDataCtrl', function($scope, $http, $location, $state, $timeout,Upload){


    $scope.uploadFiles = function (files) {
        debugger;
        if (files && files.length) {
            for (var i = 0; i < files.length; i++) {
                Upload.upload({ data: {file: files[i]} });
            }
            // or send them all together for HTML5 browsers:
            //Upload.upload({..., data: {file: files}, ...});
        }
    }



});

app.controller('UserManagementCtrl', function($scope, $http, $location, $state, $timeout){

});
