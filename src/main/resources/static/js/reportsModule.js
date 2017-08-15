app.controller('ReportsCtrl', function ($scope, $http, $location, $state, $timeout) {

    $scope.format = 'yyyy-MM-dd';

    $scope.selectType = [
        {key: "daily", label: "daily", dateOptions: {datepickerMode: "day"},setEndDate:function(){

            $scope.endDate=moment($scope.startDate).format("YYYY-MM-DD");


        }},
        {key: "weekly", label: "weekly", dateOptions: {datepickerMode: "day"},setEndDate:function(){
            $scope.endDate=moment($scope.startDate).add(1, 'weeks').format("YYYY-MM-DD");
        }},
        {
            key: "monthly", label: "monthly", dateOptions: {
            formatYear: 'yyyy',
            startingDay: 1,
            minMode: 'month'
        },setEndDate:function(){
            $scope.endDate=moment($scope.startDate).add(1, 'months').format("YYYY-MM-DD");
        }
        },
        {
            key: "annually", label: "annually", dateOptions: {
            formatYear: 'yyyy',
            startingDay: 1,
            minMode: 'year'
        },setEndDate:function(){
            $scope.endDate=moment($scope.startDate).add(1, 'years').format("YYYY-MM-DD");
        }
        }
    ];

    $scope.reports=[];

    $scope.currentType = $scope.selectType[0];


    $scope.changeDatePicker=function(){

        $scope.currentType.setEndDate();
    }

    $scope.search=function(){

        var send={
            "startDate":moment($scope.startDate).format("YYYY-MM-DD"),
            "endDate": $scope.endDate
        }

        $http.post("/reports/search",send).then(function(res){
            $scope.reports=res.data;
            $scope.reportsDisplay=res.data.slice();
        });
    }


    $scope.export=function(){
        window.open("/reports/export?startDate="
            +moment($scope.startDate).format("YYYY-MM-DD")+"&endDate="+$scope.endDate, "_blank");
    }

    $scope.dateOptions = {
        datepickerMode: "day"
    };

    $scope.popup = {
        opened: false
    };

    $scope.open = function () {
        $scope.popup.opened = true;
    };

    $scope.typeChange = function () {
        $scope.dateOptions = $scope.currentType.dateOptions;
    };

    $scope.options = {
        minDate: new Date(),
        showWeeks: true
    };


});

