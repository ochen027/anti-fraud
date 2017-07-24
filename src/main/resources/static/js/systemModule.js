app.controller('ImportDataCtrl', function ($scope, $http, $location, $state, $timeout, Upload) {


    $scope.uploadFiles = function (files) {
        debugger;
        if (files && files.length) {
            Upload.upload({
                url: '/customer/upload',
                data: {file: files,username:"test1"}
            }).then(function (res) {
                console.log('Success ' + res.config.data.file.name + 'uploaded. Response: ' + res.data);
            });
        }
    }


});

app.controller('UserManagementCtrl', function ($scope, $http, $location, $state, $timeout) {

});
