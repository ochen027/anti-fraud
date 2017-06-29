# project structure version 2


## Structure
**DB:Hadoop Hbase, mysql**  
**Back-end: java, spring boot, spring mvc , hibernate, JPA**  
**Front-end: angularjs , bootstrap**  

As we know AML system need to process lots of data every day. In order to support big data requirement, Hadoop is a good choice. We have several reasons to choose Hadoop as the DB side. First of all, the functionality of “Hbase” including: storing, searching, updating and deleting data. It is also a type of NoSQL database, which means the speed of selecting data is amazing comparing with RDBMS. Moreover, Hadoop hive could do ETL task, such as “import data”, “clear dirty data” and “support SQL like language” in Hbase. Since analyze is  one of the major function point for AML. Map-reduce as the key feature in Hadoop toolkit could analysis transactions and alerts. Besides, HDFS is a java-based file system and this is also one of the best choice for document engine.


## Rule engine

Drools: configurable Rule engine.
Link: https://www.drools.org/


There are lots of rule engine in java area, such as: easy-rule, openrules. However, these two rule engines is designed to process static condition programmed by java code. Drool is developed by JBOSS open source group and it loads java like configuration file in resource folder. So it should be possible to generate scenario by java code and load to this rule engine to make it dynamically.

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