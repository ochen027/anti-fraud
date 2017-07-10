/**
 * Created by cchen627 on 5/4/2016.
 */
app.factory('draw2dService', [function () {
    var  self={
        flowPoint:function(obj){
             var j= {
                 uid: obj.id,
                 type: obj.type, //obj.userData.type or obj.type
                 name: obj.userData.name,
                 print:obj.userData.name+"("+obj.userData.customerID+")",
                 flowid: obj.userData.customerID,
                 api: '',
                 events: [],
                 description: '',
                 attributes: {
                     x: obj.x, y: obj.y, height: obj.height, width:obj.width, color:obj.color, shape:obj.userData.type
                 }
             }
            return j
        },
        flowEvent:function(obj){
            var j={
                    uid:obj.id,//obj.labels.type
                    type:obj.type,
                    name: obj.userData.name,
                    print: obj.labels.length>0?obj.labels[0].labelData.text:"",
                    flowid: obj.userData.customerID,
                    api:'',
                    endpoint:obj.target.node,
                    description:'',
            }
            return j;

        },
        draw2dToModel:function(draw2dObj){
            var flowEvents=[];
            var tempEvents={};
            var tempPoints={};
            var staticMapping={
                "WorkflowShape.WorkflowStart":1,
                "WorkflowShape.WorkflowEnd":1,
                "WorkflowShape.WorkflowProcess":1,
                "WorkflowShape.Connection":2,
            }
            for (var i in draw2dObj){
                var o=draw2dObj[i];
                if(staticMapping[o.type]===1){
                    tempPoints[o.id]=self.flowPoint(o);
                }else{
                    tempEvents[o.id]=o;
                }
            }


            for(var key in tempEvents){
                 var o=tempEvents[key];
                flowEvents.push(self.flowEvent(o));
                tempPoints[o.source.node].events.push(o.id);
            }

            var flowPoints= Object.keys(tempPoints).map(function(k) { return tempPoints[k] });

            return {"flowEvents":flowEvents,"flowPoints":flowPoints}

        }

    }

    return {
        draw2dToModel:self.draw2dToModel
    };

}]);