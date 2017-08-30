-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: localhost    Database: aml
-- ------------------------------------------------------
-- Server version	5.7.14-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `scenario`
--

DROP TABLE IF EXISTS `scenario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `scenario` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) DEFAULT NULL,
  `creation_date` datetime DEFAULT NULL,
  `last_update_date` datetime DEFAULT NULL,
  `last_updated_by` varchar(255) DEFAULT NULL,
  `status` bit(1) DEFAULT NULL,
  `scenario_code` varchar(255) DEFAULT NULL,
  `scenario_content` longtext,
  `scneario_desc` varchar(255) DEFAULT NULL,
  `scenario_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `scenario`
--

LOCK TABLES `scenario` WRITE;
/*!40000 ALTER TABLE `scenario` DISABLE KEYS */;
INSERT INTO `scenario` VALUES (1,'sysadmin','2017-08-29 15:59:07','2017-08-29 15:59:07','sysadmin','','C1','package com.pwc.aml.rules.service;\nimport com.pwc.aml.customers.entity.CustomerBase;\nimport com.pwc.aml.common.hbase.HbaseDaoImp;\nimport com.pwc.aml.common.hbase.IHbaseDao;\nimport org.apache.hadoop.hbase.client.HTable;\nimport com.pwc.common.util.FormatUtils;\n\nrule \"Case1\"\n    salience 1\n    when\n        $customer : CustomerBase(totalTransAmt >= 30000 && totalTransCount >=0);\n    then\n        IHbaseDao hBaseDAO = new HbaseDaoImp();\n        HTable table = hBaseDAO.getTable(\"aml:alerts\");\n        String alertId = FormatUtils.GenerateId();\n        hBaseDAO.putData(table, alertId, \"f1\", \"alertName\", \"Scenario 1 Conflict\");\n        hBaseDAO.putData(table, alertId, \"f1\", \"alertContent\", \"Conflict with Transactions Amount > 30000 and in the last 3 days\");\n        hBaseDAO.putData(table, alertId, \"f1\", \"customerId\", $customer.getCustomerId());\n        hBaseDAO.putData(table, alertId, \"f1\", \"accountId\", $customer.getAccountIdArray());\n        hBaseDAO.putData(table, alertId, \"f1\", \"transIdArray\", $customer.getTransIdArray());\n        hBaseDAO.putData(table, alertId, \"f1\", \"scenarioId\", \"1\");\n        hBaseDAO.putData(table, alertId, \"f1\", \"businessDate\", $customer.getBusinessDate());\n        hBaseDAO.putData(table, alertId, \"f1\", \"totalAmt\", $customer.getTotalTransAmt().toString());\n        hBaseDAO.putData(table, alertId, \"f1\", \"createdDate\", $customer.getAlertCreationDate());\n        hBaseDAO.putData(table, alertId, \"f1\", \"alertDesc\", \"Alert Desc\");\n                hBaseDAO.putData(table, alertId, \"f1\", \"accountId\", $customer.getAccountId());\n        $customer.setAlertId(alertId);\nend','Scenario 1 Description','Scenario 1'),(2,'sysadmin','2017-08-29 15:59:07','2017-08-29 15:59:07','sysadmin','','C2','package com.pwc.aml.rules.service;\nimport com.pwc.aml.customers.entity.CustomerBase;\nimport com.pwc.aml.common.hbase.HbaseDaoImp;\nimport com.pwc.aml.common.hbase.IHbaseDao;\nimport org.apache.hadoop.hbase.client.HTable;\nimport com.pwc.common.util.FormatUtils;\n\nrule \"Case2\"\n    salience 1\n    when\n        $customer : CustomerBase(totalTransAmt >= 90000);\n    then\n        IHbaseDao hBaseDAO = new HbaseDaoImp();\n        HTable table = hBaseDAO.getTable(\"aml:alerts\");\n        String alertId = FormatUtils.GenerateId();\n        hBaseDAO.putData(table, alertId, \"f1\", \"alertName\", \"Scenario 2 Conflict\");\n        hBaseDAO.putData(table, alertId, \"f1\", \"alertContent\", \"Conflict with Transactions Amount > 90000 in the recent days and with no any transactions in last long period.\");\n        hBaseDAO.putData(table, alertId, \"f1\", \"customerId\", $customer.getCustomerId());\n        hBaseDAO.putData(table, alertId, \"f1\", \"accountId\", $customer.getAccountIdArray());\n        hBaseDAO.putData(table, alertId, \"f1\", \"transIdArray\", $customer.getTransIdArray());\n        hBaseDAO.putData(table, alertId, \"f1\", \"scenarioId\", \"2\");\n        hBaseDAO.putData(table, alertId, \"f1\", \"businessDate\", $customer.getBusinessDate());\n        hBaseDAO.putData(table, alertId, \"f1\", \"totalAmt\", $customer.getTotalTransAmt().toString());\n        hBaseDAO.putData(table, alertId, \"f1\", \"createdDate\", $customer.getAlertCreationDate());\n        hBaseDAO.putData(table, alertId, \"f1\", \"alertDesc\", \"Alert Desc\");\n                hBaseDAO.putData(table, alertId, \"f1\", \"accountId\", $customer.getAccountId());\n        $customer.setAlertId(alertId);\nend','Scenario 2 Description','Scenario 2');
/*!40000 ALTER TABLE `scenario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-08-29 16:09:58
