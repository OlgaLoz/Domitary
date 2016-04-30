-- MySQL dump 10.13  Distrib 5.6.24, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: dormitorydb
-- ------------------------------------------------------
-- Server version	5.6.21

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
-- Table structure for table `block`
--

DROP TABLE IF EXISTS `block`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `block` (
  `ID_Block` int(11) NOT NULL AUTO_INCREMENT,
  `BlockNumber` int(11) NOT NULL,
  `ID_Dormitory` int(11) NOT NULL,
  PRIMARY KEY (`ID_Block`),
  KEY `FK_Block_Dormitory` (`ID_Dormitory`),
  CONSTRAINT `FK_Block_Dormitory` FOREIGN KEY (`ID_Dormitory`) REFERENCES `dormitory` (`ID_Dormitory`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `block`
--

LOCK TABLES `block` WRITE;
/*!40000 ALTER TABLE `block` DISABLE KEYS */;
INSERT INTO `block` VALUES (5,1,13),(6,2,13),(8,1,11),(9,1,12),(10,2,11),(68,10,13),(78,10,13),(88,10,13),(98,10,13),(100,15,12);
/*!40000 ALTER TABLE `block` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dormitory`
--

DROP TABLE IF EXISTS `dormitory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dormitory` (
  `ID_Dormitory` int(11) NOT NULL AUTO_INCREMENT,
  `Number` int(11) NOT NULL,
  `Address` varchar(50) NOT NULL,
  `MaxBlocksCount` int(11) NOT NULL,
  `FreeBlocksCount` int(11) NOT NULL,
  PRIMARY KEY (`ID_Dormitory`)
) ENGINE=InnoDB AUTO_INCREMENT=65 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dormitory`
--

LOCK TABLES `dormitory` WRITE;
/*!40000 ALTER TABLE `dormitory` DISABLE KEYS */;
INSERT INTO `dormitory` VALUES (11,2,'ул. Л. Беды, 4',200,198),(12,3,'ул. Л. Беды, 2б',200,198),(13,4,'пр. Дзержинского, 95 ',200,198),(64,5,'QWERTY',12,12);
/*!40000 ALTER TABLE `dormitory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `ID_Role` int(11) NOT NULL AUTO_INCREMENT,
  `RoleName` varchar(50) NOT NULL,
  PRIMARY KEY (`ID_Role`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'Guest'),(2,'Student'),(3,'Doctor'),(4,'Governor'),(5,'DeaneryWorker'),(6,'Admin');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `room`
--

DROP TABLE IF EXISTS `room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `room` (
  `ID_Room` int(11) NOT NULL AUTO_INCREMENT,
  `RoomNumber` int(11) NOT NULL,
  `MaxPlacesCount` int(11) NOT NULL,
  `FreePlacesCount` int(11) NOT NULL,
  `ID_Block` int(11) NOT NULL,
  PRIMARY KEY (`ID_Room`),
  KEY `FK_Room_Block` (`ID_Block`),
  CONSTRAINT `FK_Room_Block` FOREIGN KEY (`ID_Block`) REFERENCES `block` (`ID_Block`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=85 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room`
--

LOCK TABLES `room` WRITE;
/*!40000 ALTER TABLE `room` DISABLE KEYS */;
INSERT INTO `room` VALUES (9,1,2,2,5),(10,2,3,3,5),(11,1,2,2,6),(12,2,3,3,6),(15,1,2,0,8),(16,2,3,3,8),(17,1,2,2,9),(18,2,3,3,9),(19,1,2,2,10),(20,2,3,3,10),(61,1,4,4,68),(62,2,2,2,68),(67,1,4,4,78),(68,2,2,2,78),(73,1,4,4,88),(74,2,2,2,88),(79,1,4,4,98),(80,2,2,2,98),(83,1,2,2,100),(84,2,3,3,100);
/*!40000 ALTER TABLE `room` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `student` (
  `ID_Student` int(11) NOT NULL AUTO_INCREMENT,
  `FirstName` varchar(50) NOT NULL,
  `MidName` varchar(50) DEFAULT NULL,
  `LastName` varchar(50) NOT NULL,
  `DateOfBirth` date DEFAULT NULL,
  `GroupNumber` varchar(50) NOT NULL,
  `Statement` varchar(700) DEFAULT NULL,
  `DateOfSettlement` date DEFAULT NULL,
  `Contract` varchar(700) DEFAULT NULL,
  `ID_Room` int(11) DEFAULT NULL,
  `ID_Status` int(11) NOT NULL,
  `ID_User` int(11) NOT NULL,
  PRIMARY KEY (`ID_Student`),
  KEY `FK_Student_Room` (`ID_Room`),
  KEY `FK_Student_StudentStatus` (`ID_Status`),
  KEY `FK_Student_User` (`ID_User`),
  CONSTRAINT `FK_Student_Room` FOREIGN KEY (`ID_Room`) REFERENCES `room` (`ID_Room`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `FK_Student_StudentStatus` FOREIGN KEY (`ID_Status`) REFERENCES `studentstatus` (`ID_Status`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `FK_Student_User` FOREIGN KEY (`ID_User`) REFERENCES `user` (`ID_User`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=290 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES (9,'Петр','Петрович','Петров','1996-01-20','123456','{\"city\":\"Гомель\",\"class\":\"Model.Statement\",\"dean\":\"Прыткову В.А.\",\"father\":\"Петров Петр Евгеньевич\",\"fillingDate\":\"28.04.2016\",\"firstName\":\"Петра\",\"flat\":\"135\",\"group\":\"123456\",\"homePhone\":\"321-65-94\",\"house\":\"16\",\"lastName\":\"Петрова\",\"midName\":\"Петровича\",\"mobilePhone\":\"(+375 44) 123-45-67\",\"mother\":\"Петрова Анна Сергеевна\",\"region\":\"Гомельской области\",\"street\":\"Васильковая\",\"training\":\"Бюджетная\"}','2016-04-30','{\"chair\":\"Программного обеспечения информационных технологий\",\"city\":\"Гомель\",\"class\":\"Model.Contract\",\"faculty\":\"Факультет компьютерных систем и сетей\",\"fillingDate\":\"28.04.2016\",\"flat\":\"135\",\"house\":\"16\",\"passport\":\"MP 1112233\",\"passportDateOfIssue\":\"17.02.2003\",\"street\":\"Васильковая\"}',15,7,13),(10,'Иван','Иванович','Иванов','1995-01-02','654321','{\"city\":\"Гомель\",\"class\":\"Model.Statement\",\"dean\":\"Прыткову В.А.\",\"father\":\"Иванов Иван Евгеньевич\",\"fillingDate\":\"28.04.2016\",\"firstName\":\"Иван\",\"flat\":\"135\",\"group\":\"654321\",\"homePhone\":\"321-65-94\",\"house\":\"16\",\"lastName\":\"Иванов\",\"midName\":\"Иванович\",\"mobilePhone\":\"(+375 44) 123-45-67\",\"mother\":\"Иванова Анна Сергеевна\",\"region\":\"Гомельской области\",\"street\":\"Васильковая\",\"training\":\"Бюджетная\"}','2016-04-30','{\"chair\":\"Программного обеспечения информационных технологий\",\"city\":\"Гомель\",\"class\":\"Model.Contract\",\"faculty\":\"Факультет компьютерных систем и сетей\",\"fillingDate\":\"28.04.2016\",\"flat\":\"135\",\"house\":\"16\",\"passport\":\"MP 1112245\",\"passportDateOfIssue\":\"17.02.2003\",\"street\":\"Васильковая\"}',15,7,15),(11,'Федор','Федорович','Федев','1996-01-03','147369',NULL,NULL,NULL,NULL,1,16),(12,'Максим','Сергеевич','Горбачев','1995-11-21','351001',NULL,NULL,NULL,NULL,1,17),(13,'Ольга','Викторовна','Лозовик','1996-06-01','351001',NULL,NULL,NULL,NULL,1,18),(14,'Наталья','Геннадьевна','Владимирова','1996-01-22','351001',NULL,NULL,NULL,NULL,1,19),(217,'Павел','Павлович','Павлов','1996-01-02','963852',NULL,NULL,NULL,NULL,1,44);
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `studentstatus`
--

DROP TABLE IF EXISTS `studentstatus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `studentstatus` (
  `ID_Status` int(11) NOT NULL AUTO_INCREMENT,
  `StatusName` varchar(50) NOT NULL,
  PRIMARY KEY (`ID_Status`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `studentstatus`
--

LOCK TABLES `studentstatus` WRITE;
/*!40000 ALTER TABLE `studentstatus` DISABLE KEYS */;
INSERT INTO `studentstatus` VALUES (1,'Default'),(2,'Candidate'),(3,'DeaneryPassed'),(4,'DeaneryNotPassed'),(5,'BodyCheckPassed'),(6,'BodyCheckNotPassed'),(7,'Settled'),(8,'NotSettled');
/*!40000 ALTER TABLE `studentstatus` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `ID_User` int(11) NOT NULL AUTO_INCREMENT,
  `Login` varchar(50) NOT NULL,
  `Password` varbinary(500) NOT NULL,
  `Salt` varbinary(10) NOT NULL,
  `ID_Role` int(11) NOT NULL,
  PRIMARY KEY (`ID_User`),
  KEY `FK_User_Role` (`ID_Role`),
  CONSTRAINT `FK_User_Role` FOREIGN KEY (`ID_Role`) REFERENCES `role` (`ID_Role`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'doctor','���}�v�O�j��{��','��`��t$',3),(2,'governor','����h-��!�C��Eo\Z|','�����?�',4),(3,'deaneryworker','�騗�RMk�knD�{!O)','[t^����',5),(4,'admin','}�ؗ�]3nF��tj@L}ئ','���Y�7��',6),(13,'Peter','�nZ*�zD���TV�<�&�Y','�X�3�8,',2),(15,'Ivan','Լ��4j\0���t���z<��','2(����',2),(16,'Fedor','�-���o�-\'���: ?zS','?Bkm���g',2),(17,'Maxim','','',2),(18,'Olga','','',2),(19,'Natali','','',2),(44,'pavel','o.��s_����:����&*̤','�7D��v',2);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-04-30 19:41:56
