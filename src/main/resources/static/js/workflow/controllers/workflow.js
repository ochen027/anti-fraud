


app.controller('EditWorkflowCtrl', function ($scope, $http, $location, $cookies, $cookieStore, $state, $timeout, $loginService,draw2dService) {


    window.targetProperty = {};
    window.project = {};


    $timeout(function () {
        var initClick = function () {
            saveProjectClick();
            btnCancelClick();
            btnStartClick();
            btnEndClick();
            btnProcessQueueClick();
            btnProcessPointClick();
            btnDeleteClick();
            savePropertyClick();
            btnSaveProjectClick();
        }


        /**
         * initial create and edit
         */
        var initial = function () {
            Workflow.init("canvas", targetProperty);
            window.flowId = $("#flowUID").val();
            initialEdit(flowId);
            initialRole();
        };


        /**
         * setting role list
         */
        var initialRole = function () {
            $("#processStartRole").append("<option></option>");

            $http.get("/roles/listAll").then(function (res) {
                if (res.status != 200) {
                    console.log("get role list error");
                }

                res.data.forEach(function(item){
                    $("#processStartRole").append("<option value='"+item.id+"'>"+item.roleName+"</option>");
                });

            });

        }


        var initialEdit = function (flowId) {

            if (flowId != undefined && flowId.length > 0) {
                var sendData = {
                    "flowId": flowId
                }

            }
        }


        // var setWorkflowHeader = function () {
        //     $("#projectName").html(project.name.replace(/</g, '&lt;'));
        // }

        var saveProjectClick = function () {

            $("#btnSaveProject").click(function () {
                setProjectValueToDialog();
                $("#projectDialog").modal("show");
            });

        }


        var setProjectValueToDialog = function () {
            $("#projectID").val(project.name);
            $("#type").val(project.type);
            $("#description").html(project.description);
        }

        var btnDeleteClick = function () {

            $("#btnDelete").click(function () {
                Workflow.deleteSelectedFigure();
            });

        }


        var btnCancelClick = function () {

            $("#btnCancel").click(function () {
                location.href = "";
            });

        };


        var btnStartClick = function () {

            $("#start-button").click(function () {
                window.targetProperty = new Object();
                targetProperty.title = "Start Flow.";
                targetProperty.type = "startFlow";
                openCreateDialog(targetProperty);
            });
        }

//click end button
        var btnEndClick = function () {

            $("#end-button").click(function () {
                window.targetProperty = new Object();
                targetProperty.title = "End Flow.";
                targetProperty.type = "endFlow";
                openCreateDialog(targetProperty);
            });

        }

//click process button
        var btnProcessPointClick = function () {

            $("#process-point-button").click(function () {
                window.targetProperty = new Object();
                targetProperty.title = "Process Flow.";
                targetProperty.type = "processFLow";
                openCreateDialog(targetProperty);
            });
        }

//click processQueue button
        var btnProcessQueueClick = function () {
            $("#process-queue-button").click(function () {
                window.targetProperty = new Object();
                targetProperty.title = "Process Queue.";
                targetProperty.type = "processQueue";
                openCreateDialog(targetProperty);
            });
        }

        window.targetPropertyDialogInit = function () {
            $("#propertyDialogLabel").html(targetProperty.title);
            $("#propertyName").val(targetProperty.name);
            $("#propertyID").val(targetProperty.customerID);
            $("#processStartRole").val(targetProperty.rGuid);
        }

        var openCreateDialog = function (targetProperty) {
            targetProperty.name = "";
            targetProperty.customerID = "";
            targetProperty.editDialog = openEditDialog;
            targetPropertyDialogInit();
            $("#propertyDialog").modal("show");
        }


//save property
        var savePropertyClick = function () {

            $("#btnSaveProperty").click(function () {
                $("#propertyDialog").modal("hide");
                targetProperty.createConnection = createConnection;
                setTargetPropertyDialog();
                Workflow[targetProperty.type]($.extend(true, {}, targetProperty));
            });
        }

        var setTargetPropertyDialog = function () {
            targetProperty.name = $("#propertyName").val();
            targetProperty.customerID = $("#propertyID").val();
            targetProperty.rGuid = $("#processStartRole").val();
        }

        var createConnection = function (sPort, tPort) {
            targetProperty.type = "connection";
            targetProperty.title = "Connection";
            targetProperty.name = "";
            targetProperty.customerID = "";
            targetProperty.id = undefined;
            targetProperty.connection = {};
            targetProperty.connection.sourcePort = sPort;
            targetProperty.connection.targetPort = tPort;
            targetPropertyDialogInit();
            $("#propertyDialog").modal("show");
        }


        /**
         * click save project
         */
        var btnSaveProjectClick = function () {
            $("#btnSaveWorkflow").click(function () {
                debugger;
                var result = Workflow.writeWorkflow();
                var model = draw2dService.draw2dToModel(result);
                project.chartJson = JSON.stringify(result);
                project.flowEvents = model.flowEvents;
                project.flowPoints = model.flowPoints;
                project.name=$('#projectID').val();
                project.type=$('#type').val();
                project.description=$('#description').val();

                $http.post("/workflow/saveOrUpdate",project).then(function(res){
                    if(res.data!==200){
                        console.log("save workflow error!");
                        return;
                    }

                    debugger;

                });

            });

        }


        // var setProjectValue = function () {
        //     project.name = $("#projectID").val().replace("<", "&lt;");
        //     project.type = $("#type").val().replace("<", "&lt;");
        //     project.description = $("#description").val().replace("<", "&lt;");
        // }


        var openEditDialog = function (setting) {
            window.targetProperty = new Object();
            $("#propertyDialog").modal("show");

            if (setting.cssClass == "WorkflowShape_EditWithPopupLabel") {
                targetProperty.id = setting.id;
                targetProperty.customerID = setting.text.slice(setting.text.lastIndexOf("(") + 1, setting.text.lastIndexOf(")"));
                targetProperty.name = setting.text.slice(0, setting.text.lastIndexOf("("));
                targetProperty.type = "connection";
                targetProperty.title = "Connection";
                targetProperty.label = setting;
            } else {
                targetProperty.id = setting.id;
                targetProperty.customerID = setting.userData.customerID;
                targetProperty.name = setting.userData.name;
                targetProperty.type = setting.userData.type;
                targetProperty.title = setting.userData.title;
                targetProperty.rGuid = setting.userData.rGuid;
            }
            targetPropertyDialogInit();
        }

        initial();
        initClick();
    });

});