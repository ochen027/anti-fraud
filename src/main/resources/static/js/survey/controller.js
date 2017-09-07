/**
 * survey model
 *
 */

app.controller('DoSurveyController', function ($scope, $http, $location, $state) {


    $http.get("url to get survey").then(function (res) {
        if(res.status==200){
            console.log("error");
            return;
        }

        $scope.surveyObject.questionData= res.data;
        $scope.surveyObject.survey = new Survey.Survey(surveyObject.questionData, "surveyArea");

    });

});


app.controller('SurveyManagerController', function ($scope, $http, $location, $state,$timeout) {


    $scope.createSurvey=function(){
        $state.go("surveyEditor");
    }


    $scope.getSurveyList=function(){

    }

    $scope.getSurveyObjList=function(){

    }

    $timeout(function(){
        $scope.getSurveyList();
        $scope.getSurveyObjList();
    });


});


app.controller('surveyEditorController', function ($scope, $http, $location, $state, $timeout) {


    $scope.doSaveSurvey=function () {
        var surveyID = $("body").find("#surveyID").val();
        var sendInfo = surveyEdit.getSurveyInfo();
        if (!sendInfo.title || !sendInfo.nType || !sendInfo.name) {
            alert('Survey ' + (!sendInfo.name ? ' NAME, ' : '') + (!sendInfo.nType ? ' TYPE, ' : '') + (!sendInfo.title ? ' TITLE, ' : '') + ' can not empty');
            return;
        }



        debugger;
        // if (surveyID) {
        //     sendInfo.uuid = surveyID;
        //     var ajaxOption = {
        //         url: __CERBERUS__.APIRoot + "/survey/UpdateSurvey",
        //         data: sendInfo,
        //         dataType: 'json',
        //         method: "post",
        //         success: function (res) {
        //             console.log(res.msg);
        //             $("#saveSurveyDialog").modal("hide");
        //             $("#messageSaveOrUpdateDialog").find(".message").html("Survey is updated");
        //             $("#messageSaveOrUpdateDialog").modal("show");
        //         },
        //         error: function (err) {
        //             console.log(err);
        //         }
        //     };
        //     $.ajaxEx(ajaxOption);
        // } else {
        //     var ajaxOption = {
        //         url: __CERBERUS__.APIRoot + "/survey/saveSurvey",
        //         data: sendInfo,
        //         dataType: 'json',
        //         method: "post",
        //         success: function (res) {
        //             $("body").find("#surveyID").val(res.data.uuid);
        //             $("#saveSurveyDialog").modal("hide");
        //             $("#messageSaveOrUpdateDialog").find(".message").html("Survey is saved");
        //             $("#messageSaveOrUpdateDialog").modal("show");
        //         },
        //         error: function (err) {
        //             console.log(err);
        //         }
        //     }
        //     $.ajaxEx(ajaxOption);
        // } ;

    }


    $scope.cancelSurvey =function() {
        $state.go("surveyManager");
    }


    $timeout(function () {

        window.surveyEdit = $(".contain").surveyEdit();

        if ($("body").find("#surveyID").val() != '')
            $('#surveyName').attr("disabled", true);

        $('[role=tablist]').on('dblclick', '[href*=#page]', function (e) {
            $('#editPageDialog').data('page-name', $(this).attr('href'));
            $('#editPageName').val($(this).html());
            $('#editPageDialog').modal("show");
        });

        $('#btn-change-page-name').on('click', function (e) {
            const editName = $('#editPageName').val();
            if (editName === '') {
                alert('Page Name can not empty!');
            } else {
                var editPageTab = $('[role=tablist] > a[href=' + $('#editPageDialog').data('page-name') + ']');
                editPageTab.html(editName);
                $('#editPageDialog').modal("hide");
            }
        });


    })

});
