$(function() {
    init();
    var surveyList = [];

    function init() {
        setSurveyData();
        $("#btn-delete-survey").on("click", function() {
            var ajaxOption = {
                url: __CERBERUS__.APIRoot + "/survey/deleteSurvey",
                data: {
                    suGuid: $("#delete-survey-id").val()
                },
                dataType: 'json',
                method: "post",
                success: function(data) {
                    if (data.code == 0) {
                        $('#surveyTable').DataTable().destroy();
                        $('#surveyResponseTable').DataTable().destroy();
                        $('#deleteModal').modal('hide');
                        setSurveyData();
                    }
                },
                error: function(err) {
                    console.log(err);
                }
            };
            $.ajaxEx(ajaxOption);
        });
    }

    function setSurveyData() {
        var dataTableOption = {
            sDom: "t",
            iDisplayLength: 10,
            bAutoWidth: false,
            responsive: true,
            bSort: true,
            bFilterOnEnter: true,
            paging: false,
            processing: false
        }
        dataTableOption.columns = [{
            "data": "name"
        }, {
            "data": "desc"
        }, {
            "data": "uuid"
        }];
        dataTableOption.aoColumnDefs = [{
            fnCreatedCell: function(nTd, sData, oData, iRow, iCol) {
                var sHtml = '<a href="javascript:void(0)" class="removeFilter" data-suGuid="' + sData + '" onclick="editSurvey(this)"><i class="fa fa-pencil"></i></a> ';
                // sHtml += '<a href="javascript:void(0)" class="removeFilter" data-suGuid="' + sData + '" onclick="openDeleteModal(this)"><i class="fa fa-trash-o"></i></a>';
                $(nTd).html(sHtml);
            },
            aTargets: [2]
        }];
        var ajaxOption = {
            url: __CERBERUS__.APIRoot + "/survey/getSurveys",
            dataType: 'json',
            method: "get",
            success: function(res) {
                if (res.data.code == 0) {
                    dataTableOption.data = res.data.data;
                    $("#surveyTable").DataTable(dataTableOption);
                    $('#deleteModal').modal("hide");
                    if (res.data) {
                        res.data.data.forEach(function(item) {
                            surveyList.push({
                                name: item.name,
                                uuid: item.uuid
                            });
                        });
                    }
                } else {
                    dataTableOption.data = [];
                    $("#surveyTable").DataTable(dataTableOption);
                }
                $.ajaxEx({
                    url: __CERBERUS__.APIRoot + "/survey/getAllSurveyObj",
                    dataType: 'json',
                    data: {
                        survey: surveyList,
                    },
                    method: 'post',
                    success: function(res) {
                        if (res.code == 0) {
                            var objList = res.data;
                            var ajaxOption = {
                                url: __CERBERUS__.APIRoot + "/vendor/getVendor",
                                dataType: 'json',
                                method: "get",
                                success: function(res) {
                                    if (res.code == 0) {
                                        $.each(objList, function(key, objItem) {
                                            var flag = true;
                                            $.each(res.data, function(key, vItem) {
                                                if(vItem.vsurvey_guid == objItem.oGuid){
                                                    flag = false;
                                                    objItem.vendorName = vItem.vvname;
                                                }
                                            });
                                            if(flag) {
                                                objItem.vendorName = "";
                                            }
                                        });
                                        setSurveyObjectData(objList);
                                    } else {
                                        setSurveyObjectData([]);
                                    }
                                },
                                error: function(err) {
                                    console.log(err);
                                }
                            };
                            $.ajaxEx(ajaxOption);
                        } else {
                            setSurveyObjectData([]);
                        }
                        surveyList = [];
                    },
                    error: function(err) {
                        console.log(err);
                    }
                })

            },
            error: function(err) {
                console.log(err);
            }
        };
        $.ajaxEx(ajaxOption);
    }

    function setSurveyObjectData(data) {
        var dataTableOption = {
            sDom: "t",
            iDisplayLength: 10,
            bAutoWidth: false,
            responsive: true,
            bSort: true,
            order: [[ 2, "desc" ]],
            bFilterOnEnter: true,
            paging: false,
            processing: false
        }
        dataTableOption.columns = [{
            "data": "vendorName"
        }, {
            "data": "resStatus"
        }, {
            "data": "createAt"
        }, {
            "data": "oGuid"
        }];
        dataTableOption.aoColumnDefs = [{
            fnCreatedCell: function(nTd, sData, oData, iRow, iCol) {
                $(nTd).html(tools.formatDate(sData));

            },
            aTargets: [2]
        }, {
            fnCreatedCell: function(nTd, sData, oData, iRow, iCol) {
                var sHtml = '<a href="'+__CERBERUS__.APIRoot+'/survey/survey-ans/' + sData + '" class="removeFilter" data-suGuid="' + sData + '"><i class="fa fa-eye" aria-hidden="true"></i></a> ';
                $(nTd).html(sHtml);

            },
            aTargets: [3]
        }];
        dataTableOption.data = data;
        $("#surveyResponseTable").DataTable(dataTableOption)
    }
})

function editSurvey(dom) {
    var suGuid = $(dom).attr("data-suGuid");
    $("#input-edit-surveyID").val(suGuid);
    $("#form-edit-survey").submit();
}

function openDeleteModal(dom) {
    var suGuid = $(dom).attr("data-suGuid");
    $("#delete-survey-id").val(suGuid);
    $('#deleteModal').modal("show");
}

function createSurvey() {
    location.href = __CERBERUS__.APIRoot + "/survey/survey-designer";
}
