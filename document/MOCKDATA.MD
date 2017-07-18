#MockData

## Alerts
```$javascript



```


## Alert

```$javascript

AlertSchema={
    alertId: "String",
    AlertName:"String",
    TransactionsId:"id1,id2",
    CustomerId:"String",
    ScenarioId:"String",
    DocumentID:"id1,id2",
    Comments:"id1,id2",
    BusinessDay:"String",
    Total:"Double",
    CreatedBy:"String",
    CreatedDate:"date",
    Description:"String"
}

```


```

AlertAPI={
    alertId: "String",
    AlertName:"String",
    Transactions:["obj","obj"],
    Customer:"Obj",
    Scenario:"Obj",
    Document:["obj","obj"],//排序
    Comments:["obj","obj"],//排序
    BusinessDay:"String",
    Total:"Double",
    CreatedBy:"String",
    CreatedDate:"date",
    Description:"String"
}

```



````

AlertsAPI=[AlertAPI,AlertAPI];

```