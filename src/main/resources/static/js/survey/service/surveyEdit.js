$.fn.surveyEdit = function () {
    var customerID = 0;
    var self = this;
    var $questionEles = $(this.find("#questions li"));
    var $questionArea = $(this.find("#questionArea"));
    var surveyInfo;
    var isNewSurvey = true;

    var bindingSort = function () {
        settingQuestionNumber();
    }

    var editQuestionBinding = function () {
        $(".questionDisplay").click(function () {

            $(this).parent().find(".questionProp").show();
            $(this).hide();
        });
    }

    var initialDraggable = function () {
        $(".sortableQuestionArea.active").sortable({
            stop: bindingSort,
            delay: 200
        });

        $(".sortableQuestionArea.active").disableSelection();

        editQuestionBinding();

        $questionEles.draggable({
            connectToSortable: ".sortableQuestionArea.active",
            helper: function (event, ui) {
                var type = $(event.currentTarget).attr("type");
                var temp = questionTemplate[type];
                var dom = [];
                dom.push(temp);
                return $(dom.join(''));
            },
            delay: 200,
            start: function (event, ui) {
                var questionArea = $(".ui-draggable-dragging");
                questionArea.find(".panel-body").css("display", "none");
                questionArea.find(".panel-title .text-right").css("display", "none");
            },
            stop: function (event, ui) {

                var questionArea = $(".ui-draggable-dragging");
                questionArea.attr("style", "");

                questionArea.find(".panel-body").css("display", "block");
                questionArea.find(".panel-title .text-right").css("display", "block");

                //binding value
                setVal(questionArea);

                //binding delete function
                deleteQuestionBinding(questionArea);

                //binding save function
                saveQuestionBinding(questionArea);

                //bingding add choice
                addChoiceBinding(questionArea);
                refreshChoice(questionArea);

                //binding add Row
                addRowBinding(questionArea);
                refreshRowValues(questionArea);

                //binding add Column
                addColumnBinding(questionArea);
                refreshColumns(questionArea);

                //binding add Item
                addItemBinding(questionArea);
                refreshItems(questionArea);

                //binding add Rate
                addRateBinding(questionArea);
                refreshRateValues(questionArea);

                //give question number
                settingQuestionNumber();
                autoSetCustomerId(questionArea);

                initialDraggable();
            }
        });
    }

    var initialUpdate = function () {
        if (surveyInfo.uuid) {
            self.parent().find("#surveyName").val(surveyInfo.name);
            self.parent().find("#surveyTitle").val(surveyInfo.survey.title);
            self.parent().find("#surveyType").val(surveyInfo.nType);
            self.parent().find("#surveyDescription").val(surveyInfo.desc);
            BindingPage();
            var dependenceArea = $("body").find("#dependenceContainer");
            dependenceArea.find(".dependences").attr("dependences", JSON.stringify(surveyInfo.survey.triggers));
            refreshDependences(dependenceArea);
            $("body").find(".pageList").find("a").first().trigger('click');
            // $("body").find(".pageList").find("li").first().find('a').addClass('active');
            $("body").find(".pageArea").find(".tab-pane").first().addClass("active");
            isNewSurvey = false;
        }
    }

    var BindingPage = function () {

        if (surveyInfo && surveyInfo.survey.pages) {
            var num = 1;
            $("body").find(".pageList").find("a").remove();
            $("body").find(".pageArea").find("#page1").remove();
            surveyInfo.survey.pages.forEach(function (value, key) {
                var pageName = "page" + num;
                if (num == 1) {
                    self.find(".pageList").append("<a class='nav-link active' data-toggle='tab' href='#" + pageName + "' role='tab'>" + value.name + "</a>");
                } else {
                    self.find(".pageList").append("<a class='nav-link' data-toggle='tab' href='#" + pageName + "' role='tab'>" + value.name + "</a>");
                }
                self.find(".tab-content").append("<div data-pagename=" + value.name + " id=" + pageName + " class='sortableQuestionArea tab-pane'></div>");
                num++;
                if (value.questions) {
                    value.questions.forEach(function (v, k) {
                        bindingQuestions(pageName, v);
                    });
                }

            });
            $(".pageArea").find("[data-save=0]").attr("data-save", 1);

        }
    };

    var deleteQuesionBinding = function (questionArea) {
        questionArea.find(".question-button-delete").click(function () {
            $(this).parents(".questionArea").remove();
            settingQuestionNumber();
        });

    };

    function bindingQuestions(pageName, v) {
        // add questions
        $("#" + pageName).append(questionTemplate[v.type]);

        var targetQuestion = $("#" + pageName).find(".questionArea:last-child").attr("questiondata", JSON.stringify(v));

        saveQuestionBinding(targetQuestion);

        //binding value
        setVal(targetQuestion);


        editQuestionBinding();

        //binding delete function
        deleteQuesionBinding(targetQuestion);

        //binding save function
        saveQuestionBinding(targetQuestion);

        //bingding add choice
        addChoiceBinding(targetQuestion);
        refreshChoice(targetQuestion);

        //binding add Row
        addRowBinding(targetQuestion);
        refreshRowValues(targetQuestion);
        //binding add Column
        addColumnBinding(targetQuestion);
        refreshColumns(targetQuestion);
        //binding add Item
        addItemBinding(targetQuestion);
        refreshItems(targetQuestion);
        //binding add Rate
        addRateBinding(targetQuestion);
        refreshRateValues(targetQuestion);
        //give question number
        settingQuestionNumber();

        initialDraggable();
    }

    function setVal($questionArea) {
        customerID++;
        var questiondata = $.parseJSON($questionArea.attr("questiondata"));

        function bindingAttr(key, value) {
            $questionArea.find("." + key).attr(key, JSON.stringify(value));
        }

        function bindingVal(key, value) {
            if (key == "html") {
                $questionArea.find(".Html").val(value.toString());
            } else {
                $questionArea.find("." + key).val(value.toString());
            }
        }

        function bindingTitle(key, value) {
            $questionArea.find("." + key).val(value);
            $questionArea.find(".questionDisplayTitle").html(value);
        }

        var questionMapping = {
            choices: bindingAttr,
            columns: bindingAttr,
            items: bindingAttr,
            rateValues: bindingAttr,
            title: bindingTitle,
            rows: bindingAttr
        }
        for (var key in questiondata) {
            if (questionMapping[key]) {
                questionMapping[key](key, questiondata[key]);
            } else {
                bindingVal(key, questiondata[key]);
            }
        }
    }

    function deleteQuestionBinding(questionArea) {
        questionArea.find(".question-button-delete").click(function () {
            $(this).parents(".questionArea").remove();
            settingQuestionNumber();
        });

    };

    function saveQuestionBinding(questionArea) {
        var saveBtn = $(questionArea.find(".question-button-save"));
        saveBtn.attr("data-save", 0);

        questionArea.find("input").on("input", function (e) {
            if (saveBtn.data("save") === 1) {
                saveBtn.attr("data-save", 0);
            } else {
                // console.log(e);
            }
        });
        questionArea.find("select").on("change", function (e) {
            if (saveBtn.data("save") === 1) {
                saveBtn.attr("data-save", 0);
            } else {
                // console.log(e);
            }
        });
        questionArea.find("textarea").on("input", function (e) {
            if (saveBtn.data("save") === 1) {
                saveBtn.attr("data-save", 0);
            } else {
                // console.log(e);
            }
        });
        questionArea.find(".expandBtn").click(function () {
            questionArea.find(".questionDisplay").show();
            questionArea.find(".questionProp").hide();
        });
        questionArea.find(".question-button-save").click(function () {

            var questionArea = $(this).parents(".questionArea");
            questionArea.find(".questionDisplay").show();
            questionArea.find(".questionProp").hide();

            var questiontitle = questionArea.find(".title").val();
            var questionData = {
                type: questionArea.attr("questionType"),
                choices: questionArea.find(".choices").length != 0 ? JSON.parse(questionArea.find(".choices").attr("choices")) : undefined,
                choicesOrder: questionArea.find(".choicesOrder").val(),
                colCount: questionArea.find(".colCount").val(),
                hasOther: questionArea.find(".hasOther").val() == "true" ? true : false,
                hasComment: questionArea.find(".hasComment").val() == "true" ? true : false,
                isRequired: questionArea.find(".isRequired").val() == "true" ? true : false,
                name: questionArea.find(".name").val(),
                otherText: questionArea.find(".otherText").val(),
                title: questiontitle,
                visible: questionArea.find(".visible").val() == "true" ? true : false,
                rows: questionArea.find(".rows").length != 0 ? JSON.parse(questionArea.find(".rows").attr("rows")) : undefined,
                columns: questionArea.find(".columns").length != 0 ? JSON.parse(questionArea.find(".columns").attr("columns")) : undefined,
                html: questionArea.find(".Html").val(),
                items: questionArea.find(".items").length != 0 ? JSON.parse(questionArea.find(".items").attr("items")) : undefined,
                rateValues: questionArea.find(".rateValues").length != 0 ? JSON.parse(questionArea.find(".rateValues").attr("rateValues")) : undefined,
            };

            questionArea.attr("questionData", JSON.stringify(questionData));

            questionArea.find(".questionDisplayTitle").html(questionArea.find(".title").val().replace(/</g, '&lt;'));
            $(this).attr("data-save", 1);
        });
    };

    function addChoiceBinding(questionArea) {
        questionArea.find(".addChoice").click(function () {
            var questionArea = $(this).parents(".questionArea");
            var key = questionArea.find(".choiceKey").val();
            var choice = {
                value: questionArea.find(".choiceValue").val(),
                text: key
            };

            questionArea.find(".choiceValue").val("");
            questionArea.find(".choiceKey").val("");
            var choiceValues = questionArea.find(".choices").attr("choices");
            var choiceData = [];
            if (choiceValues != "") {
                choiceData = JSON.parse(choiceValues);
            }
            choiceData.push(choice);
            questionArea.find(".choices").attr("choices", JSON.stringify(choiceData));
            refreshChoice(questionArea);
        });
    };

    function refreshChoice(questionArea) {
        //choiceData

        if (questionArea.find(".choices").length > 0) {
            questionArea.find(".choiceContent").html("");
            var choiceData = JSON.parse(questionArea.find(".choices").attr("choices"));
            $.each(choiceData, function (key, value) {
                var choiceContent = questionArea.find(".choiceContent").append("<tr><td>" + value.value + "</td><td>" + value.text + "</td><td><button type='button' class='btn removeChoice' key='" + value.value + "'>remove</button></td></tr>");
                removeChoiceButton(choiceContent);
            });
        }

    }

    function removeChoiceButton(choiceContent) {
        choiceContent.find(".removeChoice").click(function () {
            var key = $(this).attr("key");
            var questionArea = $(this).parents(".questionArea");
            var choiceData = JSON.parse(questionArea.find(".choices").attr("choices"));

            for (var i = 0; i < choiceData.length; i++) {
                if (choiceData[i].value == key) {
                    choiceData.splice(i, 1);
                    break;
                }
            }

            questionArea.find(".choices").attr("choices", JSON.stringify(choiceData));
            $(this).parents("tr").remove();
        });
    };

    function addRowBinding(questionArea) {
        questionArea.find(".addRow").click(function () {
            var questionArea = $(this).parents(".questionArea");
            var key = questionArea.find(".rowKey").val();
            var row = {
                value: questionArea.find(".rowValue").val(),
                text: key
            };

            questionArea.find(".rowValue").val("");
            questionArea.find(".rowKey").val("");
            var rowValues = questionArea.find(".rows").attr("rows");
            var rowData = [];
            if (rowValues != "") {
                rowData = JSON.parse(rowValues);
            }
            rowData.push(row);
            questionArea.find(".rows").attr("rows", JSON.stringify(rowData));

            refreshRowValues(questionArea);
        });
    };

    function refreshRowValues(questionArea) {
        if (questionArea.find(".rows").length > 0) {
            questionArea.find(".rowContent").html("");
            var rowValuesData = JSON.parse(questionArea.find(".rows").attr("rows"));
            $.each(rowValuesData, function (key, value) {
                var rowContent = questionArea.find(".rowContent").append("<tr><td>" + value.value + "</td><td>" + value.text + "</td><td><button type='button' class='btn removeRow' key='" + value.value + "'>remove</button></td></tr>");
                removeRowButton(rowContent);
            });
        }

    }

    function removeRowButton(rowContent) {
        rowContent.find(".removeRow").click(function () {
            var key = $(this).attr("key");
            var questionArea = $(this).parents(".questionArea");
            var rowData = JSON.parse(questionArea.find(".rows").attr("rows"));
            for (var i = 0; i < rowData.length; i++) {
                if (rowData[i].value == key) {
                    rowData.splice(i, 1);
                    break;
                }
            }
            questionArea.find(".rows").attr("rows", JSON.stringify(rowData));
            $(this).parents("tr").remove();
        });
    };

    function addColumnBinding(questionArea) {
        questionArea.find(".addColumn").click(function () {
            var questionArea = $(this).parents(".questionArea");
            var key = questionArea.find(".columnKey").val();
            var column = {
                value: questionArea.find(".columnValue").val(),
                text: key
            };

            questionArea.find(".columnValue").val("");
            questionArea.find(".columnKey").val("");
            var columnValues = questionArea.find(".columns").attr("columns");
            var columnData = [];
            if (columnValues != "") {
                columnData = JSON.parse(columnValues);
            }
            columnData.push(column);
            questionArea.find(".columns").attr("columns", JSON.stringify(columnData));

            refreshColumns(questionArea);
        });
    };

    function refreshColumns(questionArea) {
        if (questionArea.find(".columns").length > 0) {
            var columnData = JSON.parse(questionArea.find(".columns").attr("columns"));
            questionArea.find(".columnContent").html("");

            $.each(columnData, function (key, value) {
                var columnContent = questionArea.find(".columnContent").append("<tr><td>" + value.value + "</td><td>" + value.text + "</td><td><button type='button' class='btn removeColumn' key='" + value.value + "'>remove</button></td></tr>");
                removeColumnButton(columnContent);
            });
        }
    };

    function removeColumnButton(columnContent) {
        columnContent.find(".removeColumn").click(function () {
            var key = $(this).attr("key");
            var questionArea = $(this).parents(".questionArea");
            var columnData = JSON.parse(questionArea.find(".columns").attr("columns"));
            for (var i = 0; i < columnData.length; i++) {
                if (columnData[i].value == key) {
                    columnData.splice(i, 1);
                    break;
                }
            }
            questionArea.find(".columns").attr("columns", JSON.stringify(columnData));
            $(this).parents("tr").remove();
        });
    };

    function addItemBinding(questionArea) {
        questionArea.find(".addItem").click(function () {
            var questionArea = $(this).parents(".questionArea");
            var key = questionArea.find(".itemKey").val();
            var item = {
                name: questionArea.find(".itemValue").val(),
                title: key,
            };
            questionArea.find(".itemValue").val("");
            questionArea.find(".itemKey").val("");
            var itemValues = questionArea.find(".items").attr("items");
            var itemData = [];
            if (itemValues != "") {
                itemData = JSON.parse(itemValues);
            }
            itemData.push(item);
            questionArea.find(".items").attr("items", JSON.stringify(itemData));

            refreshItems(questionArea);
        });
    };

    function refreshItems(questionArea) {

        if (questionArea.find(".items").length > 0) {
            var itemData = JSON.parse(questionArea.find(".items").attr("items"));
            questionArea.find(".itemContent").html("");
            $.each(itemData, function (key, value) {
                var itemContent = questionArea.find(".itemContent").append("<tr><td>" + value.name + "</td><td>" + value.title + "</td><td><button type='button' class='btn removeItem' key='" + value.name + "'>remove</button></td></tr>");
                removeItemButton(itemContent);
            });
        }
    };

    function removeItemButton(itemContent) {
        itemContent.find(".removeItem").click(function () {

            var key = $(this).attr("key");
            var questionArea = $(this).parents(".questionArea");
            var itemData = JSON.parse(questionArea.find(".items").attr("items"));

            for (var i = 0; i < itemData.length; i++) {
                if (itemData[i].name == key) {
                    itemData.splice(i, 1);
                    break;
                }
            }
            questionArea.find(".items").attr("items", JSON.stringify(itemData));
            $(this).parents("tr").remove();
        });
    };

    function addRateBinding(questionArea) {
        questionArea.find(".addRate").click(function () {
            var questionArea = $(this).parents(".questionArea");
            var key = questionArea.find(".rateKey").val();
            var rate = {
                value: questionArea.find(".rateValue").val(),
                text: key
            };

            questionArea.find(".rateValue").val("");
            questionArea.find(".rateKey").val("");
            var rateValues = questionArea.find(".rateValues").attr("rateValues");
            var rateData = [];
            if (rateValues != "") {
                rateData = JSON.parse(rateValues);
            }
            rateData.push(rate);
            questionArea.find(".rateValues").attr("rateValues", JSON.stringify(rateData));

            refreshRateValues(questionArea);
        });
    };

    function refreshRateValues(questionArea) {

        if (questionArea.find(".rateValues").length > 0) {
            var rateData = JSON.parse(questionArea.find(".rateValues").attr("rateValues"));
            //rateData
            questionArea.find(".rateContent").html("");
            $.each(rateData, function (key, value) {
                var rateContent = questionArea.find(".rateContent").append("<tr><td>" + value.value + "</td><td>" + value.text + "</td><td><button type='button' class='btn removeRate' key='" + value.value + "'>remove</button></td></tr>");
                removeRateButton(rateContent);
            });
        }

    };

    function removeRateButton(rateContent) {
        rateContent.find(".removeRate").click(function () {
            var key = $(this).attr("key");
            var questionArea = $(this).parents(".questionArea");
            var rateData = JSON.parse(questionArea.find(".rateValues").attr("rateValues"));
            for (var i = 0; i < rateData.length; i++) {
                if (rateData[i].value == key) {
                    rateData.splice(i, 1);
                    break;
                }
            }
            questionArea.find(".rateValues").attr("rateValues", JSON.stringify(rateData));
            $(this).parents("tr").remove();
        });
    };

    function autoSetCustomerId(questionArea) {
        questionArea.find(".name").val("customerID" + customerID++);
    };

    var questionItem = {
        topButton: '<div class="col-md-12 text-right"><button class="btn question-button-delete"></button><button class="btn question-button-save"></button></div>',
        questionTitle: '<div class="question-title-label">Question:</div><div class="form-group row"><div class="col-md-10"><textarea class="form-control input-md title" placeholder="Question"></textarea></div></div>',
        required: '<div class="col-xs-12 col-sm-12 col-md-4"><label class="">Required:</label><div class=""><select class="form-control isRequired"><option value="false">false</option><option value="true">true</option></select></div></div>',
        columnCol: '<div class="col-xs-12 col-sm-12 col-md-4"><label class="">Column Count:</label><div class=""><input type="text" class="form-control input-md colCount"></div></div>',
        choiceOrder: '<div class="col-xs-12 col-sm-12 col-md-4"><label class="">Choices Order:</label><div class=""><select class="form-control choicesOrder"><option value="asc">asc</option><option value="desc">desc</option><option value="random">random</option></select></div></div>',
        otherQuestion: '<div class="col-xs-12 col-sm-12 col-md-4"><label class="">Question has other answer:</label><div class=""><select class="form-control hasOther"><option value="false">false</option><option value="true">true</option></select></div></div>',
        otherText: '<div class="col-xs-12 col-sm-12 col-md-4"><label class="">Question has other text:</label><div class=""><input type="text" class="form-control input-md otherText"></div></div>',
        visible: '<div class="col-xs-12 col-sm-12 col-md-4"><label class="">Visible:</label><div class=""><select class="form-control visible"><option value="true">true</option><option value="false">false</option></select></div></div>',
        comment: '<div class="col-xs-12 col-sm-12 col-md-4"><label class="">Question has comment:</label><div class=""><select class="form-control hasComment"><option value="false">false</option><option value="true">true</option></select></div></div>',
        name: '<div class="col-xs-12 col-sm-12 col-md-4" style="display: none;"><label class="">Name:</label><div class=""><input type="text" class="form-control input-md name"/></div></div>',
        html: '<div class="col-md-6"><label>Html:</label><textarea type="text" class="form-control input-md Html"></textarea></div>',
        choices: '<div class="question-title-label">Choices:</div><div class="form-group"><div class="row"><div class="col-md-5"><div class="control-label"><label>Value:</label></div><div class=""><input type="text" class="form-control input-md choiceValue"/></div></div><div class="col-md-5"><div class="control-label"><label>Label:</label></div><div class=""><input type="text" class="form-control input-md choiceKey"/></div></div><div class="col-md-2 text-xs-left"><button type="button" class="btn orange-btn margin-top-btn addChoice">Add Choice</button></div></div><div class="row"><div class="col-md-12"><div class="page_box_content"><table class="table table-striped table-hover dataTable no-footer choices" choices="[]"><thead><tr><th>Value</th><th>Label</th><th>Remove</th></tr></thead><tbody class="choiceContent"></tbody></table></div></div></div></div>',
        items: '<div class="question-title-label">Items:</div><div class="form-group"><div class="row"><div class="col-md-5"><label>Value:</label><input type="text"  class="form-control input-md itemValue"/></div><div class="col-md-5"><label>Label:</label><input type="text"  class="form-control input-md itemKey"/></div><div class="col-md-2 text-right form-group"><button type="button" class="btn orange-btn margin-top-btn addItem">Add Item</button></div></div><div class="row"><div class="col-md-12"><div class="page_box_content"><table class="table table-striped table-bordered items" items="[]"><thead><tr><th>Value</th><th>Label</th><th>Remove</th></tr></thead><tbody class="itemContent"></tbody></table></div></div></div></div>',
        rows: '<div class="question-title-label">Rows:</div><div class="row"><div class="col-md-5"><label>Value:</label><input type="text" class="form-control input-md rowValue"/></div><div class="col-md-5"><label>Label:</label><input type="text" class="form-control input-md rowKey"/></div><div class="col-md-2 text-right form-group"><button type="button" class="btn orange-btn margin-top-btn addRow">Add Row</button></div></div><div class="row"><div class="col-md-12"><div class="page_box_content"><table class="table table-striped table-hover dataTable no-footer rows" rows="[]"><thead><tr><th>Value</th><th>Label</th><th>Remove</th></tr></thead><tbody class="rowContent"></tbody></table></div></div></div>',
        columns: '<div class="question-title-label">Columns:</div><div class="row"><div class="col-md-5"><label>Value:</label><input type="text"  class="form-control input-md columnValue"/></div><div class="col-md-5"><label>Label:</label><input type="text" class="form-control input-md columnKey"/></div><div class="col-md-2 text-right form-group"><button type="button" class="btn orange-btn margin-top-btn addColumn">Add Column</button></div></div><div class="row"><div class="col-md-12"><div class="page_box_content"><table class="table table-striped table-hover dataTable no-footer columns" columns="[]"><thead><tr><th>Value</th><th>Label</th><th>Remove</th></tr></thead><tbody class="columnContent"></tbody></table></div></div></div>',
        rates: '<div class="question-title-label">Rates:</div><div class="form-group"><div class="row"><div class="col-xs-12 col-sm-12 col-md-4" style="display: none;"><label class="">Name:</label><inputtype="text" class="form-control input-md name"/></div><div class="col-md-5"><label>Value:</label><input type="text"  class="form-control input-md rateValue"/></div><div class="col-md-5"><label>Label:</label><input type="text"  class="form-control input-md rateKey"/></div><div class="col-md-2 text-right form-group"><button type="button" class="btn orange-btn margin-top-btn addRate">Add Rate</button></div></div><div class="row"><div class="col-md-12"><div class="page_box_content"><table class="table table-striped table-bordered rateValues" rateValues="[]"><thead><tr><th>Value</th><th>Label</th><th>Remove</th></tr></thead><tbody class="rateContent"></tbody></table></div></div></div></div>',
    }

    var questionTemplate = {
        checkbox: '<div class="panel checkboxQuestionArea questionArea" questionType="checkbox" questionData="[]"><div class="panel-heading"><h3 class="panel-title page-title-area"><div class="col-md-6">Checkbox</div>' + questionItem['topButton'] + '</h3></div><div class="panel-body"><div class="questionDisplay col-md-12"><span class="badge questionNumber">1</span><span class="questionDisplayTitle">Question</span></div><div class="questionProp col-md-12"><div class="expandBtn"><i class="fa fa-times" aria-hidden="true"></i></div>' + questionItem['questionTitle'] + '<div class="question-title-label">Property:</div><div class="form-group row">' + questionItem['required'] + questionItem['columnCol'] + questionItem['choiceOrder'] + '</div><div class="row form-group">' + questionItem['otherQuestion'] + questionItem['otherText'] + questionItem['visible'] + '</div><div class="form-group row">' + questionItem['comment'] + questionItem['name'] + '</div>' + questionItem['choices'] + '</div></div></div>',
        dropdown: '<div class="panel dropdownQuestionArea questionArea" questionType="dropdown" questionData="[]"><div class="panel-heading"><h3 class="panel-title page-title-area"><div class="col-md-6">Dropdown</div>' + questionItem['topButton'] + '</h3></div><div class="panel-body"><div class="questionDisplay col-md-12"><span class="badge questionNumber">1</span><span class="questionDisplayTitle">Question</span></div><div class="questionProp col-md-12"><div class="expandBtn"><i class="fa fa-times" aria-hidden="true"></i></div>' + questionItem['questionTitle'] + '<div class="question-title-label">Property:</div><div class="form-group row">' + questionItem['required'] + questionItem['visible'] + questionItem['choiceOrder'] + '</div><div class="row form-group">' + questionItem['otherQuestion'] + questionItem['otherText'] + questionItem['name'] + '</div>' + questionItem['choices'] + '</div></div></div>',
        html: '<div class="panel htmlQuestionArea questionArea" questionType="html" questionData="[]"><div class="panel-heading"><h3 class="panel-title page-title-area"><div class="col-md-6">Html</div>' + questionItem['topButton'] + '</h3></div><div class="panel-body"><div class="questionDisplay col-md-12"><span class="badge questionNumber">1</span><span class="questionDisplayTitle">Question</span></div><div class="questionProp col-md-12"><div class="expandBtn"><i class="fa fa-times" aria-hidden="true"></i></div>' + questionItem['questionTitle'] + '<div class="question-title-label">Property:</div>' + questionItem['name'] + '<div class="form-group row">' + questionItem['html'] + questionItem['visible'] + '</div></div></div></div>',
        radiogroup: '<div class="panel radiogroupQuestionArea questionArea" questionType="radiogroup" questionData="[]"><div class="panel-heading"><h3 class="panel-title page-title-area"><div class="col-md-6">Radiogroup</div>' + questionItem['topButton'] + '</h3></div><div class="panel-body"><div class="questionDisplay col-md-12"><span class="badge questionNumber">1</span><span class="questionDisplayTitle">Question</span></div><div class="questionProp col-md-12"><div class="expandBtn"><i class="fa fa-times" aria-hidden="true"></i></div>' + questionItem['questionTitle'] + '<div class="question-title-label">Property:</div>' + questionItem['name'] + '<div class="form-group row">' + questionItem['columnCol'] + questionItem['choiceOrder'] + questionItem['otherQuestion'] + '</div><div class="form-group row">' + questionItem['otherText'] + questionItem['visible'] + questionItem['required'] + '</div>' + questionItem['choices'] + '</div></div></div>',
        multipletext: '<div class="panel multipleTextQuestionArea questionArea" questionType="multipletext" questionData="[]"><div class="panel-heading"><h3 class="panel-title page-title-area"><div class="col-md-6">Multiple Text</div>' + questionItem['topButton'] + '</h3></div><div class="panel-body"><div class="questionDisplay col-md-12"><span class="badge questionNumber">1</span><span class="questionDisplayTitle">Question</span></div><div class="questionProp col-md-12"><div class="expandBtn"><i class="fa fa-times" aria-hidden="true"></i></div>' + questionItem['questionTitle'] + '<div class="question-title-label">Property:</div><div class="form-group row">' + questionItem['name'] + questionItem['columnCol'] + questionItem['visible'] + questionItem['required'] + '</div>' + questionItem['items'] + '</div></div></div>',
        comment: '<div class="panel commentQuestionArea questionArea" questionType="comment" questionData="[]"><div class="panel-heading"><h3 class="panel-title page-title-area"><div class="col-md-6">Comment</div>' + questionItem['topButton'] + '</h3></div><div class="panel-body"><div class="questionDisplay col-md-12"><span class="badge questionNumber">1</span><span class="questionDisplayTitle">Question</span></div><div class="questionProp col-md-12"><div class="expandBtn"><i class="fa fa-times" aria-hidden="true"></i></div>' + questionItem['questionTitle'] + '<div class="question-title-label">Property:</div><div class="form-group row">' + questionItem['visible'] + questionItem['name'] + questionItem['required'] + '</div></div></div></div>',
        rating: '<div class="panel ratingQuestionArea questionArea" questionType="rating" questionData="[]"><div class="panel-heading"><h3 class="panel-title page-title-area"><div class="col-md-6">Rating</div>' + questionItem['topButton'] + '</h3></div><div class="panel-body"><div class="questionDisplay col-md-12"><span class="badge questionNumber">1</span><span class="questionDisplayTitle">Question</span></div><div class="questionProp col-md-12"><div class="expandBtn"><i class="fa fa-times" aria-hidden="true"></i></div>' + questionItem['questionTitle'] + '<div class="question-title-label">Property:</div><div class="form-group row">' + questionItem['visible'] + questionItem['required'] + questionItem['name'] + '</div>' + questionItem['rates'] + '</div></div></div>',
        text: '<div class="panel checkboxQuestionArea questionArea" questionType="text" questionData="[]"><div class="panel-heading"><h3 class="panel-title page-title-area"><div class="col-md-6">Text</div>' + questionItem['topButton'] + '</h3></div><div class="panel-body"><div class="questionDisplay col-md-12"><span class="badge questionNumber">1</span><span class="questionDisplayTitle">Question</span></div><div class="questionProp col-md-12"><div class="expandBtn"><i class="fa fa-times" aria-hidden="true"></i></div>' + questionItem['questionTitle'] + '<div class="question-title-label">Property:</div><div class="form-group row">' + questionItem['visible'] + questionItem['required'] + '</div></div></div>' + questionItem['name'] + '</div>',
        matrix: '<div class="panel matrixSingleQuestionArea questionArea" questionType="matrix" questionData="[]"><div class="panel-heading"><h3 class="panel-title page-title-area"><div class="col-md-6">Matrix single</div>' + questionItem['topButton'] + '</h3></div><div class="panel-body"><div class="questionDisplay col-md-12"><span class="badge questionNumber">1</span><span class="questionDisplayTitle">Question</span></div><div class="questionProp col-md-12"><div class="expandBtn"><i class="fa fa-times" aria-hidden="true"></i></div>' + questionItem['questionTitle'] + '<div class="question-title-label">Property:</div><div class="form-group row">' + questionItem['visible'] + questionItem['required'] + '</div>' + questionItem['rows'] + questionItem['columns'] + '</div></div>' + questionItem['name'] + '</div>'
    }

    function settingQuestionNumber() {
        $(".pageArea").each(function (k, v) {
            $(v).find(".questionNumber").each(function (number, value) {
                $(value).html((number + 1));
            });
        });
    }

    initialDraggable();

    if (self.parent().find("#survey").val()) {
        surveyInfo = JSON.parse(self.parent().find("#survey").val());
        $("body").find("#surveyID").val(surveyInfo.uuid);
        initialUpdate();
    }


    function generator() {
        var surveyJSON = {
            title: "survey title",
            pages: []
        };
        var title = $("body").find("#surveyTitle").val();
        if (title) {
            surveyJSON.title = title;
        }
        $("body").find(".pageArea").find(".sortableQuestionArea").each(function (index, element) {
            var questions = [];
            $(element).find(".questionArea").each(function (i, element) {
                var data = $(element).attr("questiondata");
                if (data != "[]") {
                    questions.push(JSON.parse(data));
                }
            });
            var pageName;
            $("body").find(".pageList").find('a').each(function (n, item) {
                if ($(item).attr('href') == ('#' + $(element).attr('id'))) {
                    pageName = $(item).html();
                    surveyJSON.pages.push({
                        // name: $(this).attr("data-pagename"),
                        name: pageName,
                        questions: questions
                    });
                }
            });
        });


        surveyJSON.triggers = JSON.parse($("body").find(".dependences").attr("dependences"));
        return StringToBooleanTransform(surveyJSON);
        //return surveyJSON;
    };


    function StringToBooleanTransform(obj) {

        var objStr = JSON.stringify(obj);
        objStr = objStr.replace(new RegExp("\"true\"", 'g'), "true");
        objStr = objStr.replace(new RegExp("\"false\"", 'g'), "false");

        return JSON.parse(objStr);
    }

    function refreshDependences(dependenceArea) {
        if (dependenceArea.find(".dependences").length > 0) {
            var dependenceData = JSON.parse(dependenceArea.find(".dependences").attr("dependences"));
            var questions = [];
            $("body").find(".pageArea").find(".sortableQuestionArea").each(function (index, element) {
                $(element).find(".questionArea").each(function (index, element) {
                    var data = $(element).attr("questiondata");
                    if (data != "[]") {
                        questions.push({
                            name: JSON.parse(data).name,
                            display: JSON.parse(data).title
                        });
                    }
                });
            });
            dependenceArea.find(".dependenceContent").html("");
            var sourceQuestion;
            var distQuestion;
            $.each(dependenceData, function (key, value) {
                for (var k in questions) {
                    if (questions[k].name == value.name) {
                        sourceQuestion = questions[k].display;
                    }
                    if (questions[k].name == value.questions) {
                        distQuestion = questions[k].display;
                    }
                }
                var dependenceContent = dependenceArea.find(".dependenceContent").append("<tr><td>" + sourceQuestion + "</td><td>" + value.value + "</td><td>" + value.pages + "</td><td>" + distQuestion + "</td><td><button type='button' class='btn removeDependence' key='" + value.name + "' value='" + value.value + "'>remove</button></td></tr>");
                removeDependenceButton(dependenceContent);
            });
        }
    };

    function removeDependenceButton(dependenceContent) {
        dependenceContent.find(".removeDependence").click(function () {
            var key = $(this).attr("key");
            var value = $(this).attr("value");
            var dependenceArea = $("body").find(".dependences");
            var dependenceData = JSON.parse(dependenceArea.attr("dependences"));

            for (var i = 0; i < dependenceData.length; i++) {
                if ((dependenceData[i].name == key) && (dependenceData[i].value == value)) {
                    dependenceData.splice(i, 1);
                    break;
                }
            }
            dependenceArea.attr("dependences", JSON.stringify(dependenceData));
            $(this).parents("tr").remove();
        });
    };

    function sendDataToServer(survey) {
        var resultAsString = JSON.stringify(survey.data);
        alert(resultAsString); //send Ajax request to your web server.
    }

    function htmlUnescape(value) {
        return String(value).replace(/&quot;/g, '"').replace(/&#39;/g, "'").replace(/&lt;/g, '<').replace(/&gt;/g, '>')
            .replace(/&amp;/g, '&').replace(/&nbsp;&nbsp;&nbsp;&nbsp;/g, '\t').replace(/&nbsp;/g, ' ');
    }

    function UnescapeTitle() {
        $(".sv_q_title").each(function (key, value) {
            $(value).html(htmlUnescape($(value).html()));
        });

        $("span[data-bind]").each(function (key, value) {
            $(value).html(htmlUnescape($(value).html()));
        });

        $("td[data-bind]").each(function (key, value) {
            $(value).html(htmlUnescape($(value).html()));
        });

        $("th[data-bind]").each(function (key, value) {
            $(value).html(htmlUnescape($(value).html()));
        });

        $("option").each(function (key, value) {
            $(value).html(htmlUnescape($(value).html()));
        })
    }


    return {
        addPage: function () {
            self.parent().find("#addPageDialog").modal("show");
        },

        doAddPage: function () {
            var pageName = self.parent().find("#pageName").val();
            var num = 0;
            if (pageName) {
                self.find(".pageList li a").each(function (index, item) {
                    if ($(item).attr("href") == ('#' + pageName)) {
                        alert("You can not add a same page");
                        num = 1;
                    }
                });
            } else {
                alert("You need input a page name");
                num = 1;
            }
            if (num == 0) {
                self.find(".pageArea").find(".deleteBtn").css("display", "block");
                self.find(".pageArea").find(".deleteBtn").css("float", "right");
                var len = self.find(".pageList").find("a").length + 1;
                var id = "page" + len;
                self.find(".pageList").append("<a class='nav-link pull-left' data-toggle='tab' href='#" + id + "' role='tab'>" + pageName.replace("<", "&lt;") + "</a>");
                self.find(".tab-content").append("<div id=" + id + " data-pageName = " + pageName.replace("<", "&lt;") + " class='sortableQuestionArea tab-pane ui-sortable'></div>");
                self.parent().find("#addPageDialog").modal("hide");
                self.parent().find("#pageName").val("");
                if (self.find(".pageList").find("a").length == 1) {
                    self.find(".pageList").find("a").trigger("click");
                }
            }
        },

        deleteCurrentPage: function () {
            var currentPage = self.find(".pageList").find(".active").attr("href");
            var nextPage = self.find(".pageList").find(".active").next();
            self.find(".pageList").find(".active").remove();
            // if(nextPage.length==0){
            self.find(".pageList").find("a").first().trigger("click");
            // }else {
            //     nextPage.find('a').trigger("click");
            // }

            self.find(".pageArea").find(currentPage).remove();
            if (self.find(".pageList").find("a").length == 1) {
                self.find(".pageList").find("a").trigger("click");
            }
            if (self.find(".pageList").find("a").length == 0) {
                self.find(".pageArea").find(".deleteBtn").css("display", "none");
            }
        },

        addDependences: function () {

            var dependenceArea = $("body").find("#dependenceContainer");
            var page = dependenceArea.find(".dependencePages").val();
            var pages = [];
            pages.push(page);
            var question = dependenceArea.find(".dependenceQuestions").val();
            var questions = [];
            questions.push(question);
            var dependence = {
                name: dependenceArea.find(".dependenceId").val(),
                value: dependenceArea.find(".dependenceValue").val(),
                questions: questions,
                operator: "equal",
                type: "visible",
                pages: pages,
            };

            dependenceArea.find(".dependenceId").val("");
            dependenceArea.find(".dependenceValue").val("");
            dependenceArea.find(".dependenceQuestions").val("");
            dependenceArea.find(".dependencePages").val("");
            var dependences = dependenceArea.find(".dependences").attr("dependences");
            var dependenceData = [];
            if (dependences != "[]") {
                dependenceData = JSON.parse(dependences);
            }
            dependenceData.push(dependence);
            dependenceArea.find(".dependences").attr("dependences", JSON.stringify(dependenceData));
            refreshDependences(dependenceArea);
        },

        addDependence: function () {
            $('.dependencePages').find('option').remove();
            $('.dependencePages').append('<option>select a page</option>');
            $('.dependenceId').find('option').remove();
            $('.dependenceId').append('<option>select source question</option>');
            $('.dependenceQuestions').find('option').remove();
            $('.dependenceQuestions').append('<option>select dist question</option>');
            $("body").find(".pageArea").find(".sortableQuestionArea").each(function (index, element) {
                var questions = [];
                $(element).find(".questionArea").each(function (index, element) {
                    var data = $(element).attr("questiondata");
                    var title = JSON.parse(data)['title'];
                    if (data != "[]") {
                        questions.push(JSON.parse(data));
                        $('.dependenceId').append('<option value="' + JSON.parse(data)['name'] + '">' + title + '</option>');
                        $('.dependenceQuestions').append('<option value="' + JSON.parse(data)['name'] + '">' + title + '</option>');
                    }

                });
            });
            var pageName;
            $("body").find(".pageList").find('a').each(function (index, item) {
                pageName = $(item).html();
                $('.dependencePages').append('<option value="' + pageName + '">' + pageName + '</option>');
            })
            self.parent().find("#addDependenceDialog").modal("show");
        },

        runSurvey: function () {
            $("#runSurveyDialog").modal("show");
            $(".sortableQuestionArea").find("");

            var surveyJSON = generator();
            var survey = new Survey.Survey(surveyJSON, "surveyContainer");
            survey.onComplete.add(sendDataToServer);
            UnescapeTitle();
            survey.onCurrentPageChanged.add(UnescapeTitle);

            window.currentLanguage = "en";
            function i18n() {
                updateText();
                $("button[type='button']").on('click', function () {
                    var language = $(this).attr("language")
                    if (language != undefined)
                        window.currentLanguage = language;
                    updateText();
                });
                $("input[type='button']").on('click', function () {
                    var language = $(this).attr("language")
                    if (language != undefined)
                        window.currentLanguage = language;
                    updateText();
                });
            }

            function updateText() {
                var i18n = $.i18n();
                i18n.locale = window.currentLanguage;
                i18n.load('/js/survey/i18n/survey-' + i18n.locale + '.json', i18n.locale).done(function () {

                    $("span.data-bind").each(function (key, value) {
                        var data = $(value).attr("key");
                        $(value).html($.i18n(data));
                    });
                });
            }

            i18n();
        },

        saveSurvey: function () {
            var isSave = $(".pageArea").find("[data-save=0]");
            // var num = 0;
            // $.each($(".questionArea .panel-body .questionDisplay"), function (k, v) {
            //     if ($(v).css("display") == "none") {
            //         num = 1;
            //     }
            // });
            // if (num == 0 && isSave.length===0){
            //     $("#saveSurveyDialog").modal("show");
            // }
            if (isSave.length === 0) {
                $("#saveSurveyDialog").modal("show");
            } else {
                $("#messageDialog").modal("show");
            }
        },

        getSurveyInfo: function () {
            var surveyName = $("body").find("#surveyName").val();
            var surveyTitle = $("body").find("#surveyTitle").val();
            var surveyType = $("body").find("#surveyType").val();
            var surveyDescription = $("body").find("#surveyDescription").val();

            var surveyJson = {
                "name": surveyName,
                "desc": surveyDescription,
                "nType": surveyType,
                "survey": generator(),
                "title": surveyTitle,
            }
            return surveyJson;
        }

    }
};
