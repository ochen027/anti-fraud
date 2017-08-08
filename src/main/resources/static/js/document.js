/**
 * Created by whuang072 on 7/13/2017.
 */


app.controller('DocumentCtrl', function ($scope, $http,  $state, Upload, $timeout) {
    // upload later on form submit or something similar
    $scope.submit = function() {
        if ($scope.form.file.$valid && $scope.file) {
            $scope.upload($scope.file);
        }
        //multiple files
        if ($scope.form.files.$valid && $scope.files) {
            $scope.multipleUpload($scope.files);
        }
    };

    // upload on file select or drop
    $scope.upload = function (file) {
        Upload.upload({
            url: '/document/upload',
            method: 'POST',
            withCredentials: true,
            data: {file: file},
            resumeSizeUrl: '/uploaded/size/url?file=' + file.name, // uploaded file size so far on the server.
            resumeSizeResponseReader: function(data) {return data.size;}, // reads the uploaded file size from resumeSizeUrl GET response
            resumeSize: function() {return promise;} // function that returns a promise which will be resolved to the upload file size on the server.

        }).then(function (resp) {
            console.log('Success ' + resp.config.data.file.name + ' uploaded. Response: ' + resp.data);
            $scope.file = resp.data.data;

        }, function (resp) {
            console.log('Error status: ' + resp.status);
        }, function (evt) {
            console.log("process");
            var progressPercentage = parseInt(100.0 * evt.loaded / evt.total);
            console.log('progress: ' + progressPercentage + '% ' + evt.config.data.file.name);
        });
    };

    //multiple upload
    $scope.multipleUpload = function(files){
        if (files && files.length) {
            for (var i = 0; i < files.length; i++) {
                this.upload(files[i]);
            }
        }
    }
    //download
    $scope.confirm = function() {
        $http.get("/document/download")
            .then(function (resp){
                console.log(resp + "download confirm ");
                $scope.dataFiles = resp.data;
            },function(error){
                console.log(error.status);
            })
    }
    $scope.download = function(fileName){
        window.location.href="/file/download?fileName="+fileName;
         // $http.get("/file/download").then(function (res) {
         //    console.log(res);
         //
         //
         // });

    }



    //Added by Orvin Upload file into HDFS
    $scope.uploadFiles = function (files) {
        if (files && files.length) {
            Upload.upload({
                url: '/documents/upload',
                data: {file: files[0],workObjId:"201708071058455875519638759332"}
            }).then(function (res) {
                $scope.refresh();

            });
        }

    }

    $scope.refresh = function () {
        $scope.dataFiles = [];
        console.log("Begin get document list");
        $http.get("/documents/getByAlertId/201708071058455875519638759332").then(function (res) {
            if (res.status !== 200) {
                console.log(res);
                return;
            }
            $scope.dataFiles=res.data;

        });
    }


    //Added By Orvin Get Document by Alert Work Object Id 201708071058455875519638759332
    $timeout(function () {
        $scope.dataFiles = [];
        console.log("Begin get document list");
        $http.get("/documents/getByAlertId/201708071058455875519638759332").then(function (res) {
            if (res.status !== 200) {
                console.log(res);
                return;
            }
            $scope.dataFiles=res.data;

        });
    })

    //Added By Orvin Download file
    $scope.filedownload = function(fileName, filePath){
        window.location.href="/documents/download/"+fileName+"/"+filePath;
    }

});