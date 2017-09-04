$(function () {

    window.surveyObject = {
        survey: {},
        surveyID: {},
        questionData: {},
        answerData: {},
        oGuid: null,
        resStatus: "start"
    };

    var countryList = [
        { language: 'english', locate: 'en' },
        { language: 'chinese', locate: 'cn' },
        { language: 'spanish', locate: 'es' }
    ];

    var barCodeText = getRandomInt(100000000000, 999999999999);

    /**
     *
     * @param survey
     */
    function sendDataToServer(survey) {
        var resultAsString = JSON.stringify(survey.data);

        var surveyAnswerData = survey.data;

        var sendData = {
            suGuid: surveyObject.surveyID,
            surveyRes: surveyAnswerData,
            oGuid: surveyObject.oGuid,
            resStatus: "completed",
            customer: {
                firstName: $("#firstName").val(),
                lastName: $("#lastName").val(),
                phone: $("#phoneNumber").val()
            }
        };

        var score = 0;

        $.each(surveyAnswerData, function (index, item) {
            if (typeof(item) === 'object') {
                var flag = false;
                $.each(item, function(key, value){
                    if(value.indexOf('_score_max')>=0){
                        flag =true;
                    }else{
                        if (parseInt(value) && (value.indexOf('_score_')>=0)) {
                            score += parseInt(value);
                        }
                    }
                });
                if(flag){
                    var max = item.map(function (x) {
                        return parseInt(x)
                    }).reduce(function (x, y) {
                        return x >= y ? x : y;
                    });
                    score += max;
                }
            } else {
                if ((item.indexOf('_score_')>=0) &&parseInt(item)) {
                    score += parseInt(item)
                }
            }
        });

        alert(score);

        $.ajaxEx({
            contentType: 'application/json',
            dataType: 'json',
            url: __CERBERUS__.APIRoot + "/survey/updateSurveyObj",
            method: "post",
            data: sendData,
            success: function (data) {
                $("#saveSurvey").hide();
                $("#btnExport").show();
                alert("your survey is saved! Thank you for your time.");
            },
            error: function (data) {
                console.log(data);
            }
        })


        // alert(resultAsString); //send Ajax request to your web server.
    }

    function StringToBooleanTransform(obj) {
        var objStr = JSON.stringify(obj);
        if (objStr && objStr.length > 0) {
            objStr = objStr.replace(new RegExp("\"true\"", 'g'), "true");
            objStr = objStr.replace(new RegExp("\"false\"", 'g'), "false");
            return JSON.parse(objStr);
        }
        return {};

    }


    /**
     * initial survey content
     */
    function initial() {

        if ($("#firstName").val() != '') {
            $("#firstName").attr("readonly", true);
        } else {
            $("#firstName").attr("readonly", false);
        }
        if ($("#lastName").val() != '') {
            $("#lastName").attr("readonly", true);
        } else {
            $("#lastName").attr("readonly", false);
        }
        if ($("#phoneNumber").val() != '') {
            $("#phoneNumber").attr("readonly", true);
        } else {
            $("#phoneNumber").attr("readonly", false);
        }



        surveyObject.surveyID = $("#sGuid").val();
        surveyObject.oGuid = $("#oGuid").val();

        $.ajaxEx({
            url: __CERBERUS__.APIRoot + "/survey/getSurveyById",
            method: "get",
            data: {uuid: surveyObject.surveyID},
            success: function (data) {
                surveyObject.questionData = StringToBooleanTransform(data.survey);
                surveyObject.survey = new Survey.Survey(surveyObject.questionData, "surveyArea");
                UnescapeTitle();
                surveyChange();
                surveyObject.survey.onCurrentPageChanged.add(UnescapeTitle);
                surveyObject.survey.onCurrentPageChanged.add(checkComplete);

                surveyObject.survey.onComplete.add(sendDataToServer);
                initialAnswer();
            },
            error: function (data) {
                console.log(data);
            }
        });
    }


    function initialAnswer() {

        var sendData = {oGuid: surveyObject.oGuid}

        $.ajaxEx({
            url: __CERBERUS__.APIRoot + "/survey/getSurveyObj",
            method: "POST",
            data: sendData,
            success: function (data) {
                if (typeof data == "string") {
                    data = JSON.parse(data);
                }
                if (data.code == "0") {
                    surveyObject.survey.data = data.data.data.surveyRes;

                    surveyObject.resStatus = data.data.data.resStatus;
                    checkComplete();
                    $(".i18n[language='" + currentLanguage + "']").trigger("click");
                }

            },
            error: function (data) {
                console.log(data);
            }
        })

        $('input[type=button]').on('click', function () {
            $(".i18n[language='" + currentLanguage + "']").trigger("click");
            // quesAnswersFormat();
        })
    }

    function quesAnswersFormat() {
        var formNodes = $('.sv_qcbc');
        if(formNodes && formNodes.length > 0){

            var divNodes = $(formNodes[3]).children('div');
            $(formNodes[3]).css("height", "860px")
            divNodes.css("position", "absolute");
            for(var index = 0; index < divNodes.length; index++){
                switch (index % 4){
                    case 1:
                        $(divNodes[index]).css("left", "25%");
                        break;
                    case 2:
                        $(divNodes[index]).css("left", "50%");
                        break;
                    case 3:
                        $(divNodes[index]).css("left", "75%");
                        break;
                }
                var temp = 930 - parseInt(index / 4) * 20;
                $(divNodes[index]).css("bottom", temp + 'px');
            }
        }

        //set Date formate for question NO.7
        $.each($('h5'), function (key, value) {
            if($(value).text()[0] == 7){
                $(value).next().next().datepicker({closeText: "Close"}).attr('readonly','readonly');
            }
        });
    }

    function checkComplete() {
        if (surveyObject.resStatus == "completed") {
            $("input[value=Complete]").hide();
            $("#saveSurvey").hide();
            $("#btnExport").show();
            $("input[type!=button][data-bind]").attr("disabled", "disabled");
            $("select[data-bind]").attr("disabled", "disabled");
            $("textarea[data-bind]").attr("disabled", "disabled");
        } else {
            $("#btnExport").hide();
        }
    }

    function backToSelectBinding() {
        $("#backToSelect").click(function () {
            history.back();
        });
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

    function surveyChange() {
        surveyObject.survey.onValueChanged.add(function (sender, options) {
            surveyObject.options = options;
        });
    };

    function surveySavebinding() {
        $("#saveSurvey").click(function () {
            surveyObject.answerData = surveyObject.survey.data;

            var sendData = {
                suGuid: surveyObject.surveyID,
                surveyRes: surveyObject.answerData,
                oGuid: surveyObject.oGuid,
                resStatus: "in processing"
            };

            $.ajaxEx({
                url: __CERBERUS__.APIRoot + "/survey/updateSurveyObj",
                method: "post",
                data: sendData,
                success: function (data) {
                    alert("your survey saved!");
                },
                error: function (data) {
                    console.log(data);
                }
            })
        });
        $("#btnDownloadPDF").click(function () {
            window.open(__CERBERUS__.APIRoot + '/survey/downloadPdf/' + window.currentLanguage + '/' + surveyObject.surveyID + "/" + barCodeText);
        })
    }

    /**
     * JsBarcode generate
     */
    function generateBarcode() {
        JsBarcode("#barcode").options({font: "OCR-B"}).EAN13("1234567890128", {fontSize: 18, textMargin: 0})
            .render();
    }

    function getRandomInt(min, max) {
        min = Math.ceil(min);
        max = Math.floor(max);
        return Math.floor(Math.random() * (max - min)) + min;
    }

    window.currentLanguage = "en";
    function i18n() {
        updateText();
        $("button[type='button']").on('click', function () {
            var language = $(this).attr("language")
            if (language != undefined)
                window.currentLanguage = language;
            updateText();
        });
    }

    function updateText() {
        var i18n = $.i18n();
        i18n.locale = window.currentLanguage;
        i18n.load(''+__CERBERUS__.APIRoot+'/js/survey/i18n/survey-' + i18n.locale + '-' + surveyObject.surveyID + '.json', i18n.locale).done(function() {

            $("span.data-bind").each(function(key, value) {
                var data = $(value).attr("key");
                $(value).html($.i18n(data));
            });
        });
    }

    function exportExcel() {
        $("#btnExport").click(function () {

            surveyObject.surveyID = $("#sGuid").val();
            surveyObject.oGuid = $("#oGuid").val();
            window.open(__CERBERUS__.APIRoot + "/survey/exportExcel?oGuid=" + surveyObject.oGuid + "&uuid=" + surveyObject.surveyID+"&language="+window.currentLanguage);

        });
    }

    initial();
    surveySavebinding();
    backToSelectBinding();
    generateBarcode();
    exportExcel();
    i18n();
})
