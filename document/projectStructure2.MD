# project structure version 2


## Structure
**DB:Hadoop Hbase, mysql**  
**Back-end: java, spring boot, spring mvc , hibernate, JPA**  
**Front-end: angularjs , bootstrap**  


Hadoop Hbase is a nosql database, which can support big data requirement. In this project, Hbase can support searching and storing transaction.
and Hadoop hive could do ETL task, such as clear dirty data, using sql to control hbase. Map-reduce in hadoop could analysis transactions and alerts.
Hdfs is a java-based file system, this is also one of the best choice for document engine.



## Rule engine

Drools: configurable Rule engine.
https://www.drools.org/

There are lots of rule engine in java, such as: easy-rule, openrules. 
But these two rule engine could not be configured dynamically. With the research, Drools is the one could support 



## Document engine
HDFS has it's own UI and API for java to manage file systems. As we use hadoop as the tools, it is also the key feature in hadoop system. 


## hadoop part

hdfs:
http://sinl069076:50070/dfshealth.html#tab-overview    
Hdfs is the document management system in Hadoop, this link could view the basic directory structure in Hadoop server.

yarn:
http://sinl069076:8088/cluster  
Overall information of map-reduce and node. 

HBASE:
http://sinl069076:60010/zk.jsp  
UI for Nosql DB