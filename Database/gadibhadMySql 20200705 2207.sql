-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.0.45-community-nt


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema testdb
--

CREATE DATABASE IF NOT EXISTS testdb;
USE testdb;

--
-- Definition of table `agent_destination`
--

DROP TABLE IF EXISTS `agent_destination`;
CREATE TABLE `agent_destination` (
  `agent_destination_id` int(10) unsigned NOT NULL auto_increment,
  `agent_destination_name` varchar(45) NOT NULL,
  PRIMARY KEY  (`agent_destination_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `agent_destination`
--

/*!40000 ALTER TABLE `agent_destination` DISABLE KEYS */;
INSERT INTO `agent_destination` (`agent_destination_id`,`agent_destination_name`) VALUES 
 (1,'Gaya Mandi'),
 (2,'Nawada'),
 (3,'Tilaiya'),
 (4,'Hisua');
/*!40000 ALTER TABLE `agent_destination` ENABLE KEYS */;


--
-- Definition of table `agent_details`
--

DROP TABLE IF EXISTS `agent_details`;
CREATE TABLE `agent_details` (
  `agent_id` int(10) unsigned NOT NULL auto_increment,
  `agent_name` varchar(45) default NULL,
  `agent_mark` varchar(45) NOT NULL,
  `agent_address` varchar(45) NOT NULL,
  `agent_mob` varchar(10) default NULL,
  PRIMARY KEY  (`agent_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `agent_details`
--

/*!40000 ALTER TABLE `agent_details` DISABLE KEYS */;
INSERT INTO `agent_details` (`agent_id`,`agent_name`,`agent_mark`,`agent_address`,`agent_mob`) VALUES 
 (1,'Guddu','ZA','Gaya Mandi',''),
 (3,'','ASM','Nawada',''),
 (4,'','TKS','Tilaiya',''),
 (5,'','GTR','Hisua','');
/*!40000 ALTER TABLE `agent_details` ENABLE KEYS */;


--
-- Definition of table `box_details`
--

DROP TABLE IF EXISTS `box_details`;
CREATE TABLE `box_details` (
  `box_id` int(10) unsigned NOT NULL auto_increment,
  `box_name` varchar(45) NOT NULL,
  `total_wt` int(10) unsigned NOT NULL,
  PRIMARY KEY  (`box_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `box_details`
--

/*!40000 ALTER TABLE `box_details` DISABLE KEYS */;
INSERT INTO `box_details` (`box_id`,`box_name`,`total_wt`) VALUES 
 (1,'Carat',10),
 (2,'Carat',20),
 (3,'Box',10),
 (4,'Box',4),
 (5,'Gift',10),
 (6,'Box',5),
 (7,'Carat',5),
 (8,'Box',2),
 (9,'Gift',4);
/*!40000 ALTER TABLE `box_details` ENABLE KEYS */;


--
-- Definition of table `challan_book`
--

DROP TABLE IF EXISTS `challan_book`;
CREATE TABLE `challan_book` (
  `challan_id` int(10) unsigned NOT NULL auto_increment,
  `challan_date` date NOT NULL,
  `truck_no` varchar(45) NOT NULL,
  `driver_name` varchar(45) default NULL,
  `driver_mobile` varchar(45) default NULL,
  `destination_id` int(10) unsigned NOT NULL,
  `crte_tms` datetime NOT NULL,
  `session_id` varchar(45) NOT NULL,
  `source_id` int(10) unsigned NOT NULL,
  PRIMARY KEY  (`challan_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `challan_book`
--

/*!40000 ALTER TABLE `challan_book` DISABLE KEYS */;
INSERT INTO `challan_book` (`challan_id`,`challan_date`,`truck_no`,`driver_name`,`driver_mobile`,`destination_id`,`crte_tms`,`session_id`,`source_id`) VALUES 
 (6,'2020-06-26','MH 20 8987','Rajan ','1234567890',1,'2020-06-28 14:34:51','61EC7C3B0F172220F9C89905447B7574',35),
 (7,'2020-06-25','MH 20 8987','Rajan ','1234567890',1,'2020-06-28 16:37:44','4AE1DDD0E730D98B8E657D194A514DCE',35),
 (8,'2020-06-28','MH 20 8987','Guddu','1234567890',1,'2020-06-28 18:48:33','B01BE26690D2224F65E178D0DBFB52C0',35),
 (9,'2020-07-03','MH 20 8987','Rajan ','1234567890',1,'2020-07-03 14:05:14','6904391A91FD5993E123976FB5102ECD',35),
 (11,'2020-07-03','MH 15 3033','Murshid','8087561312',1,'2020-07-03 23:49:43','69E2985C412AB04F711371E22DD71EA9',35),
 (12,'2020-07-04','BR 2B 3456','Rinku','8087561312',1,'2020-07-04 13:51:00','7AC08BDB754DF0D52265B61092AE9074',35),
 (13,'2020-07-05','MH 20 8987','Rajan ','1234567890',1,'2020-07-05 13:17:09','816546B21078264BBA2CC9182304C774',35);
/*!40000 ALTER TABLE `challan_book` ENABLE KEYS */;


--
-- Definition of table `destination_details`
--

DROP TABLE IF EXISTS `destination_details`;
CREATE TABLE `destination_details` (
  `destination_id` int(10) unsigned NOT NULL auto_increment,
  `destination` varchar(45) NOT NULL,
  PRIMARY KEY  (`destination_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `destination_details`
--

/*!40000 ALTER TABLE `destination_details` DISABLE KEYS */;
INSERT INTO `destination_details` (`destination_id`,`destination`) VALUES 
 (1,'Gaya');
/*!40000 ALTER TABLE `destination_details` ENABLE KEYS */;


--
-- Definition of table `fare_book`
--

DROP TABLE IF EXISTS `fare_book`;
CREATE TABLE `fare_book` (
  `fare_id` int(10) unsigned NOT NULL auto_increment,
  `sub_lot_id` int(10) unsigned NOT NULL,
  `total_fare` double NOT NULL,
  `session_id` varchar(45) NOT NULL,
  `crte_tms` datetime NOT NULL,
  `fare_per_box` double NOT NULL,
  PRIMARY KEY  (`fare_id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `fare_book`
--

/*!40000 ALTER TABLE `fare_book` DISABLE KEYS */;
INSERT INTO `fare_book` (`fare_id`,`sub_lot_id`,`total_fare`,`session_id`,`crte_tms`,`fare_per_box`) VALUES 
 (18,18,3752,'4AE1DDD0E730D98B8E657D194A514DCE','2020-06-28 16:36:00',67),
 (19,19,936,'B01BE26690D2224F65E178D0DBFB52C0','2020-06-28 18:49:09',78),
 (20,20,2211,'B01BE26690D2224F65E178D0DBFB52C0','2020-06-28 18:53:08',67),
 (21,21,4027.5,'72D512BB278E3382ACE20AC7518EC112','2020-06-28 19:07:11',89.5),
 (22,22,660,'D072AAC7CB40DACCE1207462DDD3DD53','2020-06-28 19:22:43',55),
 (23,23,1014,'D072AAC7CB40DACCE1207462DDD3DD53','2020-06-28 19:25:15',78),
 (24,24,1288,'D072AAC7CB40DACCE1207462DDD3DD53','2020-06-28 19:25:54',56),
 (25,25,3043,'FA49CD316F53040D3C69213EDB5A37C5','2020-07-02 00:01:22',89.5),
 (26,26,2574,'5BFDE70F52A1B82C6569992A7942D19B','2020-07-03 14:15:47',78),
 (27,27,4368,'5BFDE70F52A1B82C6569992A7942D19B','2020-07-03 14:17:10',78),
 (28,28,2278,'84A9FADC70619C14320DAB5030B8B9A5','2020-07-03 14:46:38',67),
 (29,29,1474,'84A9FADC70619C14320DAB5030B8B9A5','2020-07-03 14:47:02',67),
 (30,30,804,'84A9FADC70619C14320DAB5030B8B9A5','2020-07-03 14:48:31',67),
 (31,31,1288,'84A9FADC70619C14320DAB5030B8B9A5','2020-07-03 14:49:08',56),
 (32,32,1794,'D3F3C4D309D786FA19AB48534AF9E018','2020-07-04 20:26:52',78),
 (33,33,804,'D3F3C4D309D786FA19AB48534AF9E018','2020-07-04 20:28:18',67),
 (34,34,1848,'D3F3C4D309D786FA19AB48534AF9E018','2020-07-04 20:38:35',56),
 (35,35,1068,'DE45A0D293609690933943BDE2F44D35','2020-07-04 22:33:24',89),
 (36,36,1848,'DE45A0D293609690933943BDE2F44D35','2020-07-04 22:33:47',56),
 (37,37,5865,'DE45A0D293609690933943BDE2F44D35','2020-07-04 22:49:01',85),
 (38,38,672,'816546B21078264BBA2CC9182304C774','2020-07-05 13:17:38',56),
 (39,39,1068,'68CD5FC43E86A47E7545FCE26078CE9A','2020-07-05 16:21:33',89),
 (40,40,3752,'68CD5FC43E86A47E7545FCE26078CE9A','2020-07-05 16:22:11',67);
/*!40000 ALTER TABLE `fare_book` ENABLE KEYS */;


--
-- Definition of table `fare_collection`
--

DROP TABLE IF EXISTS `fare_collection`;
CREATE TABLE `fare_collection` (
  `collection_id` int(10) unsigned NOT NULL auto_increment,
  `fare_id` int(10) unsigned NOT NULL,
  `sub_lot_id` int(10) unsigned NOT NULL,
  `tot_payment` double NOT NULL,
  `pymt_dt` date NOT NULL,
  `debit_amt` double default NULL,
  `ledger_dt` date NOT NULL,
  PRIMARY KEY  USING BTREE (`collection_id`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `fare_collection`
--

/*!40000 ALTER TABLE `fare_collection` DISABLE KEYS */;
INSERT INTO `fare_collection` (`collection_id`,`fare_id`,`sub_lot_id`,`tot_payment`,`pymt_dt`,`debit_amt`,`ledger_dt`) VALUES 
 (27,25,25,43,'2020-07-02',0,'2020-07-01'),
 (28,18,18,1000,'2020-07-02',0,'2020-07-01'),
 (29,18,18,1000,'2020-07-01',0,'2020-07-02'),
 (30,18,18,1700,'2020-07-01',52,'2020-07-01'),
 (31,19,19,936,'2020-07-03',0,'2020-07-01'),
 (32,20,20,500,'2020-07-02',0,'2020-07-02'),
 (33,20,20,1000,'2020-07-03',0,'2020-07-03'),
 (34,20,20,711,'2020-07-02',0,'2020-07-03'),
 (35,21,21,2000,'2020-07-03',0,'2020-07-02'),
 (36,21,21,1000,'2020-07-03',0,'2020-07-04'),
 (37,21,21,1000,'2020-07-03',27.5,'2020-07-02'),
 (38,22,22,660,'2020-07-03',0,'2020-07-02'),
 (39,23,23,1014,'2020-07-03',0,'2020-07-02'),
 (40,24,24,1000,'2020-07-03',0,'2020-07-03'),
 (41,24,24,288,'2020-07-04',0,'2020-07-04');
/*!40000 ALTER TABLE `fare_collection` ENABLE KEYS */;


--
-- Definition of table `fare_rule`
--

DROP TABLE IF EXISTS `fare_rule`;
CREATE TABLE `fare_rule` (
  `fare_id` int(10) unsigned NOT NULL auto_increment,
  `source_id` int(10) unsigned NOT NULL,
  `agent_destination_id` int(10) unsigned NOT NULL,
  `box_id` int(10) unsigned NOT NULL,
  `item_id` int(10) unsigned NOT NULL,
  `fare` double NOT NULL,
  PRIMARY KEY  (`fare_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `fare_rule`
--

/*!40000 ALTER TABLE `fare_rule` DISABLE KEYS */;
INSERT INTO `fare_rule` (`fare_id`,`source_id`,`agent_destination_id`,`box_id`,`item_id`,`fare`) VALUES 
 (1,35,1,1,1,67),
 (2,35,1,2,2,78),
 (3,35,2,2,2,89.5),
 (4,35,3,1,1,89);
/*!40000 ALTER TABLE `fare_rule` ENABLE KEYS */;


--
-- Definition of table `item_details`
--

DROP TABLE IF EXISTS `item_details`;
CREATE TABLE `item_details` (
  `item_id` int(10) unsigned NOT NULL auto_increment,
  `item_name` varchar(45) NOT NULL,
  PRIMARY KEY  (`item_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `item_details`
--

/*!40000 ALTER TABLE `item_details` DISABLE KEYS */;
INSERT INTO `item_details` (`item_id`,`item_name`) VALUES 
 (1,'Grape'),
 (2,'Anar');
/*!40000 ALTER TABLE `item_details` ENABLE KEYS */;


--
-- Definition of table `local_fare`
--

DROP TABLE IF EXISTS `local_fare`;
CREATE TABLE `local_fare` (
  `lfare_id` int(10) unsigned NOT NULL auto_increment,
  `ledger_dt` date NOT NULL,
  `local_driver` varchar(45) NOT NULL,
  `tot_local_fare` double NOT NULL,
  `from_To_Where` varchar(45) NOT NULL,
  `tot_wt` int(10) unsigned NOT NULL,
  `sub_lot_id_array` varchar(100) NOT NULL,
  PRIMARY KEY  (`lfare_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `local_fare`
--

/*!40000 ALTER TABLE `local_fare` DISABLE KEYS */;
INSERT INTO `local_fare` (`lfare_id`,`ledger_dt`,`local_driver`,`tot_local_fare`,`from_To_Where`,`tot_wt`,`sub_lot_id_array`) VALUES 
 (3,'2020-07-04','Mantu',2200,'Gaya-Tilaiya',60,'33');
/*!40000 ALTER TABLE `local_fare` ENABLE KEYS */;


--
-- Definition of table `local_fare_track`
--

DROP TABLE IF EXISTS `local_fare_track`;
CREATE TABLE `local_fare_track` (
  `lfare_track_id` int(10) unsigned NOT NULL auto_increment,
  `sub_lot_id` int(10) unsigned NOT NULL,
  `indicator` varchar(1) NOT NULL,
  PRIMARY KEY  USING BTREE (`lfare_track_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `local_fare_track`
--

/*!40000 ALTER TABLE `local_fare_track` DISABLE KEYS */;
INSERT INTO `local_fare_track` (`lfare_track_id`,`sub_lot_id`,`indicator`) VALUES 
 (1,32,'N'),
 (2,33,'Y'),
 (3,34,'N'),
 (4,35,'N'),
 (5,36,'N'),
 (6,37,'N'),
 (7,38,'N'),
 (8,39,'N'),
 (9,40,'N');
/*!40000 ALTER TABLE `local_fare_track` ENABLE KEYS */;


--
-- Definition of table `lot_book`
--

DROP TABLE IF EXISTS `lot_book`;
CREATE TABLE `lot_book` (
  `lot_id` int(10) unsigned NOT NULL auto_increment,
  `challan_id` int(10) unsigned NOT NULL,
  `trader_id` int(10) unsigned NOT NULL,
  `item_id` int(10) unsigned NOT NULL,
  `total_qty` int(10) unsigned NOT NULL,
  `total_wt` int(10) unsigned NOT NULL,
  `box_id` int(10) unsigned NOT NULL,
  `crte_tms` datetime NOT NULL,
  `session_id` varchar(45) NOT NULL,
  `is_Distributed` varchar(45) default NULL,
  `receiver` varchar(45) default NULL,
  PRIMARY KEY  (`lot_id`)
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `lot_book`
--

/*!40000 ALTER TABLE `lot_book` DISABLE KEYS */;
INSERT INTO `lot_book` (`lot_id`,`challan_id`,`trader_id`,`item_id`,`total_qty`,`total_wt`,`box_id`,`crte_tms`,`session_id`,`is_Distributed`,`receiver`) VALUES 
 (25,6,1,2,56,560,1,'2020-06-28 14:34:51','61EC7C3B0F172220F9C89905447B7574','N',''),
 (27,6,2,2,45,900,2,'2020-06-28 16:16:45','4AE1DDD0E730D98B8E657D194A514DCE','N',NULL),
 (28,7,2,1,25,500,2,'2020-06-28 16:37:44','4AE1DDD0E730D98B8E657D194A514DCE','N',''),
 (29,7,2,2,23,230,3,'2020-06-28 16:37:44','4AE1DDD0E730D98B8E657D194A514DCE','N',''),
 (30,8,2,1,45,450,3,'2020-06-28 18:48:33','B01BE26690D2224F65E178D0DBFB52C0','N',''),
 (31,8,3,2,67,1340,2,'2020-06-28 18:48:33','B01BE26690D2224F65E178D0DBFB52C0','N',''),
 (32,8,1,1,56,1120,2,'2020-06-28 18:48:33','B01BE26690D2224F65E178D0DBFB52C0','N',''),
 (33,8,2,2,56,224,4,'2020-06-28 18:48:33','B01BE26690D2224F65E178D0DBFB52C0','N',''),
 (34,8,1,1,23,115,6,'2020-06-28 18:48:33','B01BE26690D2224F65E178D0DBFB52C0','N',''),
 (35,8,2,1,45,225,7,'2020-06-28 18:48:33','B01BE26690D2224F65E178D0DBFB52C0','N',''),
 (36,8,2,1,69,690,1,'2020-06-28 18:48:33','B01BE26690D2224F65E178D0DBFB52C0','N',''),
 (37,9,1,1,12,120,1,'2020-07-03 14:05:14','6904391A91FD5993E123976FB5102ECD','N',''),
 (38,9,2,2,23,230,1,'2020-07-03 14:05:14','6904391A91FD5993E123976FB5102ECD','N',''),
 (40,11,2,1,12,120,1,'2020-07-03 23:49:43','69E2985C412AB04F711371E22DD71EA9','N',''),
 (41,11,1,1,45,900,2,'2020-07-03 23:49:43','69E2985C412AB04F711371E22DD71EA9','N',''),
 (42,11,2,2,56,1120,2,'2020-07-03 23:49:43','69E2985C412AB04F711371E22DD71EA9','N',''),
 (43,11,3,2,89,890,5,'2020-07-03 23:49:43','69E2985C412AB04F711371E22DD71EA9','N',''),
 (44,12,4,1,123,1230,1,'2020-07-04 13:51:00','7AC08BDB754DF0D52265B61092AE9074','N',''),
 (45,12,2,2,67,1340,2,'2020-07-04 13:51:00','7AC08BDB754DF0D52265B61092AE9074','N',''),
 (46,12,1,1,56,560,3,'2020-07-04 13:51:00','7AC08BDB754DF0D52265B61092AE9074','N',''),
 (47,13,1,1,12,120,1,'2020-07-05 13:17:09','816546B21078264BBA2CC9182304C774','N',''),
 (48,13,2,2,45,900,2,'2020-07-05 13:17:09','816546B21078264BBA2CC9182304C774','N',''),
 (49,13,4,2,56,560,1,'2020-07-05 13:17:09','816546B21078264BBA2CC9182304C774','N','');
/*!40000 ALTER TABLE `lot_book` ENABLE KEYS */;


--
-- Definition of table `source_details`
--

DROP TABLE IF EXISTS `source_details`;
CREATE TABLE `source_details` (
  `source_id` int(10) unsigned NOT NULL auto_increment,
  `source_name` varchar(45) NOT NULL,
  PRIMARY KEY  (`source_id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `source_details`
--

/*!40000 ALTER TABLE `source_details` DISABLE KEYS */;
INSERT INTO `source_details` (`source_id`,`source_name`) VALUES 
 (35,'Nasik');
/*!40000 ALTER TABLE `source_details` ENABLE KEYS */;


--
-- Definition of table `sub_lot_book`
--

DROP TABLE IF EXISTS `sub_lot_book`;
CREATE TABLE `sub_lot_book` (
  `sub_lot_id` int(10) unsigned NOT NULL auto_increment,
  `lot_id` int(10) unsigned NOT NULL,
  `total_qty` int(11) NOT NULL,
  `agent_destination_id` int(11) NOT NULL,
  `crte_tms` datetime NOT NULL,
  `session_id` varchar(45) NOT NULL,
  `agent_id` int(10) unsigned NOT NULL,
  `receiving_date` date NOT NULL,
  `local_driver` varchar(45) default NULL,
  PRIMARY KEY  (`sub_lot_id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sub_lot_book`
--

/*!40000 ALTER TABLE `sub_lot_book` DISABLE KEYS */;
INSERT INTO `sub_lot_book` (`sub_lot_id`,`lot_id`,`total_qty`,`agent_destination_id`,`crte_tms`,`session_id`,`agent_id`,`receiving_date`,`local_driver`) VALUES 
 (18,25,56,1,'2020-06-28 16:36:00','4AE1DDD0E730D98B8E657D194A514DCE',1,'2020-06-29',''),
 (19,30,12,1,'2020-06-28 18:49:09','B01BE26690D2224F65E178D0DBFB52C0',1,'2020-06-28','Rakesh'),
 (20,30,33,2,'2020-06-28 18:53:08','B01BE26690D2224F65E178D0DBFB52C0',3,'2020-06-28','Rakesh'),
 (21,27,45,2,'2020-06-28 19:07:11','72D512BB278E3382ACE20AC7518EC112',3,'2020-06-28','Rakesh'),
 (22,28,12,1,'2020-06-28 19:22:43','D072AAC7CB40DACCE1207462DDD3DD53',1,'2020-06-28',''),
 (23,28,13,1,'2020-06-28 19:25:15','D072AAC7CB40DACCE1207462DDD3DD53',1,'2020-06-28',''),
 (24,29,23,2,'2020-06-28 19:25:54','D072AAC7CB40DACCE1207462DDD3DD53',3,'2020-06-29',''),
 (25,31,34,2,'2020-07-02 00:01:22','FA49CD316F53040D3C69213EDB5A37C5',3,'2020-07-02',''),
 (26,31,33,1,'2020-07-03 14:15:47','5BFDE70F52A1B82C6569992A7942D19B',1,'2020-07-02','Rakesh'),
 (27,32,56,2,'2020-07-03 14:17:10','5BFDE70F52A1B82C6569992A7942D19B',3,'2020-07-03','Rakesh'),
 (28,33,34,1,'2020-07-03 14:46:38','84A9FADC70619C14320DAB5030B8B9A5',1,'2020-07-03',''),
 (29,33,22,1,'2020-07-03 14:47:02','84A9FADC70619C14320DAB5030B8B9A5',1,'2020-07-03',''),
 (30,37,12,1,'2020-07-03 14:48:31','84A9FADC70619C14320DAB5030B8B9A5',1,'2020-07-03','Rakesh'),
 (31,38,23,2,'2020-07-03 14:49:08','84A9FADC70619C14320DAB5030B8B9A5',3,'2020-07-02',''),
 (32,34,23,3,'2020-07-04 20:26:52','D3F3C4D309D786FA19AB48534AF9E018',4,'2020-07-03','Mantu'),
 (33,35,12,3,'2020-07-04 20:28:18','D3F3C4D309D786FA19AB48534AF9E018',4,'2020-07-04','Mantu'),
 (34,35,33,4,'2020-07-04 20:38:35','D3F3C4D309D786FA19AB48534AF9E018',5,'2020-06-30','Rinku'),
 (35,40,12,3,'2020-07-04 22:33:24','DE45A0D293609690933943BDE2F44D35',4,'2020-07-06','Mantu'),
 (36,41,33,4,'2020-07-04 22:33:47','DE45A0D293609690933943BDE2F44D35',5,'2020-07-06','Pappu'),
 (37,36,69,4,'2020-07-04 22:49:01','DE45A0D293609690933943BDE2F44D35',5,'2020-07-01','Pappu'),
 (38,47,12,2,'2020-07-05 13:17:38','816546B21078264BBA2CC9182304C774',3,'2020-07-08','Pappu'),
 (39,41,12,3,'2020-07-05 16:21:33','68CD5FC43E86A47E7545FCE26078CE9A',4,'2020-07-05','Mantu'),
 (40,42,56,4,'2020-07-05 16:22:11','68CD5FC43E86A47E7545FCE26078CE9A',5,'2020-07-05','Mantu');
/*!40000 ALTER TABLE `sub_lot_book` ENABLE KEYS */;


--
-- Definition of table `trader_details`
--

DROP TABLE IF EXISTS `trader_details`;
CREATE TABLE `trader_details` (
  `trader_id` int(10) unsigned NOT NULL auto_increment,
  `trader_name` varchar(45) default NULL,
  `trader_address` varchar(120) NOT NULL,
  `trader_mobile` varchar(15) default NULL,
  `trader_mark` varchar(45) NOT NULL,
  PRIMARY KEY  (`trader_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `trader_details`
--

/*!40000 ALTER TABLE `trader_details` DISABLE KEYS */;
INSERT INTO `trader_details` (`trader_id`,`trader_name`,`trader_address`,`trader_mobile`,`trader_mark`) VALUES 
 (1,'Iftekhar','','1234567890','IFT'),
 (2,'Danish','','1234567890','DGA'),
 (3,'','Patna','','RKS'),
 (4,'Mushtaque','Nasik','','MGR');
/*!40000 ALTER TABLE `trader_details` ENABLE KEYS */;


--
-- Definition of table `truck_ledger`
--

DROP TABLE IF EXISTS `truck_ledger`;
CREATE TABLE `truck_ledger` (
  `truck_no` varchar(20) NOT NULL,
  `truck_start_dt` date NOT NULL,
  `truck_end_dt` date default NULL,
  `source_id` int(10) unsigned NOT NULL,
  `destination_id` int(10) unsigned NOT NULL,
  `adv_fare` double NOT NULL,
  `tot_fare` double default NULL,
  `prize_amt` double default NULL,
  PRIMARY KEY  (`truck_no`,`truck_start_dt`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `truck_ledger`
--

/*!40000 ALTER TABLE `truck_ledger` DISABLE KEYS */;
INSERT INTO `truck_ledger` (`truck_no`,`truck_start_dt`,`truck_end_dt`,`source_id`,`destination_id`,`adv_fare`,`tot_fare`,`prize_amt`) VALUES 
 ('BR 2B 3456','2020-07-04','2020-07-04',35,1,20000,80000,0),
 ('MH 15 3033','2020-07-03','2020-07-04',35,1,20000,115000,5000),
 ('MH 20 8987','2020-06-25','2020-07-04',35,1,30000,125000,5000),
 ('MH 20 8987','2020-06-26','2020-07-02',35,1,30000,100000,5000),
 ('MH 20 8987','2020-06-28','2020-07-04',35,1,30000,50000,0),
 ('MH 20 8987','2020-07-03','2020-07-03',35,1,30000,100000,5000),
 ('MH 20 8987','2020-07-05','2020-07-08',35,1,20000,120000,0);
/*!40000 ALTER TABLE `truck_ledger` ENABLE KEYS */;


--
-- Definition of table `truck_pymt`
--

DROP TABLE IF EXISTS `truck_pymt`;
CREATE TABLE `truck_pymt` (
  `pymt_id` int(10) unsigned NOT NULL auto_increment,
  `truck_no` varchar(20) NOT NULL,
  `t_start_dt` date NOT NULL,
  `pymt_amt` double NOT NULL,
  `pymt_dt` date NOT NULL,
  `ledger_dt` date NOT NULL,
  PRIMARY KEY  (`pymt_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `truck_pymt`
--

/*!40000 ALTER TABLE `truck_pymt` DISABLE KEYS */;
INSERT INTO `truck_pymt` (`pymt_id`,`truck_no`,`t_start_dt`,`pymt_amt`,`pymt_dt`,`ledger_dt`) VALUES 
 (1,'MH 15 3033','2020-07-03',4500,'2020-07-04','2020-07-11'),
 (2,'MH 15 3033','2020-07-03',50000,'2020-07-05','2020-07-11'),
 (3,'MH 15 3033','2020-07-03',45500,'2020-07-10','2020-07-11'),
 (4,'MH 20 8987','2020-06-25',100000,'2020-07-11','2020-07-11'),
 (5,'BR 2B 3456','2020-07-04',45500,'2020-07-03','2020-07-04'),
 (6,'BR 2B 3456','2020-07-04',14500,'2020-07-11','2020-07-04');
/*!40000 ALTER TABLE `truck_pymt` ENABLE KEYS */;


--
-- Definition of table `user_credential`
--

DROP TABLE IF EXISTS `user_credential`;
CREATE TABLE `user_credential` (
  `user_id` int(10) unsigned NOT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(100) default NULL,
  `active` varchar(1) NOT NULL default 'N',
  PRIMARY KEY  USING BTREE (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user_credential`
--

/*!40000 ALTER TABLE `user_credential` DISABLE KEYS */;
INSERT INTO `user_credential` (`user_id`,`username`,`password`,`active`) VALUES 
 (4,'mussu','$2a$10$J2Gd7n6ur.4dNpnh32rAa.uxlGE9HIQlPIfPjPeoEmzpAFN6zDIv.','Y'),
 (5,'rahat','$2a$10$ScOzWvIRuACJTNQWr0bPrOruvJ4HEzecRpHYZO12sxO8tHKaovox2','Y');
/*!40000 ALTER TABLE `user_credential` ENABLE KEYS */;


--
-- Definition of table `user_credential_user_role`
--

DROP TABLE IF EXISTS `user_credential_user_role`;
CREATE TABLE `user_credential_user_role` (
  `username` varchar(40) NOT NULL,
  `user_role_id` int(10) unsigned NOT NULL,
  `roles_user_role_id` varchar(45) NOT NULL,
  PRIMARY KEY  USING BTREE (`username`,`user_role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user_credential_user_role`
--

/*!40000 ALTER TABLE `user_credential_user_role` DISABLE KEYS */;
INSERT INTO `user_credential_user_role` (`username`,`user_role_id`,`roles_user_role_id`) VALUES 
 ('mussu',2,'2'),
 ('mussu',3,'3'),
 ('rahat',1,'1');
/*!40000 ALTER TABLE `user_credential_user_role` ENABLE KEYS */;


--
-- Definition of table `user_details`
--

DROP TABLE IF EXISTS `user_details`;
CREATE TABLE `user_details` (
  `user_id` int(10) unsigned NOT NULL,
  `user_name` varchar(45) NOT NULL,
  `first_name` varchar(45) NOT NULL,
  `last_name` varchar(45) NOT NULL,
  `address` varchar(45) NOT NULL,
  `mobile_no` varchar(45) NOT NULL,
  `email_id` varchar(45) NOT NULL,
  PRIMARY KEY  (`user_id`,`user_name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user_details`
--

/*!40000 ALTER TABLE `user_details` DISABLE KEYS */;
INSERT INTO `user_details` (`user_id`,`user_name`,`first_name`,`last_name`,`address`,`mobile_no`,`email_id`) VALUES 
 (4,'mussu','Musarrat','Jahan','Purnea','8087561312','mussu@gmail.com');
/*!40000 ALTER TABLE `user_details` ENABLE KEYS */;


--
-- Definition of table `user_event`
--

DROP TABLE IF EXISTS `user_event`;
CREATE TABLE `user_event` (
  `event_id` int(10) unsigned NOT NULL auto_increment,
  `lastLogin` datetime NOT NULL,
  `username` varchar(45) NOT NULL,
  `lastLogout` datetime default NULL,
  `session_id` varchar(45) NOT NULL,
  PRIMARY KEY  (`event_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1332 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user_event`
--

/*!40000 ALTER TABLE `user_event` DISABLE KEYS */;
INSERT INTO `user_event` (`event_id`,`lastLogin`,`username`,`lastLogout`,`session_id`) VALUES 
 (1248,'2020-06-28 14:33:59','mussu',NULL,'61EC7C3B0F172220F9C89905447B7574'),
 (1249,'2020-06-28 15:43:45','mussu',NULL,'4AE1DDD0E730D98B8E657D194A514DCE'),
 (1250,'2020-06-28 17:52:13','mussu',NULL,'DAEF54AC0573611259EB3331450B27E1'),
 (1251,'2020-06-28 17:59:15','mussu',NULL,'B5E1D91C6F108279E2E6D3392FF5EE4A'),
 (1252,'2020-06-28 18:06:29','mussu',NULL,'B53305196222D87FD68032D35233E1F3'),
 (1253,'2020-06-28 18:13:34','mussu',NULL,'62B7DCFA8DABDED2B97F8AA0CFBB5FF3'),
 (1254,'2020-06-28 18:36:19','mussu',NULL,'A7D84B6E794BF90A1F1EAAF8A8C6160A'),
 (1255,'2020-06-28 18:45:41','mussu',NULL,'B01BE26690D2224F65E178D0DBFB52C0'),
 (1256,'2020-06-28 19:01:58','mussu',NULL,'72D512BB278E3382ACE20AC7518EC112'),
 (1257,'2020-06-28 19:21:15','mussu',NULL,'D072AAC7CB40DACCE1207462DDD3DD53'),
 (1258,'2020-06-28 19:47:57','mussu',NULL,'49076461046081947AD06FCBC7B70536'),
 (1259,'2020-07-01 23:58:38','mussu',NULL,'FA49CD316F53040D3C69213EDB5A37C5'),
 (1260,'2020-07-02 00:24:10','mussu',NULL,'FCE9168E5E18EB6D55F6997BCEB6B49B'),
 (1261,'2020-07-02 00:29:14','mussu',NULL,'C872270C21837DF73C3E133027FBEAB9'),
 (1262,'2020-07-02 00:30:36','mussu',NULL,'83A54ECDB9EA38EDE58E734D104A5343'),
 (1263,'2020-07-02 21:58:55','mussu',NULL,'60A1695B244866F35DA2B6077C3DEB61'),
 (1264,'2020-07-02 22:15:28','mussu',NULL,'FA9A6F438388F69B7C36432DE9662CF1'),
 (1265,'2020-07-02 22:19:38','mussu',NULL,'9ECDC74FEA59D4918241D14D965FCC0E'),
 (1266,'2020-07-02 22:25:04','mussu',NULL,'5D36DE77B617821F92429BA0FCC0A609'),
 (1267,'2020-07-02 23:08:57','mussu',NULL,'50B83D6853A6E6D0DD6A1DD998696D1B'),
 (1268,'2020-07-02 23:34:10','mussu',NULL,'D16341A75ED7789B7FE5DF9739EC0A71'),
 (1269,'2020-07-02 23:35:43','mussu',NULL,'FF51AA101D09936327D041A04B14DF46'),
 (1270,'2020-07-02 23:38:13','mussu',NULL,'67B8BBD80539292F425014A4D76C0075'),
 (1271,'2020-07-03 00:01:32','mussu',NULL,'C939CB1F0EF235086AC7CC4A74429EB3'),
 (1272,'2020-07-03 00:02:53','mussu',NULL,'DB51AF03929AA91C79C04C24E08FFFA2'),
 (1273,'2020-07-03 00:04:19','mussu',NULL,'3AED8BB2D65A3646BB16B04401A20474'),
 (1274,'2020-07-03 00:34:53','mussu',NULL,'7D9C2B5ACE7FAA07C69EE7937213FB1C'),
 (1275,'2020-07-03 01:01:45','mussu',NULL,'97B6AA43FE6872FDFCE76A921CDD494B'),
 (1276,'2020-07-03 13:20:53','mussu',NULL,'A3EB1EEFE4222CCE72006597F0258895'),
 (1277,'2020-07-03 14:03:21','mussu',NULL,'6904391A91FD5993E123976FB5102ECD'),
 (1278,'2020-07-03 14:15:19','mussu',NULL,'5BFDE70F52A1B82C6569992A7942D19B'),
 (1279,'2020-07-03 14:32:58','mussu',NULL,'84A9FADC70619C14320DAB5030B8B9A5'),
 (1280,'2020-07-03 15:56:37','mussu',NULL,'775615176006605E4509BA0551A99D62'),
 (1281,'2020-07-03 17:35:15','mussu',NULL,'F35B26F6F72341BF960AFCC3EB5CAECB'),
 (1282,'2020-07-03 17:42:21','mussu',NULL,'000D68E17D150C9FA84366F5CC03F0C8'),
 (1283,'2020-07-03 17:44:30','mussu',NULL,'48F119355ABF773E5970B42650CCA287'),
 (1284,'2020-07-03 17:59:14','mussu',NULL,'65A7D203AF8F4E8919D8BB529076DCD6'),
 (1285,'2020-07-03 21:03:02','mussu',NULL,'682ADCC023F3206CBE7280B01E043C95'),
 (1286,'2020-07-03 21:23:18','mussu',NULL,'3CF292F731543243DFC8EC11643A1F78'),
 (1287,'2020-07-03 22:53:58','mussu',NULL,'1148E4CB9B1840B36450121E91417BC8'),
 (1288,'2020-07-03 23:33:15','mussu',NULL,'69E2985C412AB04F711371E22DD71EA9'),
 (1289,'2020-07-04 11:21:20','mussu',NULL,'8801B39B4FFCF2025E81A6DA81DB2A24'),
 (1290,'2020-07-04 12:41:44','mussu',NULL,'03C5AE80D4872F13852C6BD27DEF6798'),
 (1291,'2020-07-04 13:18:31','mussu',NULL,'970AC3D526DFC630358CC935E0CB018D'),
 (1292,'2020-07-04 13:43:49','mussu',NULL,'7AC08BDB754DF0D52265B61092AE9074'),
 (1293,'2020-07-04 14:39:17','mussu',NULL,'384B799C45A7FABAF0B9F7BEDFBE72D4'),
 (1294,'2020-07-04 16:11:44','mussu',NULL,'36C8E264E5927FB6B893673AB815DF9A'),
 (1295,'2020-07-04 20:01:20','mussu',NULL,'168178CAE3A28FE9CE70041108D133A1'),
 (1296,'2020-07-04 20:02:38','mussu',NULL,'9ACB9EA41605F83F9BA387AB54EF2A21'),
 (1297,'2020-07-04 20:25:56','mussu',NULL,'D3F3C4D309D786FA19AB48534AF9E018'),
 (1298,'2020-07-04 20:40:07','mussu',NULL,'89D7A36DBE0B40B449760BD2458BBCE7'),
 (1299,'2020-07-04 22:28:23','mussu',NULL,'DE45A0D293609690933943BDE2F44D35'),
 (1300,'2020-07-05 01:10:22','mussu',NULL,'A819B97AD052600EFE8946428C9FCB3C'),
 (1301,'2020-07-05 02:31:53','mussu',NULL,'A09525B744AF094E5AF8B37F1F60CD12'),
 (1302,'2020-07-05 11:42:29','mussu',NULL,'566969FDB3523EAA4A68037C2DE9786A'),
 (1303,'2020-07-05 13:09:45','mussu',NULL,'816546B21078264BBA2CC9182304C774'),
 (1304,'2020-07-05 14:52:04','mussu',NULL,'68CD5FC43E86A47E7545FCE26078CE9A'),
 (1305,'2020-07-05 17:00:50','mussu',NULL,'951685A757BCF62EAD73EAC444D5A9EF'),
 (1306,'2020-07-05 17:03:58','mussu',NULL,'6E6E44F3EB94B4512BD4D4C1C4A41AC4'),
 (1307,'2020-07-05 17:14:37','mussu',NULL,'49CCAF7E3702E8469D4CC0D836040D99'),
 (1308,'2020-07-05 17:17:02','mussu',NULL,'A9C3100301ABD7DF87FE54B7DA9F2D2A'),
 (1309,'2020-07-05 17:19:10','mussu',NULL,'89F0790D4386F9AA8758236FCC284CAA'),
 (1310,'2020-07-05 17:28:31','mussu',NULL,'69133DB9DF5B5B98BE60D99A47B2EF19'),
 (1311,'2020-07-05 17:32:01','mussu',NULL,'D61DBAB284250C7092DC2113AEC23828'),
 (1312,'2020-07-05 20:10:23','mussu',NULL,'B036E47D347656BF7F14C0C9F0685865'),
 (1313,'2020-07-05 20:13:10','mussu',NULL,'0C7F97D475BA68C538A086D462810043'),
 (1314,'2020-07-05 20:27:38','mussu',NULL,'CAEF2ECE23FA9783B11CFEB51A0129DC'),
 (1315,'2020-07-05 20:37:52','mussu',NULL,'7D9C8497ED007C9D92393597240F5FF9'),
 (1316,'2020-07-05 20:41:18','mussu',NULL,'758C811644FC802FCA5339C8A668393A'),
 (1317,'2020-07-05 20:44:10','mussu',NULL,'C811B7C812C5E804ACEE8D9DFE0F4092'),
 (1318,'2020-07-05 20:50:16','mussu',NULL,'D76C00D70DB8AB7BB74EE79AB462821C'),
 (1319,'2020-07-05 20:54:42','mussu',NULL,'4170A28FF6776430A01B41B23BF55BE6'),
 (1320,'2020-07-05 20:58:47','mussu',NULL,'151F8D95E9479C111C6714A6F1CE2E74'),
 (1321,'2020-07-05 21:22:51','mussu',NULL,'561B2E965F89786031ADB98F09E86EBF'),
 (1322,'2020-07-05 21:36:38','mussu',NULL,'620D88349FF27F35677B4E9EE14592D8'),
 (1323,'2020-07-05 21:38:47','mussu',NULL,'9C253CD400ACA9E0224116016541C55C'),
 (1324,'2020-07-05 21:46:57','mussu',NULL,'5F3D0E2A5876706469EA25EDE43F3184'),
 (1325,'2020-07-05 21:50:30','mussu',NULL,'EE956FBB583E84EC47DC8EE3F8EBF8B4'),
 (1326,'2020-07-05 21:53:31','mussu',NULL,'890B6685C36BDCFBEA99A5608AFEBBC6'),
 (1327,'2020-07-05 21:55:18','mussu',NULL,'46D1789E74BCEF8F5885E547B915FD1A'),
 (1328,'2020-07-05 21:58:28','mussu',NULL,'14BAAC7CF945F316EA982605B365CDD7'),
 (1329,'2020-07-05 22:01:45','mussu',NULL,'C53A63508DDF7ECE1437A23F94C8436F'),
 (1330,'2020-07-05 22:30:16','mussu',NULL,'94A7BD64AE39A66AC4F68914882C2DA3'),
 (1331,'2020-07-05 23:11:24','mussu',NULL,'B60E82ACB2683B9FD85B539F9D4EEFA0');
/*!40000 ALTER TABLE `user_event` ENABLE KEYS */;


--
-- Definition of table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `user_role_id` int(10) unsigned NOT NULL auto_increment,
  `username` varchar(45) NOT NULL,
  `role` varchar(45) NOT NULL,
  PRIMARY KEY  (`user_role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user_role`
--

/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` (`user_role_id`,`username`,`role`) VALUES 
 (1,'rahat','USER'),
 (2,'mussu','ADMIN'),
 (3,'mussu','USER');
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
