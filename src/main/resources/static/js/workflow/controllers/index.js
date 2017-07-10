$(function () {

    init();

    /***
     * init page
     */
    function init() {
        createWorkflowBtnClick();
        getWorkflows();
    }

    /***
     * when click create workflow
     */
    function createWorkflowBtnClick() {
        $("#btnCreateWorkflow").click(function () {
            location.href = "workflow-designer";
        });
    }
    /***
     * delete this workflow
     */
    function delWorkflows(){
        $(".fa-trash-o").on("click",function(){
            var flowId = $(this).parent().attr("data-flowUID");
            $.ajax({
               url:__CERBERUS__.APIRoot + "/workflow/deleteWorkflow",
                method:"post",
                data:{
                  flowId:flowId
                },
                success:function(res){
                    $("#workflowTableBody").html('');
                    getWorkflows();
                },
                error:function(res){
                    console.log(res);
                }
            });
        });
    }
    /***
     * edit this workflow
     */
    function toEditWorkflows(){
        $(".fa-pencil").on("click",function(){
            var flowUID = $(this).parent().attr("data-flowUID");
            $("#input-edit-workflowUID").attr("value",flowUID);
            $("#form-edit-workflow").submit();
        })
    }
    /***
     * get workflow list
     */
    function getWorkflows() {
        $.ajax({
            url: __CERBERUS__.APIRoot + "/workflow/getProjectWorkflows",
            method: "get",
            success: function (res) {
              $("#workflowTableBody").html('');
                if (res.code == 0) {
                    for (var key in res.data) {
                        var wHtml = "<tr><td>" + res.data[key].name + "</td><td>" + res.data[key].type + "</td><td>" + res.data[key].description + "</td>";
                        wHtml += "<td><a href='javascript:void(0)' data-flowUID='"+res.data[key].flowUID+"' class='removeFilter'><i class='fa fa-pencil'></i></a>";
                        wHtml += "<a href='javascript:void(0)' data-flowUID='"+res.data[key].flowUID+"' class='removeFilter'><i class='fa fa-trash-o'></i></a></td>";
                        wHtml += "</tr>"
                        $("#workflowTableBody").append(wHtml);
                        toEditWorkflows();
                        delWorkflows();
                    }
                }

            },
            error: function (res) {
                console.log(res);
            }
        });
    }

});
