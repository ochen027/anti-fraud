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
    {language: 'english', locate: 'en'},
    {language: 'chinese', locate: 'cn'},
    {language: 'spanish', locate: 'es'}
  ];

  var surveyIdForDateFormats = [
    "883fa780-6b44-11e6-8785-3d9278322och",
    "883fa780-6b44-11e6-8785-3d927832dwmt",
    "883fa780-6b44-11e6-8785-3d927832d988",
    "883fa780-6b44-11e6-8785-3d927832d987",
    "883fa780-6b44-11e6-8785-3d9278325555"
  ]
  var barCodeText = getRandomInt(100000000000, 999999999999);

  /**
   *
   * @param survey
   */
  function sendDataToServer(survey) {
    var resultAsString = JSON.stringify(survey.data);

    var vendor = $("#currentVendorId").val();

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
        $.each(item, function (key, value) {
          if (typeof(value) === 'object') {
            flag = false;
            // if (value.level && (value.level.indexOf('_score_') >= 0) && parseInt(value.level)) {
            //     score += parseInt(value.level)
            // }
            if (value.answer && (value.answer.indexOf('_score_') >= 0) && parseInt(value.answer)) {
              score += parseInt(value.answer)
            }
          } else if (value.indexOf('_score_max') >= 0) {
            flag = true;
          } else {
            if (parseInt(value) && (value.indexOf('_score_') >= 0)) {
              score += parseInt(value);
            }
          }
        });
        if (flag) {
          var max = item.map(function (x) {
            return parseInt(x)
          }).reduce(function (x, y) {
            return x >= y ? x : y;
          });
          score += max;
        }
      } else {
        if ((item.indexOf('_score_') >= 0) && parseInt(item)) {
          score += parseInt(item)
        }
      }
    });

    $.ajaxEx({
      url: __CERBERUS__.APIRoot + "/survey/updateSurveyStatus",
      method: "post",
      data: sendData,
      success: function (data) {
        $("#saveSurvey").hide();
        $("#btnExport").show();
        $.ajaxEx({
          url: __CERBERUS__.APIRoot + "/vendor-survey/updateScore",
          data: {
            score: score,
            vendor: vendor
          },
          method: 'post',
          success: function (res) {
            if (res.code == 0) {
              $("#div-message").html('Completed! Thank you for your participation.');
              $("#messageDia").modal('show');
            } else {
              console.error(res);
            }
          },
          error: function (err) {
            console.error(err);
          }
        });
      },
      error: function (data) {
        console.log(data);
      }
    });

    updateCompany();
    // alert(resultAsString); //send Ajax request to your web server.
  }

  function updateCompany() {
    var arrUrl = document.location.toString().split("/");
    var para = arrUrl[arrUrl.length - 1];
    $.ajaxEx({
      url: __CERBERUS__.APIRoot + "/vendor/updateCompany",
      data: {
        "v_guid": para,
        "supplierCompanyName": $("#supplierCompanyName").val(),
        "customerCompanyName": $("#customerCompanyName").val()
      },
      method: 'post',
      success: function (res) {
        if (res.code != 0) {
          console.log(res);
        }
      },
      error: function (err) {
        console.log(err);
      }
    });
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

    $("#btnExport").hide();

    surveyObject.surveyID = $("#sGuid").val();
    showChangeLanguageButton(surveyObject.surveyID, countryList);
    surveyObject.oGuid = $("#oGuid").val();
    $.ajaxEx({
      url: __CERBERUS__.APIRoot + "/survey/getSurveyById",
      method: "get",
      data: {uuid: surveyObject.surveyID},
      success: function (data) {
        if (!(data.nType == "ftt")) {
          $("#supplierCompanyName").parent().parent().hide();
          $("#customerCompanyName").parent().parent().hide();
        } else {
          setCompanyName();
        }


        surveyObject.questionData = StringToBooleanTransform(data.survey);
        surveyObject.survey = new Survey.Survey(surveyObject.questionData, "surveyArea");
        setOriginText();
        UnescapeTitle();
        surveyChange();
        surveyObject.survey.onCurrentPageChanged.add(UnescapeTitle);
        surveyObject.survey.onCurrentPageChanged.add(checkComplete);

        surveyObject.survey.onComplete.add(sendDataToServer);
        initialAnswer(data);
      },
      error: function (data) {
        console.log(data);
      }
    });


  }

  function showChangeLanguageButton(surveyID, countryList) {
    $.each(countryList, function (key, item) {
      $('#flagArea').append('<button id="' + item.language + '-button" type="button" class="i18n" style="margin-right: 8px;" language="' + item.locate + '"></button>');
      $.getJSON('' + __CERBERUS__.APIRoot + '/js/survey/i18n/survey-' + item.locate + '-' + surveyID + '.json').done(function (data) {
      }).fail(function (data) {
        $('#' + item.language + '-button').css({'display': 'none'});
      });
    });
  }

  function initialAnswer(survey) {

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

          if (survey.name === 'wmt' && !data.data.data.surveyRes) {
            surveyObject.survey.data = {
              ques1: "0_score_No",
              ques2: "0_score_No",
              ques3: "0_score_No",
              ques4: "0_score_No",
              ques5: "0_score_No",
              ques6: "0_score_No",
              ques7: "0_score_No",
              ques8: "0_score_No",
              ques9: "0_score_No",
              ques10: "0_score_No",
              ques11: "0_score_No",
              ques12: "0_score_No",
              ques13: "0_score_No",
              ques14: "0_score_No",
              ques15: "1_score_Yes",
              ques16: "0_score_No",
              ques17: "1_score_Yes",
              ques18: "0_score_No",
            };
          } else {
            surveyObject.survey.data = data.data.data.surveyRes;

            surveyObject.resStatus = data.data.data.resStatus;
            checkComplete();
            $(".i18n[language='" + currentLanguage + "']").trigger("click");
          }
        }
        renderTextare();
      },
      error: function (data) {
        console.log(data);
      }
    });

    $('#surveyArea').on('click', 'input', function () {
      $(".i18n[language='" + currentLanguage + "']").trigger("click");
    });

    $('input[type=button]').on('click', function () {
      setOriginText();
      $(".i18n[language='" + currentLanguage + "']").trigger("click");
      renderTextare();

      //set Date formate for question NO.7
      for (key in surveyIdForDateFormats) {
        if ({}.hasOwnProperty.call(surveyIdForDateFormats, key)) {
          if (surveyObject.surveyID == surveyIdForDateFormats[key]) {
            $.each($('h5'), function (key, value) {
              if ($(value).text()[0] == "7") {
                $(value).next().next().datepicker({closeText: "Close"}).attr('readonly', 'readonly');
              }
            });
          }
        }
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
        url: __CERBERUS__.APIRoot + "/survey/updateSurveyStatus",
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
    JsBarcode("#barcode").options({font: "OCR-B"}).EAN13(barCodeText, {fontSize: 18, textMargin: 0})
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
    i18n.load('' + __CERBERUS__.APIRoot + '/js/survey/i18n/survey-' + i18n.locale + '-' + surveyObject.surveyID + '.json', i18n.locale).done(function () {

      $("span[data-bind]").each(function (key, value) {
        var data = $(value).attr('origin-data');
        value.innerText = $.i18n(data);
      });
      $("select[data-bind]").children().each(function (key, value) {
        var data = $(value).attr('origin-data');
        value.innerText = $.i18n(data);
      });
      $("h4[data-bind]").each(function (key, value) {
        var data = $(value).attr('origin-data');
        value.innerText = $.i18n(data);
      });
      $("h5[data-bind]").each(function (key, value) {
        var data = $(value).attr('origin-data');
        var r = /^(\d)*\s*\.*\s*(\*\s*)?/;
        var temp = data.match(r);
        if (temp) {
          value.innerText = data.match(r)[0] + $.i18n(data.replace(r, ''));
        } else {
          value.innerText = $.i18n(data.replace(r, ''));
        }
      });


    });
  }

  function setOriginText() {
    $("span[data-bind]").each(function (key, value) {
      var data = value.innerText;
      $(value).attr('origin-data', data)
    });
    $("select[data-bind]").children().each(function (key, value) {
      var data = value.innerText;
      $(value).attr('origin-data', data)
    });
    $("h5[data-bind]").each(function (key, value) {
      var data = value.innerText;
      $(value).attr('origin-data', data)
    });
    $("h4[data-bind]").each(function (key, value) {
      var data = value.innerText;
      $(value).attr('origin-data', data)
    });
  }

  function exportExcel() {
    $("#btnExport").click(function () {

      surveyObject.surveyID = $("#sGuid").val();
      surveyObject.oGuid = $("#oGuid").val();
      var vendorName = $("#vname").val();
      window.open(__CERBERUS__.APIRoot + "/survey/exportExcel?oGuid=" + surveyObject.oGuid + "&uuid=" + surveyObject.surveyID + "&language=" + window.currentLanguage + "&vendorName=" + vendorName);

    });
  }

  function showVendorId() {
    var url = document.location.toString();
    var arrUrl = url.split("/");
    var para = arrUrl[arrUrl.length - 1];
    $('#vendorId').html('<b>' + __CERBERUS__.vendorTag + ' ID:</b> ' + para);
  }

  function renderTextare() {
    $('.data-bind[key="Contact address"]').parent().next().next().attr('placeholder', 'Sample ' + __CERBERUS__.vendorTag + ' LLC. Level 6, Building 2, Lane 2889, Jinke Road, Pudong New District, Shanghai P.R.C.');

    var $q = $('.data-bind[key="Full legal name (in English and in local language, if applicable)"]').parent();
    var isDisabled = $q.next().next().prop("disabled");

    if (isDisabled != true) {
      $q.next().next().val($("#vname").val());
    }
  }

  function setCompanyName() {

    var arrUrl = document.location.toString().split("/");
    var vGuid = arrUrl[arrUrl.length - 1];

    $.ajaxEx({
      url: __CERBERUS__.APIRoot + "/vendor/getCompany",
      data: {"vguid": vGuid},
      dataType: 'json',
      method: "get",
      success: function (res) {
        if (res.code === 0) {
          var data = res.data;
          if (data.length > 0) {
            $("#supplierCompanyName").html(data[0].c_supplier_company_name);
            $("#customerCompanyName").html(data[0].c_customer_company_name);
          } else {
            console.log("can't find company");
          }
        } else {
          console.log("get company error");
        }
      },
      error: function (data) {
        console.log(data);
      }
    });

  }

  initial();
  surveySavebinding();
  backToSelectBinding();
  generateBarcode();
  exportExcel();
  i18n();
  showVendorId();
})
