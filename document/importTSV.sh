#!/bin/bash
export HADOOP_CLASSPATH=`/data/Hadoop/cdh/hbase-0.98.6-hadoop2/bin/hbase mapredcp`
cd /data/Hadoop/cdh
echo $1
path=$1
fileName=${path##*/}
./hadoop-2.5.0-cdh5.3.6/bin/hdfs dfs -put $1  /user/hadoop/tmp/sampleData/tsvImport
./hadoop-2.5.0-cdh5.3.6/bin/yarn jar hbase-0.98.6-hadoop2/lib/hbase-server-0.98.6-hadoop2.jar importtsv -Dimporttsv.columns=$3 $2 /user/hadoop/tmp/sampleData/tsvImport/$fileName
