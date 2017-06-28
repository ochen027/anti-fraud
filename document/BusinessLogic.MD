#Business Logic of AML POC



### DataInput (From csv dat file, running batch in backend)

solution A: In Demo part ETL tool or sql script being used to insert data.

Solution B: In real case, A background batch function will be implemented.
CSV or DAT file would be put into a folder and after that application would
scan this folder for importing.

### scenario management ---> (rule engine, generate alert from what kind of rule it matched)  

operate date

As this version is based on Nodejs. There are some rule engine:

a). [node-rules](https://github.com/mithunsatheesh/node-rules): 

example code: 

``` javascript

var RuleEngine = require('node-rules');

//define the rules
var rules = [{
	"condition": function(R) {
		R.when(this && (this.transactionTotal < 500));
	},
	"consequence": function(R) {
		this.result = false;
		R.stop();
	}
}];

//sample fact to run the rules on	
var fact = {
    "name":"user4",
    "application":"MOB2",
    "transactionTotal":400,
    "cardType":"Credit Card",
};

//initialize the rule engine
var R = new RuleEngine(rules);

//Now pass the fact on to the rule engine for results
R.execute(fact,function(result){ 

	if(result.result) 
		console.log("Payment Accepted"); 
	else 
		console.log("Payment Rejected");
	
});
```


b). [nools](https://github.com/doug-martin/nools): 

example code:

``` js
var nools = require("nools");

var Message = function (message) {
    this.text = message;
};

var flow = nools.flow("Hello World", function (flow) {

    //find any message that start with hello
    flow.rule("Hello", [Message, "m", "m.text =~ /^hello\\sworld$/"], function (facts) {
        facts.m.text = facts.m.text + " goodbye";
        this.modify(facts.m);
    });

    //find all messages then end in goodbye
    flow.rule("Goodbye", [Message, "m", "m.text =~ /.*goodbye$/"], function (facts) {
        console.log(facts.m.text);
    });
});
```



### case management ---->workflow ,user, role,case pool 

1. configurable workflow
2. Alert, Case, Case pool module developing,document, comments, export to excel.
3. user, user group, role developing

### other
1. business date 
2. i18n
3. log engine: everything has log.
