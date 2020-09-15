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
-- Definition of table `bank_transfer`
--

DROP TABLE IF EXISTS `bank_transfer`;
CREATE TABLE `bank_transfer` (
  `transfer_id` int(10) unsigned NOT NULL auto_increment,
  `bank_name` varchar(45) NOT NULL,
  `account_holder` varchar(45) NOT NULL,
  `amount` double NOT NULL,
  `refernce_desc` varchar(45) default NULL,
  `ledger_dt` date NOT NULL,
  PRIMARY KEY  (`transfer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `bank_transfer`
--

/*!40000 ALTER TABLE `bank_transfer` DISABLE KEYS */;
INSERT INTO `bank_transfer` (`transfer_id`,`bank_name`,`account_holder`,`amount`,`refernce_desc`,`ledger_dt`) VALUES 
 (54,'Axis','Danish',1000,'Advance','2020-07-25'),
 (55,'ICICI','Rahat',2000,'Bank','2020-07-25');
/*!40000 ALTER TABLE `bank_transfer` ENABLE KEYS */;


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
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `challan_book`
--

/*!40000 ALTER TABLE `challan_book` DISABLE KEYS */;
INSERT INTO `challan_book` (`challan_id`,`challan_date`,`truck_no`,`driver_name`,`driver_mobile`,`destination_id`,`crte_tms`,`session_id`,`source_id`) VALUES 
 (31,'2020-07-26','MH 20 8988','Rajan ','1234567890',1,'2020-07-24 23:51:17','89A8B5402D0D2FB0B41C98112CC66EAA',37),
 (32,'2020-07-24','MH 20 8987','Rajan ','1234567890',1,'2020-07-24 23:52:23','89A8B5402D0D2FB0B41C98112CC66EAA',36),
 (33,'2020-07-25','MH 20 8987','Rajan ','1234567890',1,'2020-07-24 23:53:52','89A8B5402D0D2FB0B41C98112CC66EAA',36),
 (35,'2020-07-24','MH 15 3033','Rajan ','1234567890',1,'2020-07-24 23:54:46','89A8B5402D0D2FB0B41C98112CC66EAA',36),
 (36,'2020-07-26','BR 2B 7890','Rajan ','9876789098',1,'2020-07-25 19:46:57','98C3DE888D699505FBAE7277AF22D74E',36);
/*!40000 ALTER TABLE `challan_book` ENABLE KEYS */;


--
-- Definition of table `daily_ledger_book`
--

DROP TABLE IF EXISTS `daily_ledger_book`;
CREATE TABLE `daily_ledger_book` (
  `ledger_id` int(10) unsigned NOT NULL auto_increment,
  `ledger_dt` date NOT NULL,
  `tot_truck_pymt` double NOT NULL,
  `tot_bnk_trnsfr` double NOT NULL,
  `tot_local_fare` double NOT NULL,
  `tot_mny_withdrwl` double NOT NULL,
  `tot_othr_expns` double NOT NULL,
  `tot_thekedari` double NOT NULL,
  `tot_collections` double NOT NULL,
  `tot_ledger_bal` double NOT NULL,
  PRIMARY KEY  (`ledger_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `daily_ledger_book`
--

/*!40000 ALTER TABLE `daily_ledger_book` DISABLE KEYS */;
INSERT INTO `daily_ledger_book` (`ledger_id`,`ledger_dt`,`tot_truck_pymt`,`tot_bnk_trnsfr`,`tot_local_fare`,`tot_mny_withdrwl`,`tot_othr_expns`,`tot_thekedari`,`tot_collections`,`tot_ledger_bal`) VALUES 
 (1,'2020-07-24',0,0,0,0,0,0,0,47000),
 (7,'2020-07-25',20000,3000,3000,1000,200,0,10427,32227);
/*!40000 ALTER TABLE `daily_ledger_book` ENABLE KEYS */;


--
-- Definition of table `destination_details`
--

DROP TABLE IF EXISTS `destination_details`;
CREATE TABLE `destination_details` (
  `destination_id` int(10) unsigned NOT NULL auto_increment,
  `destination` varchar(45) NOT NULL,
  PRIMARY KEY  (`destination_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `destination_details`
--

/*!40000 ALTER TABLE `destination_details` DISABLE KEYS */;
INSERT INTO `destination_details` (`destination_id`,`destination`) VALUES 
 (1,'Gaya'),
 (2,'Patna');
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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `fare_book`
--

/*!40000 ALTER TABLE `fare_book` DISABLE KEYS */;
INSERT INTO `fare_book` (`fare_id`,`sub_lot_id`,`total_fare`,`session_id`,`crte_tms`,`fare_per_box`) VALUES 
 (1,73,3864,'19443AFAE0FA513D4167D93EA5B216CE','2020-07-26 20:50:10',69),
 (2,74,748,'19443AFAE0FA513D4167D93EA5B216CE','2020-07-26 20:50:35',68);
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
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `fare_collection`
--

/*!40000 ALTER TABLE `fare_collection` DISABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `fare_rule`
--

/*!40000 ALTER TABLE `fare_rule` DISABLE KEYS */;
INSERT INTO `fare_rule` (`fare_id`,`source_id`,`agent_destination_id`,`box_id`,`item_id`,`fare`) VALUES 
 (5,36,1,1,1,65),
 (6,36,2,2,1,69),
 (7,36,2,1,1,60);
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
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `local_fare`
--

/*!40000 ALTER TABLE `local_fare` DISABLE KEYS */;
INSERT INTO `local_fare` (`lfare_id`,`ledger_dt`,`local_driver`,`tot_local_fare`,`from_To_Where`,`tot_wt`,`sub_lot_id_array`) VALUES 
 (29,'2020-07-25','Mantu',1000,'Gaya-Hisua',560,'68'),
 (30,'2020-07-25','Rakesh',2000,'Gaya-Tilaiya,Nawada,Gaya Mandi',1380,'66, 67, 69');
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
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `local_fare_track`
--

/*!40000 ALTER TABLE `local_fare_track` DISABLE KEYS */;
INSERT INTO `local_fare_track` (`lfare_track_id`,`sub_lot_id`,`indicator`) VALUES 
 (30,66,'Y'),
 (31,67,'Y'),
 (32,68,'Y'),
 (33,69,'Y'),
 (34,73,'N'),
 (35,74,'N');
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
) ENGINE=InnoDB AUTO_INCREMENT=90 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `lot_book`
--

/*!40000 ALTER TABLE `lot_book` DISABLE KEYS */;
INSERT INTO `lot_book` (`lot_id`,`challan_id`,`trader_id`,`item_id`,`total_qty`,`total_wt`,`box_id`,`crte_tms`,`session_id`,`is_Distributed`,`receiver`) VALUES 
 (80,32,1,1,23,460,2,'2020-07-24 23:52:23','89A8B5402D0D2FB0B41C98112CC66EAA','N',''),
 (81,32,2,1,40,800,2,'2020-07-24 23:52:23','89A8B5402D0D2FB0B41C98112CC66EAA','N',''),
 (82,32,3,2,56,560,3,'2020-07-24 23:52:23','89A8B5402D0D2FB0B41C98112CC66EAA','N',''),
 (83,33,4,1,23,230,3,'2020-07-24 23:53:52','89A8B5402D0D2FB0B41C98112CC66EAA','N',NULL),
 (85,35,2,1,45,900,2,'2020-07-24 23:54:46','89A8B5402D0D2FB0B41C98112CC66EAA','N',NULL),
 (86,36,2,2,12,240,2,'2020-07-25 19:46:57','98C3DE888D699505FBAE7277AF22D74E','N',''),
 (87,36,4,1,45,450,1,'2020-07-25 19:46:57','98C3DE888D699505FBAE7277AF22D74E','N',''),
 (88,36,5,2,56,1120,2,'2020-07-25 19:46:57','98C3DE888D699505FBAE7277AF22D74E','N',''),
 (89,31,3,1,67,670,1,'2020-07-26 01:07:27','F3D30BCD09E95A5D3399E56F9F785733','N',NULL);
/*!40000 ALTER TABLE `lot_book` ENABLE KEYS */;


--
-- Definition of table `money_withdrawl`
--

DROP TABLE IF EXISTS `money_withdrawl`;
CREATE TABLE `money_withdrawl` (
  `withdrawl_Id` int(10) unsigned NOT NULL auto_increment,
  `withdrawl_By` varchar(45) NOT NULL,
  `withdrawl_Amt` double NOT NULL,
  `withdrawl_Mode` varchar(45) NOT NULL,
  `payment_by` varchar(45) default NULL,
  `ledger_dt` date NOT NULL,
  PRIMARY KEY  USING BTREE (`withdrawl_Id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `money_withdrawl`
--

/*!40000 ALTER TABLE `money_withdrawl` DISABLE KEYS */;
INSERT INTO `money_withdrawl` (`withdrawl_Id`,`withdrawl_By`,`withdrawl_Amt`,`withdrawl_Mode`,`payment_by`,`ledger_dt`) VALUES 
 (10,'Rahat',1000,'CASH','Abid','2020-07-25');
/*!40000 ALTER TABLE `money_withdrawl` ENABLE KEYS */;


--
-- Definition of table `other_expense`
--

DROP TABLE IF EXISTS `other_expense`;
CREATE TABLE `other_expense` (
  `expense_Id` int(10) unsigned NOT NULL auto_increment,
  `expense_Desc` varchar(45) NOT NULL,
  `expense_Amt` double NOT NULL,
  `expense_By` varchar(45) default NULL,
  `ledger_dt` date NOT NULL,
  PRIMARY KEY  USING BTREE (`expense_Id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `other_expense`
--

/*!40000 ALTER TABLE `other_expense` DISABLE KEYS */;
INSERT INTO `other_expense` (`expense_Id`,`expense_Desc`,`expense_Amt`,`expense_By`,`ledger_dt`) VALUES 
 (12,'Nashta',200,'Abid','2020-07-25');
/*!40000 ALTER TABLE `other_expense` ENABLE KEYS */;


--
-- Definition of table `source_details`
--

DROP TABLE IF EXISTS `source_details`;
CREATE TABLE `source_details` (
  `source_id` int(10) unsigned NOT NULL auto_increment,
  `source_name` varchar(45) NOT NULL,
  PRIMARY KEY  (`source_id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `source_details`
--

/*!40000 ALTER TABLE `source_details` DISABLE KEYS */;
INSERT INTO `source_details` (`source_id`,`source_name`) VALUES 
 (36,'Nashik'),
 (37,'Timbhurni');
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
) ENGINE=InnoDB AUTO_INCREMENT=75 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sub_lot_book`
--

/*!40000 ALTER TABLE `sub_lot_book` DISABLE KEYS */;
INSERT INTO `sub_lot_book` (`sub_lot_id`,`lot_id`,`total_qty`,`agent_destination_id`,`crte_tms`,`session_id`,`agent_id`,`receiving_date`,`local_driver`) VALUES 
 (66,80,23,2,'2020-07-25 00:35:58','ADA1F02219A34989A94F358D4CA57402',3,'2020-07-25','Rakesh'),
 (67,81,40,3,'2020-07-25 00:36:24','ADA1F02219A34989A94F358D4CA57402',4,'2020-07-25','Rakesh'),
 (68,82,56,4,'2020-07-25 00:36:44','ADA1F02219A34989A94F358D4CA57402',5,'2020-07-25','Mantu'),
 (69,83,12,1,'2020-07-25 01:26:46','ADA1F02219A34989A94F358D4CA57402',1,'2020-07-25','Rakesh'),
 (70,83,11,1,'2020-07-25 15:40:33','84EFC16DF87FF96F40571F111E1FCEB3',1,'2020-07-26',''),
 (71,85,12,1,'2020-07-25 19:46:02','98C3DE888D699505FBAE7277AF22D74E',1,'2020-07-25',''),
 (72,85,12,1,'2020-07-25 20:06:39','98C3DE888D699505FBAE7277AF22D74E',1,'2020-07-25',''),
 (73,89,56,4,'2020-07-26 20:50:10','19443AFAE0FA513D4167D93EA5B216CE',5,'2020-07-29','Rakesh'),
 (74,89,11,2,'2020-07-26 20:50:35','19443AFAE0FA513D4167D93EA5B216CE',3,'2020-07-29','Rakesh');
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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `trader_details`
--

/*!40000 ALTER TABLE `trader_details` DISABLE KEYS */;
INSERT INTO `trader_details` (`trader_id`,`trader_name`,`trader_address`,`trader_mobile`,`trader_mark`) VALUES 
 (1,'Iftekhar','','1234567890','IFT'),
 (2,'Danish','','1234567890','DGA'),
 (3,'','Patna','','RKS'),
 (4,'Mushtaque','Nasik','','MGR'),
 (5,'','Nagpur','','YZR');
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
 ('BR 2B 7890','2020-07-26',NULL,36,1,25000,0,0),
 ('MH 15 3033','2020-07-24','2020-07-25',36,1,20000,50000,0),
 ('MH 20 8987','2020-07-24','2020-07-31',36,1,30000,90000,0),
 ('MH 20 8987','2020-07-25','2020-07-26',36,1,20000,80000,0),
 ('MH 20 8988','2020-07-26','2020-07-29',37,1,20000,120000,0);
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
) ENGINE=InnoDB AUTO_INCREMENT=63 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `truck_pymt`
--

/*!40000 ALTER TABLE `truck_pymt` DISABLE KEYS */;
INSERT INTO `truck_pymt` (`pymt_id`,`truck_no`,`t_start_dt`,`pymt_amt`,`pymt_dt`,`ledger_dt`) VALUES 
 (62,'MH 15 3033','2020-07-24',20000,'2020-07-25','2020-07-25');
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
) ENGINE=InnoDB AUTO_INCREMENT=1493 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user_event`
--

/*!40000 ALTER TABLE `user_event` DISABLE KEYS */;
INSERT INTO `user_event` (`event_id`,`lastLogin`,`username`,`lastLogout`,`session_id`) VALUES 
 (1402,'2020-07-19 13:59:59','mussu',NULL,'DC4ED0DA848F554B4ABB2DABAAF30493'),
 (1403,'2020-07-19 14:52:56','mussu',NULL,'2519F87AA58E70BBF741ACD5BEFA1405'),
 (1404,'2020-07-19 15:32:48','mussu',NULL,'D8BBC635E1788C6A6B346D0F96FEB97D'),
 (1405,'2020-07-19 16:06:37','mussu',NULL,'9BFA2CCBD70F39DFFBBDDE6CE5DEC9B2'),
 (1406,'2020-07-19 18:37:39','mussu',NULL,'EB44F4E6CA9829BF9A90E9F21B0E05D0'),
 (1407,'2020-07-19 19:20:42','mussu',NULL,'2D2050177C398F0C8C6BC36543263A79'),
 (1408,'2020-07-19 20:26:27','mussu',NULL,'9534836B2B01217B30221331797E16F3'),
 (1409,'2020-07-19 21:29:34','mussu',NULL,'0D6EBA3AE88D51C26873B81E054CFF5C'),
 (1410,'2020-07-19 22:32:06','mussu',NULL,'1619395724C24D822C5F00A632B235B0'),
 (1411,'2020-07-19 23:34:03','mussu',NULL,'1E684B4EEBFA69AFC6E17180B959BCD9'),
 (1412,'2020-07-20 00:47:29','mussu',NULL,'41AA6CCB9BE36F40A868C40DB06BF024'),
 (1413,'2020-07-20 12:11:33','mussu',NULL,'89B83573B035F17FE939D02728D46D46'),
 (1414,'2020-07-20 16:11:44','mussu',NULL,'808C0EDB3BC04E7BF0A89D6E43B2BA0B'),
 (1415,'2020-07-20 16:18:26','mussu',NULL,'E4C88A2381036B8476189E88AC4F1F76'),
 (1416,'2020-07-20 17:19:00','mussu',NULL,'B1C3B538DCC0D0FF4EBFCBD277E198A0'),
 (1417,'2020-07-20 17:19:19','mussu',NULL,'CA0FAC08F9378F1F8802A114F0E75CA1'),
 (1418,'2020-07-20 17:22:09','mussu',NULL,'A7480A44743E0E254BFBBD7FD5FA9BFE'),
 (1419,'2020-07-20 17:24:50','mussu',NULL,'EA6FB79C0A1E18E3195D9723508DF901'),
 (1420,'2020-07-20 17:35:24','mussu',NULL,'96F6498ED45A9C747A581C3DD5E52155'),
 (1421,'2020-07-20 17:38:54','mussu',NULL,'1BCB947275710E83F2A823D59B43BBA7'),
 (1422,'2020-07-20 17:43:09','mussu',NULL,'596B27F59D1C66956E1FEC7105577322'),
 (1423,'2020-07-20 17:47:30','mussu',NULL,'063B41A61B08340CD020948739D2743F'),
 (1424,'2020-07-20 21:02:21','mussu',NULL,'6252733846DDB15E094BCC09271946DF'),
 (1425,'2020-07-20 22:34:05','mussu',NULL,'49FD95E5A20BBB2A43518335C7DB9213'),
 (1426,'2020-07-21 00:18:30','mussu',NULL,'687E2B50B36A9A4DD574EC42350177F0'),
 (1427,'2020-07-21 00:21:43','mussu',NULL,'96FD9E21BEE031ABB7997D7CC1ABA29F'),
 (1428,'2020-07-21 00:26:46','mussu',NULL,'D04C2B999B417D6F2E10AE1C281C63C5'),
 (1429,'2020-07-21 00:29:04','mussu',NULL,'B6B33B8BE0776477733DBFE40DAD2ED8'),
 (1430,'2020-07-21 00:34:22','mussu',NULL,'042FF75D5932C11FE61D6AA4FF6AF2F7'),
 (1431,'2020-07-21 00:43:50','mussu',NULL,'D2F44DB9F76D849D20E4948736397D1A'),
 (1432,'2020-07-21 01:52:24','mussu',NULL,'68EE63D619AD7184B74AA47EAAD835C4'),
 (1433,'2020-07-21 01:55:19','mussu',NULL,'C8CF0B8FA15DD645A16D53B75A69E6DF'),
 (1434,'2020-07-21 02:17:42','mussu',NULL,'9296448D4B684EBF03FC839E03C65B9F'),
 (1435,'2020-07-21 14:27:41','mussu',NULL,'CCF7BDADC46BB904EB1CF5C795789B1E'),
 (1436,'2020-07-21 16:59:07','mussu',NULL,'FD7E4192C4F53C66E81A6EE13920AF08'),
 (1437,'2020-07-21 17:06:47','mussu',NULL,'065112BA6234FBA2A9E48DDAC308B557'),
 (1438,'2020-07-21 17:27:24','mussu',NULL,'BC8D81D1B1B8151930EE6D871DEBD346'),
 (1439,'2020-07-21 17:29:53','mussu',NULL,'E823A2AE8A8CB9BE42A5779E19063BE9'),
 (1440,'2020-07-21 17:38:37','mussu',NULL,'AF72F02D34864C7EE8267DFFEAB52B11'),
 (1441,'2020-07-21 18:33:24','mussu',NULL,'A82C67A1A2580AF2A03B4199984AB0A5'),
 (1442,'2020-07-21 19:48:29','mussu',NULL,'336D3E9663D3A3EC8FCE9538D17B7CDE'),
 (1443,'2020-07-21 19:55:54','mussu',NULL,'2871C71C13F80E5F8FF138877535B5D3'),
 (1444,'2020-07-21 20:08:07','mussu',NULL,'E21AD524C5215347A39F7B2B7E572699'),
 (1445,'2020-07-21 20:12:59','mussu',NULL,'BE47AAD45B2521A62677B223075A9718'),
 (1446,'2020-07-21 20:16:08','mussu',NULL,'F775723084E6748A718CED4FC4154828'),
 (1447,'2020-07-21 20:16:48','mussu','2020-07-21 21:02:22','38C4846911C2BDE3B7EC8814E47122C6'),
 (1448,'2020-07-21 21:02:31','mussu','2020-07-21 21:02:50','2EECE9A6D202192074F8F66FE086D72A'),
 (1449,'2020-07-21 21:02:54','mussu',NULL,'6D03E1E2D15563444315C22C29388A63'),
 (1450,'2020-07-22 02:15:42','mussu',NULL,'CC2E184F827A245E99856E523339155B'),
 (1451,'2020-07-23 01:54:11','mussu',NULL,'8BEF51442DA8E53E10E3D75E670152EC'),
 (1452,'2020-07-23 21:25:37','mussu',NULL,'5FEF9B65526EE8A45FBC4657130556B9'),
 (1453,'2020-07-23 23:19:01','mussu',NULL,'29F117692E95C5686785804BF765DEFA'),
 (1454,'2020-07-23 23:41:24','mussu',NULL,'A83081A4387971A7C0C33EB49E8D3673'),
 (1455,'2020-07-23 23:43:24','mussu',NULL,'BA986A189CC84356AE98C47E4820004C'),
 (1456,'2020-07-23 23:47:19','mussu',NULL,'EC6479E5A0E94DE1B8DB66594E53D2CF'),
 (1457,'2020-07-24 00:35:32','mussu',NULL,'53890CB2B407D64AFAB27A9C6F53BAA8'),
 (1458,'2020-07-24 14:15:28','mussu',NULL,'E9E4A3465988491F1A2AF7B2E9D2AD94'),
 (1459,'2020-07-24 14:40:12','mussu',NULL,'6CE043BA4D599AC37C7CB4BDCB4CB8FC'),
 (1460,'2020-07-24 15:11:58','mussu',NULL,'6552D92D396335E375EE3769CE7F6C9E'),
 (1461,'2020-07-24 15:20:03','mussu',NULL,'16CFAE505F8D5CC1029DF545E5EEBE0F'),
 (1462,'2020-07-24 15:30:22','mussu',NULL,'B31CA7337DA3B68E13EEE778F29C38C3'),
 (1463,'2020-07-24 15:36:21','mussu',NULL,'27465DA54224E3FBEFBAFDE556C50953'),
 (1464,'2020-07-24 16:11:06','mussu',NULL,'B8709B66658F2F434EE1B800566A1798'),
 (1465,'2020-07-24 18:36:13','mussu',NULL,'858F2E345D2230C6F0A33A24EFE4FF61'),
 (1466,'2020-07-24 21:07:35','mussu',NULL,'D77AAFC2F71A616A196355B42E3D38ED'),
 (1467,'2020-07-24 21:52:18','mussu',NULL,'ABA1A910C343C50D932DB3FAAD43F1A2'),
 (1468,'2020-07-24 23:27:01','mussu',NULL,'89A8B5402D0D2FB0B41C98112CC66EAA'),
 (1469,'2020-07-25 00:27:16','mussu',NULL,'ADA1F02219A34989A94F358D4CA57402'),
 (1470,'2020-07-25 11:12:27','mussu',NULL,'8279D7D94888E7C76825A8E13D297E4E'),
 (1471,'2020-07-25 13:41:27','mussu',NULL,'D2E3B9A8EC2A8FD438B2224F324DD79A'),
 (1472,'2020-07-25 13:53:18','mussu',NULL,'3BCA1B2A832F892912F95C1FDBE6C611'),
 (1473,'2020-07-25 14:01:13','mussu',NULL,'3EFBC7D053D597137B097BD6A76C5378'),
 (1474,'2020-07-25 15:15:06','mussu','2020-07-25 15:28:19','4BD0C9D3F7F0D46AAE4740165B2AE00D'),
 (1475,'2020-07-25 15:28:23','mussu',NULL,'84EFC16DF87FF96F40571F111E1FCEB3'),
 (1476,'2020-07-25 19:25:16','mussu',NULL,'B53E93D3DED4D2EB46B109AEDCB12749'),
 (1477,'2020-07-25 19:40:59','mussu',NULL,'62257481463ED9A6FD3116069AD71865'),
 (1478,'2020-07-25 19:45:38','mussu',NULL,'98C3DE888D699505FBAE7277AF22D74E'),
 (1479,'2020-07-25 22:42:51','mussu',NULL,'060D98D97C5711B4A8E971AD55E4AE8E'),
 (1480,'2020-07-25 23:08:52','mussu',NULL,'C4E49F579158317F2D4F307E02DFBD48'),
 (1481,'2020-07-25 23:12:49','mussu',NULL,'323EC9C54D62D7D0E592532CB0867F41'),
 (1482,'2020-07-26 00:10:23','mussu',NULL,'A261200EB3CBD886C7113EF48DE33368'),
 (1483,'2020-07-26 00:27:38','mussu',NULL,'E8DCBEA4C1C1160A6EC0114E9295A705'),
 (1484,'2020-07-26 00:27:44','mussu',NULL,'00820E620CFF68A132D731F1355EB180'),
 (1485,'2020-07-26 01:01:54','mussu',NULL,'AA78E8D35CE540DDF7F0225E3BF52F74'),
 (1486,'2020-07-26 01:06:06','mussu',NULL,'F3D30BCD09E95A5D3399E56F9F785733'),
 (1487,'2020-07-26 02:07:47','mussu',NULL,'CA48D440E1A602F996E176B1CAD14192'),
 (1488,'2020-07-26 02:59:12','mussu',NULL,'A95B055FBBFB70B81AB0B4BCA30ED727'),
 (1489,'2020-07-26 03:14:58','mussu',NULL,'8DD8D676AB2990CE2C008FBE5FA0A257'),
 (1490,'2020-07-26 16:12:07','mussu',NULL,'F3C9A2634853DB6745EC473A4517F5F1'),
 (1491,'2020-07-26 20:46:39','mussu',NULL,'19443AFAE0FA513D4167D93EA5B216CE'),
 (1492,'2020-07-31 22:10:27','mussu',NULL,'03FB7272A77AAE6D9B68805951050C52');
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
