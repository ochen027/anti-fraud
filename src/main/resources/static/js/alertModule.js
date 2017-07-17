/**
 * Created by whuang072 on 7/3/2017.
 */




app.controller('MyAlertCtrl', function ($scope, $http, $location, $state) {
    console.log("/alert/myalert");
    $http.get("/alert/myalert")
        .then(function(result){
            console.log(result);
            $scope.result = result.data;
        })


});


app.controller('AvailableAlertCtrl', function ($scope, $http, $location, $state) {

});

app.controller('SuppressedAlertCtrl', function ($scope, $http, $location, $state) {

});

app.controller('ClosedAlertCtrl', function ($scope, $http, $location, $state) {

});

app.controller('MyAlertInfoCtrl', function ($scope, $http, $location, $state) {
    console.log("myalertinfoctrl");
    $(document).ready(function() {
        $(".dropdown-toggle").bind("click", function(event){
            $(event.currentTarget).parent().toggleClass("dropup");
            $(event.currentTarget).parent().find(".customer-summary").length ?  $(event.currentTarget).parent().find(".customer-summary").toggle() : $(event.currentTarget).parent().siblings(".sub-block").toggle();

        })

    })
   $http.get("/alert/myAlertInfo/12345")
        .then(function(result){
            console.log(result);
            $scope.summary = result.data.summary;
            $scope.individual = result.data.individual;
            $scope.corporate = result.data.corporate;
            $scope.legalRepresentative = result.data.legalRepresentative;
            $scope.records = result.data.tableRecords;
        })

});

app.controller('AvailableAlertInfoCtrl', function ($scope, $http, $location, $state) {

});

app.controller('SuppressedAlertInfoCtrl', function ($scope, $http, $location, $state) {

});

app.controller('ClosedAlertInfoCtrl', function ($scope, $http, $location, $state) {

});

