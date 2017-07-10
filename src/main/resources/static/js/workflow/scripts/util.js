/**

loading
**/
// var loadingTime=1000;
//
// document.write("<div id='loading-div' style='position:absolute;z-index:2147483647;top:0;width:100%;height:100%;background-color:rgba(192,192,192,0.95)'><img style='top:45%;left:45%;height:10%;position:absolute' src='/faast_client_components/images/loading1.gif' ></div>");
//
//
// var hideLoading=function(fn){
//
// $("#loading-div").fadeTo(loadingTime,0,function(){
//     $("#loading-div").hide();
//     if(fn!=undefined)
//     fn();
// });
//
// }
//
// var showLoading=function(fn){
//     $("#loading-div").show().fadeTo(0,0.95,function(){
//      if(fn!=undefined)
//         fn();
//     });
// }
//
// showLoading();
//
// $(window).load(function () {
// hideLoading();
// });



/**/
JSON.Clone = function(obj) {
    return JSON.parse(JSON.stringify(obj));
}
