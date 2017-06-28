#project structure of AML POC

## overall 
**DB: postgresql**  
**Back-end: nodejs,express,sequlize**  
**Front-end: angularjs, bootstrap**  


The pros of this structure is most of the faast component could be reused 
in this project directly. In high level of the structure, there is no 
difference from faast project. All the resource from faast project or 
familiar with the nodejs, could work on AML project.

Current project structure has a little difference from Faast platform, all the
component has been modularized to small folder, each folder can be developed
 without running whole project.

For the front-end part, angularJS has been chose as the framework to manage
single page application. As all the api could be reused and published as 
REST API, the communication will based on it.

## detail in AML project structure
The source code and standard express structure has been redesigned and redefined
as key file is just two files: "app.js" and "cfg/default.js", which stands for the
entrance and configure file, respectively.

The "application" folder at least has one sub folder "webApp" which contains the 
router of web pages. The folder also has the customer function for this application.
The "components" folder contains all the components could be reused in another projects.

The "common" folder provided resource being reused in any component such as :
DB connection pool, express "app" object or mongodb entity if need. The 
"moduleLoader.js" defined the workflow of loading each component. But basically,
loose coupling and modularized components are the key design of current structure.

## plan B (advanced version)
**DB: postgresql or mysql, mongodb**  
**Back-end: java, spring boot, spring mvc , hibernate, JPA**  
**Front-end: reactJS,Redux**     

Plan B version is just for the real big big project, when we need to 
make this project for real user, because it would cost lots of time on 
developing backend part. For future developing and thinking in this structure.

FAQ:

1. Why use both nosql database and Relational database?  
Nosql DB has benefit in selecting and storing unstructured data. For Data 
like: transaction, invoice, logs, NoSQL is the better choice. But for data
containing relations as:user, role, Account,address, Relational database is 
better choice for searching.

2. Why java not nodejs?  
Actually AML is kind of heavy logic based application and the "business logic level"
the key part of this application. The feature of Node js is single thread and high
performance of IO, but it would be pretty slow in logic part and memory management.
Java Spring boot is kind of framework focus on business logic. It is clear java is 
the better choice.

3. Why use ReactJS?  
Nowadays, The most stable front-end framework is clearly ReactJS. The speed of developing
web application is little bit slow than Angularjs. However, for maintenance and modularize
component, ReactJS is the best solution. For now , we just have 3 choice for UI part: vue, React, Angular.
Vue is too new and not ready for real application. AngularJS is good framework for small and application.



*--by Wong*




