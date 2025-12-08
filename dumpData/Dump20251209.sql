-- MySQL dump 10.13  Distrib 8.0.44, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: collegemanagement
-- ------------------------------------------------------
-- Server version	8.0.44

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `admins`
--

DROP TABLE IF EXISTS `admins`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admins` (
  `id` int NOT NULL AUTO_INCREMENT,
  `userId` int NOT NULL,
  `adminId` varchar(20) DEFAULT NULL,
  `dob` date DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `departmentId` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `userId` (`userId`),
  UNIQUE KEY `adminId` (`adminId`),
  KEY `fk_department` (`departmentId`),
  CONSTRAINT `admins_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `users` (`userId`),
  CONSTRAINT `fk_department` FOREIGN KEY (`departmentId`) REFERENCES `departments` (`departmentId`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admins`
--

LOCK TABLES `admins` WRITE;
/*!40000 ALTER TABLE `admins` DISABLE KEYS */;
INSERT INTO `admins` VALUES (1,11,'ADMIN00012025','2012-02-12','New Delhi',NULL),(2,20,'ADMIN00202025','2025-11-13','New Delhi',NULL);
/*!40000 ALTER TABLE `admins` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course_subjects`
--

DROP TABLE IF EXISTS `course_subjects`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `course_subjects` (
  `subjectId` int NOT NULL AUTO_INCREMENT,
  `courseId` int NOT NULL,
  `semester` int NOT NULL,
  `type` enum('CLASS','LAB','SESSION','SEMINAR') DEFAULT NULL,
  `subjectName` varchar(100) NOT NULL,
  `subjectCode` varchar(20) NOT NULL,
  `description` text,
  `teachesBy` varchar(20) DEFAULT NULL,
  `shortName` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`subjectId`),
  UNIQUE KEY `subjectCode` (`subjectCode`),
  KEY `courseId` (`courseId`),
  KEY `fk_teaches_by` (`teachesBy`),
  CONSTRAINT `course_subjects_ibfk_1` FOREIGN KEY (`courseId`) REFERENCES `courses` (`courseId`),
  CONSTRAINT `fk_teaches_by` FOREIGN KEY (`teachesBy`) REFERENCES `teachers` (`employeeId`)
) ENGINE=InnoDB AUTO_INCREMENT=97 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course_subjects`
--

LOCK TABLES `course_subjects` WRITE;
/*!40000 ALTER TABLE `course_subjects` DISABLE KEYS */;
INSERT INTO `course_subjects` VALUES (1,1,1,'CLASS','Discrete Structures','MCA101',NULL,'FACULTY00022025','DS'),(2,1,1,'CLASS','Object Oriented Programming and JAVA','MCA102',NULL,'FACULTY00072025','JAVA'),(3,1,1,'CLASS','Database Management Systems','MCA103',NULL,'FACULTY00052025','DBMS'),(4,1,1,'CLASS','Computer Networks','MCA104',NULL,'FACULTY00082025','CN'),(5,1,1,'CLASS','Operating Systems with Linux','MCA105',NULL,'FACULTY00032025','OS'),(6,1,1,'LAB','Object Oriented Programming and JAVA Lab','MCA106',NULL,'FACULTY00072025','JAVA (lab)'),(7,1,1,'LAB','Database Management Systems Lab','MAC107',NULL,'FACULTY00052025','DBMS (lab)'),(8,1,1,'LAB','Computer Networks Lab','MCA108',NULL,'FACULTY00082025','CN (lab)'),(9,1,1,'LAB','Operating Systems with Linux Lab','MCA109',NULL,'FACULTY00032025','OS (lab)'),(10,1,1,'CLASS','IEEE Seminar','MCA110',NULL,NULL,'IEEE'),(11,1,1,'CLASS','Coporate Grooming','MCA111',NULL,NULL,'CG'),(12,1,1,'LAB','Placement Session','MCA112',NULL,NULL,'PS'),(13,1,2,'CLASS','Data and File Structures','MCA-S201',NULL,'FACULTY00072025','DFS'),(14,1,2,'CLASS','Object Oriented Software Engineering','MCA-S202',NULL,'FACULTY00032025','OOSE'),(15,1,2,'CLASS','Python Programming','MCA-S203',NULL,'FACULTY00082025','PYTHON'),(16,1,2,'CLASS','Software Testing','MCA-S204',NULL,'FACULTY00052025','TESTING'),(17,1,2,'CLASS','Digital Marketing','MCA-S205',NULL,'FACULTY00022025','DM'),(18,1,2,'LAB','Data and File Structures','MCA-S206',NULL,'FACULTY00072025','DFS LAB'),(19,1,2,'LAB','Object Oriented Software Engineering','MCA-S207',NULL,'FACULTY00032025','OOSE LAB'),(20,1,2,'LAB','Python Programming','MCA-S208',NULL,'FACULTY00082025','PYTHON LAB'),(21,1,2,'LAB','Software Testing','MCA-S209',NULL,'FACULTY00052025','TESTING LAB'),(22,1,3,'CLASS','Design and Analysis of Algorithms','MCA-S301',NULL,'FACULTY00082025','DAA'),(23,1,3,'CLASS','Artifical Intelligence and Machine Learning','MCA-S302',NULL,'FACULTY00082025','AI/ML'),(24,1,3,'CLASS','Cloud Computing','MCA-S303',NULL,'FACULTY00052025','CLOUD'),(25,1,3,'CLASS','Multimedia Technologies','MCA-S304',NULL,'FACULTY00122025','MMT'),(26,1,3,'CLASS','Cyber Security and Cyber Laws','MCA-S305',NULL,'FACULTY00022025','CYBER'),(27,1,3,'LAB','Design and Analysis of Algorithms','MCA-S306',NULL,'FACULTY00032025','DAA LAB'),(28,1,3,'LAB','Artifical Intelligence and Machine Learning','MCA-S307',NULL,'FACULTY00052025','AI/ML LAB'),(29,1,3,'LAB','Cloud Computing','MCA-S308',NULL,'FACULTY00072025','CLOUD LAB'),(30,4,1,'CLASS','Introduction to Communciation','BJAMC-S101',NULL,'FACULTY00132025','Intro'),(31,4,1,'CLASS','Socio,Economic and Political Overview','BJAMC-S102',NULL,'FACULTY00142025','SEPO'),(32,4,1,'CLASS','Basis of Design and Graphics','BJAMC-S103',NULL,'FACULTY00152025','Design'),(33,4,1,'CLASS','Cultural Communciation','BJAMC-S104',NULL,'FACULTY00162025','Culture'),(34,4,1,'CLASS','Personality Development','BJAMC-S105',NULL,'FACULTY00172025','PD'),(35,4,1,'CLASS','Writing Skills for Media','BJAMC-S106',NULL,'FACULTY00182025','Writing'),(36,4,1,'CLASS','Basics of English Language','BJAMC-S107',NULL,'FACULTY00192025','English'),(37,4,1,'LAB','Communciation Skills','BJAMC-S108',NULL,'FACULTY00132025','Comm Lab'),(38,4,1,'LAB','Design and Graphics','BJAMC-S109',NULL,'FACULTY00142025','DesignLab'),(39,4,1,'LAB','Cultural Communciation','BJAMC-S110',NULL,'FACULTY00152025','CC Lab'),(40,4,1,'LAB','Personality Development','BJAMC-S111',NULL,'FACULTY00162025','PD Lab'),(41,4,1,'LAB','Writing Skills for Media','BJAMC-S112',NULL,'FACULTY00172025','WriteLab'),(42,4,2,'CLASS','Development Communciation','BJAMC-S201',NULL,'FACULTY00182025','DevComm'),(43,4,2,'CLASS','Reporting and Editing for Print Journalism','BJAMC-S202',NULL,'FACULTY00192025','R&E'),(44,4,2,'CLASS','Media Law and Ethics','BJAMC-S203',NULL,'FACULTY00132025','Law'),(45,4,2,'CLASS','Health Communciation','BJAMC-S204',NULL,'FACULTY00142025','Health'),(46,4,2,'CLASS','Sports Journalism','BJAMC-S205',NULL,'FACULTY00152025','SportsJrn'),(47,4,2,'CLASS','Still Photography','BJAMC-S206',NULL,'FACULTY00162025','Photo'),(48,4,2,'CLASS','Basics of Hindi Language','BJAMC-S207',NULL,'FACULTY00172025','Hindi'),(49,4,2,'LAB','Reporting and Editing','BJAMC-S208',NULL,'FACULTY00182025','EditLab'),(50,4,2,'LAB','Still Photography','BJAMC-S209',NULL,'FACULTY00192025','PhotoLab'),(51,4,3,'CLASS','History of Journalism:Print,Electronic and Digital','BJAMC-S301',NULL,'FACULTY00132025','History'),(52,4,3,'CLASS','Radio Programming and Production','BJAMC-S302',NULL,'FACULTY00142025','Radio'),(53,4,3,'CLASS','Basis of video Camera,Lights ans Sound','BJAMC-S303',NULL,'FACULTY00152025','Camera'),(54,4,3,'CLASS','Investigate Journalism','BJAMC-S304',NULL,'FACULTY00162025','Investigate'),(55,4,3,'CLASS','Radio Jockeying and News Reading','BJAMC-S305',NULL,'FACULTY00172025','RJ'),(56,4,3,'CLASS','Audio and Video Editing','BJAMC-S306',NULL,'FACULTY00182025','AV Edit'),(57,4,3,'LAB','Radio Production','BJAMC-S307',NULL,'FACULTY00192025','RadioLab'),(58,4,3,'LAB','Visual Production','BJAMC-S308',NULL,'FACULTY00132025','VisualLab'),(59,4,3,'CLASS','Audio and Video Editing','BJAMC-S309',NULL,'FACULTY00142025','AVLab'),(60,4,4,'CLASS','Advertising:Concepts and Practices','BJAMC-S401',NULL,'FACULTY00152025','Ads'),(61,4,4,'CLASS','Public Relations:Concepts and Practices','BJAMC-S402',NULL,'FACULTY00162025','PR'),(62,4,4,'CLASS','TV Programming and Production','BJAMC-S403',NULL,'FACULTY00172025','TV Prod'),(63,4,4,'CLASS','Coporate Communciation','BJAMC-S404',NULL,'FACULTY00182025','CorpComm'),(64,4,4,'CLASS','TV News Reporting and Anchoring','BJAMC-S405',NULL,'FACULTY00192025','Anchoring'),(65,4,4,'CLASS','Digital Media-Tools and Techniques','BJAMC-S406',NULL,'FACULTY00132025','Digital'),(66,4,4,'LAB','Advertising','BJAMC-S407',NULL,'FACULTY00142025','AdsLab'),(67,4,4,'LAB','Public Relation','BJAMC-S408',NULL,'FACULTY00152025','PR Lab'),(68,4,4,'LAB','Public Relation','BJAMC-S409',NULL,'FACULTY00162025','PR Lab'),(69,4,4,'LAB','TV Production','BJAMC-S410',NULL,'FACULTY00172025','TVLab'),(70,4,4,'LAB','TV News Reporting And Anchoring','BJAMC-S411',NULL,'FACULTY00182025','AnchorLab'),(71,4,5,'CLASS','Event Management','BJAMC-S501',NULL,'FACULTY00192025','Event Mgmt'),(72,4,5,'CLASS','Communciation Research','BJAMC-S502',NULL,'FACULTY00132025','Comm Res'),(73,4,5,'CLASS','Integrated Marketing Communciation','BJAMC-S503',NULL,'FACULTY00142025','IMC'),(74,4,5,'CLASS','Theatre Appreciation','BJAMC-S504',NULL,'FACULTY00152025','Theatre'),(75,4,5,'CLASS','Film Appreciation','BJAMC-S505',NULL,'FACULTY00162025','Film App'),(76,4,5,'CLASS','Content Creation for Digital Media','BJAMC-S506',NULL,'FACULTY00172025','Content DM'),(77,4,5,'CLASS','Entreprenuial Mindset','BJAMC-S507',NULL,'FACULTY00182025','Mindset'),(78,4,5,'LAB','Event Management','BJAMC-S508',NULL,'FACULTY00192025','Event Lab'),(79,4,5,'LAB','Communciation Research','BJAMC-S509',NULL,'FACULTY00132025','Comm Res Lab'),(80,4,5,'LAB','Integrated Marketing Communciation','BJAMC-S510',NULL,'FACULTY00142025','IMC Lab'),(81,4,6,'CLASS','Global Media Scenario','BJAMC-S601',NULL,'FACULTY00152025','Global Media'),(82,4,6,'CLASS','Media Organization and Management','BJAMC-S602',NULL,'FACULTY00162025','Media Org'),(83,4,6,'CLASS','Data Journalism','BJAMC-S603',NULL,'FACULTY00172025','Data Journo'),(84,4,6,'CLASS','Digital Film Making','BJAMC-S604',NULL,'FACULTY00182025','Film Making'),(85,4,6,'CLASS','Digital Media Marketing','BJAMC-S605',NULL,'FACULTY00192025','DM Marketing'),(86,4,6,'LAB','Data Journalism','BJAMC-S606',NULL,'FACULTY00132025','Data J Lab'),(87,4,6,'LAB','Digital Film Making','BJAMC-S607',NULL,'FACULTY00142025','Film Lab'),(88,4,6,'LAB','Digital Media Marketing','BJAMC-S608',NULL,'FACULTY00152025','DM Lab'),(89,4,7,'CLASS','Media Literacy','BJAMC-S701',NULL,'FACULTY00162025','Media Lit'),(90,4,7,'CLASS','OTT Content Production and Promotion','BJAMC-S702',NULL,'FACULTY00172025','OTT Prod'),(91,4,7,'CLASS','Podcast Production','BJAMC-S703',NULL,'FACULTY00182025','Podcast'),(92,4,7,'CLASS','AI tools For Media','BJAMC-S704',NULL,'FACULTY00192025','AI Media'),(93,4,7,'CLASS','Basics of Animation','BJAMC-S705',NULL,'FACULTY00132025','Animation'),(94,4,7,'LAB','OTT Content Production and Promotion','BJAMC-S706',NULL,'FACULTY00142025','OTT Lab'),(95,4,7,'LAB','Podcast Production','BJAMC-S707',NULL,'FACULTY00152025','Podcast Lab'),(96,4,7,'LAB','AI tools For Media','BJAMC-S708',NULL,'FACULTY00162025','AI Lab');
/*!40000 ALTER TABLE `course_subjects` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `courses`
--

DROP TABLE IF EXISTS `courses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `courses` (
  `courseId` int NOT NULL AUTO_INCREMENT,
  `courseName` varchar(100) NOT NULL,
  `courseCode` varchar(20) NOT NULL,
  `description` text,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `years` int NOT NULL,
  `semesters` int DEFAULT NULL,
  PRIMARY KEY (`courseId`),
  UNIQUE KEY `courseCode` (`courseCode`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `courses`
--

LOCK TABLES `courses` WRITE;
/*!40000 ALTER TABLE `courses` DISABLE KEYS */;
INSERT INTO `courses` VALUES (1,'MCA','MCA101','Master of Computer Applications','2025-11-24 19:10:02',2,4),(4,'BJAMC','BJAMC101','Bachelor of Journalism and Mass Communication','2025-11-24 19:10:02',4,8);
/*!40000 ALTER TABLE `courses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `departments`
--

DROP TABLE IF EXISTS `departments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `departments` (
  `departmentId` int NOT NULL AUTO_INCREMENT,
  `departmentName` varchar(100) NOT NULL,
  PRIMARY KEY (`departmentId`),
  UNIQUE KEY `departmentName` (`departmentName`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `departments`
--

LOCK TABLES `departments` WRITE;
/*!40000 ALTER TABLE `departments` DISABLE KEYS */;
INSERT INTO `departments` VALUES (2,'BVICAM'),(1,'BVIMR');
/*!40000 ALTER TABLE `departments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `leave_applications`
--

DROP TABLE IF EXISTS `leave_applications`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `leave_applications` (
  `request_number` int NOT NULL AUTO_INCREMENT,
  `userId` int NOT NULL,
  `total_leaves_allotted` int DEFAULT '5',
  `remaining_leaves` int DEFAULT NULL,
  `leave_type` enum('Medical','Duty','Casual') NOT NULL,
  `date_from` date NOT NULL,
  `date_to` date NOT NULL,
  `reason` text,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `status` enum('Pending','Approved','Rejected') DEFAULT 'Pending',
  PRIMARY KEY (`request_number`),
  KEY `fk_user` (`userId`),
  CONSTRAINT `fk_user` FOREIGN KEY (`userId`) REFERENCES `users` (`userId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `leave_applications`
--

LOCK TABLES `leave_applications` WRITE;
/*!40000 ALTER TABLE `leave_applications` DISABLE KEYS */;
INSERT INTO `leave_applications` VALUES (1,4,5,NULL,'Casual','2025-11-30','2025-12-01','for personal work','2025-11-29 17:09:41','Approved'),(2,4,5,NULL,'Medical','2025-11-30','2025-11-30','Medical Leave','2025-11-29 17:57:20','Pending'),(3,4,5,NULL,'Duty','2025-11-21','2025-11-28','Duty Leave','2025-11-29 18:02:30','Rejected');
/*!40000 ALTER TABLE `leave_applications` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `students`
--

DROP TABLE IF EXISTS `students`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `students` (
  `id` int NOT NULL AUTO_INCREMENT,
  `studentId` int NOT NULL,
  `courseId` int NOT NULL,
  `enrolled_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `rollNo` varchar(20) DEFAULT NULL,
  `dob` date DEFAULT NULL,
  `father_name` varchar(20) DEFAULT NULL,
  `mother_name` varchar(20) DEFAULT NULL,
  `father_occu` varchar(30) DEFAULT NULL,
  `mother_occu` varchar(30) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `currSemester` int DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `studentId` (`studentId`),
  UNIQUE KEY `rollNo` (`rollNo`),
  KEY `fk_student_course` (`courseId`),
  CONSTRAINT `fk_student_course` FOREIGN KEY (`courseId`) REFERENCES `courses` (`courseId`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `fk_student_user` FOREIGN KEY (`studentId`) REFERENCES `users` (`userId`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `students`
--

LOCK TABLES `students` WRITE;
/*!40000 ALTER TABLE `students` DISABLE KEYS */;
INSERT INTO `students` VALUES (1,1,1,'2025-11-24 19:14:15',NULL,NULL,NULL,NULL,NULL,NULL,NULL,1),(2,2,1,'2025-11-24 19:14:15',NULL,NULL,NULL,NULL,NULL,NULL,NULL,1),(3,3,1,'2025-11-24 19:14:15',NULL,NULL,NULL,NULL,NULL,NULL,NULL,1),(4,4,1,'2025-11-24 19:14:15','BJAMC00042025','2025-11-05','L D Joshi','Deepa Joshi','Government Job','House Wife','sarojini nagar',1),(5,5,1,'2025-11-24 19:14:15',NULL,NULL,NULL,NULL,NULL,NULL,NULL,1),(6,6,4,'2025-11-24 19:14:15',NULL,NULL,NULL,NULL,NULL,NULL,NULL,1),(7,7,4,'2025-11-24 19:14:15',NULL,NULL,NULL,NULL,NULL,NULL,NULL,1),(8,8,1,'2025-11-24 19:14:15',NULL,NULL,NULL,NULL,NULL,NULL,NULL,1),(9,9,1,'2025-11-24 19:14:15',NULL,NULL,NULL,NULL,NULL,NULL,NULL,1),(10,10,1,'2025-11-24 19:14:15',NULL,NULL,NULL,NULL,NULL,NULL,NULL,1);
/*!40000 ALTER TABLE `students` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subjectclassrecord`
--

DROP TABLE IF EXISTS `subjectclassrecord`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `subjectclassrecord` (
  `record_id` int NOT NULL AUTO_INCREMENT,
  `subjectId` int NOT NULL,
  `faculty_id` varchar(15) NOT NULL,
  `week_total_classes` int NOT NULL,
  `week_taken_classes` int DEFAULT '0',
  `total_classes_till_date` int DEFAULT '0',
  `minClassNeed` int DEFAULT '44',
  PRIMARY KEY (`record_id`),
  KEY `faculty_id` (`faculty_id`),
  KEY `fk_scr_subject` (`subjectId`),
  CONSTRAINT `fk_scr_subject` FOREIGN KEY (`subjectId`) REFERENCES `course_subjects` (`subjectId`) ON DELETE CASCADE,
  CONSTRAINT `subjectclassrecord_ibfk_1` FOREIGN KEY (`subjectId`) REFERENCES `course_subjects` (`subjectId`),
  CONSTRAINT `subjectclassrecord_ibfk_2` FOREIGN KEY (`faculty_id`) REFERENCES `teachers` (`employeeId`)
) ENGINE=InnoDB AUTO_INCREMENT=94 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subjectclassrecord`
--

LOCK TABLES `subjectclassrecord` WRITE;
/*!40000 ALTER TABLE `subjectclassrecord` DISABLE KEYS */;
INSERT INTO `subjectclassrecord` VALUES (1,1,'FACULTY00022025',6,0,0,44),(2,2,'FACULTY00072025',5,0,0,44),(3,3,'FACULTY00052025',6,0,0,44),(4,4,'FACULTY00082025',5,0,0,44),(5,5,'FACULTY00032025',5,0,0,44),(6,6,'FACULTY00072025',2,0,0,14),(7,7,'FACULTY00052025',2,0,0,14),(8,8,'FACULTY00082025',2,0,0,14),(9,9,'FACULTY00032025',2,0,0,14),(10,13,'FACULTY00072025',5,0,0,44),(11,14,'FACULTY00082025',6,0,0,44),(12,15,'FACULTY00082025',5,0,0,44),(13,16,'FACULTY00052025',5,0,0,44),(14,17,'FACULTY00122025',5,0,0,44),(15,18,'FACULTY00022025',2,0,0,14),(16,19,'FACULTY00032025',2,0,0,14),(17,20,'FACULTY00052025',2,0,0,14),(18,21,'FACULTY00072025',2,0,0,14),(19,22,'FACULTY00082025',6,0,0,44),(22,25,'FACULTY00122025',5,0,0,44),(23,26,'FACULTY00022025',6,0,0,44),(24,27,'FACULTY00032025',2,0,0,14),(25,28,'FACULTY00052025',2,0,0,14),(26,29,'FACULTY00072025',2,0,0,14),(27,30,'FACULTY00132025',6,0,0,44),(28,31,'FACULTY00142025',5,0,0,44),(29,32,'FACULTY00152025',6,0,0,44),(30,33,'FACULTY00162025',5,0,0,44),(31,34,'FACULTY00172025',6,0,0,44),(32,35,'FACULTY00182025',5,0,0,44),(33,36,'FACULTY00192025',6,0,0,44),(34,37,'FACULTY00132025',2,0,0,14),(35,38,'FACULTY00142025',2,0,0,14),(36,39,'FACULTY00152025',2,0,0,14),(37,40,'FACULTY00162025',2,0,0,14),(38,41,'FACULTY00172025',2,0,0,14),(39,42,'FACULTY00182025',6,0,0,44),(40,43,'FACULTY00192025',5,0,0,44),(41,44,'FACULTY00132025',6,0,0,44),(42,45,'FACULTY00142025',5,0,0,44),(43,46,'FACULTY00152025',6,0,0,44),(44,47,'FACULTY00162025',5,0,0,44),(45,48,'FACULTY00172025',6,0,0,44),(46,49,'FACULTY00182025',2,0,0,14),(47,50,'FACULTY00192025',2,0,0,14),(48,51,'FACULTY00132025',5,0,0,44),(49,52,'FACULTY00142025',6,0,0,44),(50,53,'FACULTY00152025',5,0,0,44),(51,54,'FACULTY00162025',6,0,0,44),(52,55,'FACULTY00172025',5,0,0,44),(53,56,'FACULTY00182025',6,0,0,44),(54,57,'FACULTY00192025',2,0,0,14),(55,58,'FACULTY00132025',2,0,0,14),(56,59,'FACULTY00142025',5,0,0,44),(57,60,'FACULTY00152025',6,0,0,44),(58,61,'FACULTY00162025',5,0,0,44),(59,62,'FACULTY00172025',6,0,0,44),(60,63,'FACULTY00182025',5,0,0,44),(61,64,'FACULTY00192025',6,0,0,44),(62,65,'FACULTY00132025',5,0,0,44),(63,66,'FACULTY00142025',2,0,0,14),(64,67,'FACULTY00152025',2,0,0,14),(65,68,'FACULTY00162025',2,0,0,14),(66,69,'FACULTY00172025',2,0,0,14),(67,70,'FACULTY00182025',2,0,0,14),(68,71,'FACULTY00192025',5,0,0,44),(69,72,'FACULTY00132025',6,0,0,44),(70,73,'FACULTY00142025',5,0,0,44),(71,74,'FACULTY00152025',6,0,0,44),(72,75,'FACULTY00162025',5,0,0,44),(73,76,'FACULTY00172025',6,0,0,44),(74,77,'FACULTY00182025',5,0,0,44),(75,78,'FACULTY00192025',2,0,0,14),(76,79,'FACULTY00132025',2,0,0,14),(77,80,'FACULTY00142025',2,0,0,14),(78,81,'FACULTY00152025',5,0,0,44),(79,82,'FACULTY00162025',6,0,0,44),(80,83,'FACULTY00172025',5,0,0,44),(81,84,'FACULTY00182025',6,0,0,44),(82,85,'FACULTY00192025',5,0,0,44),(83,86,'FACULTY00132025',2,0,0,14),(84,87,'FACULTY00142025',2,0,0,14),(85,88,'FACULTY00152025',2,0,0,14),(86,89,'FACULTY00162025',5,0,0,44),(87,90,'FACULTY00172025',6,0,0,44),(88,91,'FACULTY00182025',5,0,0,44),(89,92,'FACULTY00192025',6,0,0,44),(90,93,'FACULTY00132025',5,0,0,44),(91,94,'FACULTY00142025',2,0,0,14),(92,95,'FACULTY00152025',2,0,0,14),(93,96,'FACULTY00162025',2,0,0,14);
/*!40000 ALTER TABLE `subjectclassrecord` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teacher_schedule`
--

DROP TABLE IF EXISTS `teacher_schedule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `teacher_schedule` (
  `t_schedule_id` int NOT NULL AUTO_INCREMENT,
  `employeeId` varchar(15) NOT NULL,
  `day_name` varchar(20) NOT NULL,
  `slot_number` int NOT NULL,
  `subjectId` int NOT NULL,
  `semester` int NOT NULL,
  `section` char(2) DEFAULT NULL,
  `room_number` varchar(10) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`t_schedule_id`),
  KEY `employeeId` (`employeeId`),
  CONSTRAINT `teacher_schedule_ibfk_1` FOREIGN KEY (`employeeId`) REFERENCES `teachers` (`employeeId`) ON DELETE CASCADE,
  CONSTRAINT `check_slot` CHECK ((`slot_number` between 1 and 9))
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teacher_schedule`
--

LOCK TABLES `teacher_schedule` WRITE;
/*!40000 ALTER TABLE `teacher_schedule` DISABLE KEYS */;
INSERT INTO `teacher_schedule` VALUES (3,'FACULTY00072025','Friday',1,2,1,'A',NULL,NULL),(4,'FACULTY00072025','Thursday',1,2,1,'A',NULL,NULL),(5,'FACULTY00082025','Saturday',1,4,1,'A',NULL,NULL),(6,'FACULTY00082025','Wednesday',1,4,1,'A',NULL,NULL),(7,'FACULTY00082025','Tuesday',1,4,1,'A',NULL,NULL),(8,'FACULTY00032025','Monday',1,5,1,'A',NULL,NULL),(10,'FACULTY00072025','Tuesday',2,2,1,'A',NULL,NULL),(11,'FACULTY00052025','Saturday',2,3,1,'A',NULL,NULL),(12,'FACULTY00052025','Wednesday',2,3,1,'A',NULL,NULL),(13,'FACULTY00052025','Monday',2,3,1,'A',NULL,NULL),(14,'FACULTY00082025','Friday',2,4,1,'A',NULL,NULL),(15,'FACULTY00082025','Thursday',2,4,1,'A',NULL,NULL),(17,'FACULTY00022025','Friday',4,1,1,'A',NULL,NULL),(18,'FACULTY00022025','Wednesday',4,1,1,'A',NULL,NULL),(19,'FACULTY00072025','Saturday',4,2,1,'A',NULL,NULL),(20,'FACULTY00072025','Monday',4,2,1,'A',NULL,NULL),(21,'FACULTY00032025','Thursday',4,5,1,'A',NULL,NULL),(22,'FACULTY00032025','Tuesday',4,5,1,'A',NULL,NULL),(24,'FACULTY00022025','Tuesday',5,1,1,'A',NULL,NULL),(25,'FACULTY00022025','Monday',5,1,1,'A',NULL,NULL),(26,'FACULTY00052025','Saturday',5,3,1,'A',NULL,NULL),(27,'FACULTY00052025','Thursday',5,3,1,'A',NULL,NULL),(28,'FACULTY00052025','Wednesday',5,3,1,'A',NULL,NULL),(29,'FACULTY00032025','Friday',5,5,1,'A',NULL,NULL),(31,'FACULTY00022025','Saturday',7,1,1,'A',NULL,NULL),(32,'FACULTY00022025','Friday',7,1,1,'A',NULL,NULL),(33,'FACULTY00072025','Thursday',7,6,1,'A',NULL,NULL),(34,'FACULTY00052025','Wednesday',7,7,1,'A',NULL,NULL),(35,'FACULTY00082025','Tuesday',7,8,1,'A',NULL,NULL),(36,'FACULTY00032025','Monday',7,9,1,'A',NULL,NULL),(38,'FACULTY00032025','Friday',8,5,1,'A',NULL,NULL),(39,'FACULTY00072025','Thursday',8,6,1,'A',NULL,NULL),(40,'FACULTY00052025','Wednesday',8,7,1,'A',NULL,NULL),(41,'FACULTY00082025','Tuesday',8,8,1,'A',NULL,NULL),(42,'FACULTY00032025','Monday',8,9,1,'A',NULL,NULL);
/*!40000 ALTER TABLE `teacher_schedule` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teachers`
--

DROP TABLE IF EXISTS `teachers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `teachers` (
  `userId` int NOT NULL,
  `employeeId` varchar(15) DEFAULT NULL,
  `designation` varchar(50) DEFAULT NULL,
  `departmentId` int NOT NULL,
  `joiningDate` date DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `father_name` varchar(50) DEFAULT NULL,
  `mother_name` varchar(50) DEFAULT NULL,
  `father_occu` varchar(50) DEFAULT NULL,
  `mother_occu` varchar(50) DEFAULT NULL,
  `dob` date DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`userId`),
  UNIQUE KEY `employeeId` (`employeeId`),
  KEY `departmentId` (`departmentId`),
  CONSTRAINT `teachers_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `users` (`userId`),
  CONSTRAINT `teachers_ibfk_2` FOREIGN KEY (`departmentId`) REFERENCES `departments` (`departmentId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teachers`
--

LOCK TABLES `teachers` WRITE;
/*!40000 ALTER TABLE `teachers` DISABLE KEYS */;
INSERT INTO `teachers` VALUES (2,'FACULTY00022025','Professor',2,'2020-08-15','2025-11-25 10:47:45','Anil Kumar Sharma','Priya Devi','Government Employee',NULL,'1985-06-20','123, Sector 10, Noida - 201301'),(3,'FACULTY00032025','Assistant Professor',2,'2021-01-20','2025-11-25 10:48:38','Rajesh Singh','Suman Lata','Business Owner',NULL,'1990-03-12','Flat 45, Green Apartments, Pune - 411001'),(5,'FACULTY00052025','Lecturer',2,'2022-05-10','2025-11-25 10:49:07','Gopal Varma','Lalita Devi','Farmer',NULL,'1992-11-01','Village & Post: Khera, Dist: Jaipur - 302001'),(7,'FACULTY00072025','Assistant Professor',2,'2025-11-27','2025-11-27 18:15:35','Mr. Wason','Mrs. Wason','Business','Business','1980-10-21','New Delhi'),(8,'FACULTY00082025','Senior Professor',2,'2019-03-25','2025-11-25 10:49:20','Sanjay Patel','Meena Ben','Retired Teacher',NULL,'1975-09-15','B-10, Sunrise Complex, Ahmedabad - 380006'),(12,'FACULTY00122025','Assistant Professor',1,'2025-11-25','2025-11-25 06:59:19','Joshi2','Joshi2','JOB2','House Wife','2003-12-28','Delhi, New Delhi - 110023'),(13,'FACULTY00132025','Senior Professor',1,'2010-06-15','2025-11-28 17:45:19','Suresh Kumar','Sunita Devi','Business','Homemaker','1975-05-12','B-12, Vasant Kunj, New Delhi'),(14,'FACULTY00142025','Assistant Professor',1,'2021-08-20','2025-11-28 17:45:19','Ramesh Gupta','Meena Gupta','Service','Teacher','1990-11-23','Flat 401, Galaxy Apartments, Gurugram'),(15,'FACULTY00152025','Associate Professor',1,'2018-03-10','2025-11-28 17:45:19','Harish Verma','Kavita Verma','Retired','Homemaker','1985-02-14','House No 56, Sector 14, Noida'),(16,'FACULTY00162025','Assistant Professor',1,'2022-01-05','2025-11-28 17:45:19','Vijay Singh','Lata Singh','Farmer','Homemaker','1992-07-30','C-Block, Indirapuram, Ghaziabad'),(17,'FACULTY00172025','Senior Professor',1,'2012-11-12','2025-11-28 17:45:19','Mahesh Rao','Savita Rao','Engineer','Doctor','1978-09-18','A-22, Civil Lines, Jaipur'),(18,'FACULTY00182025','Lecturer',1,'2023-07-01','2025-11-28 17:45:19','Gopal Nair','Lakshmi Nair','Service','Service','1995-04-25','Plot 88, Whitefield, Bangalore'),(19,'FACULTY00192025','Assistant Professor',1,'2021-02-18','2025-11-28 17:45:19','Kishore Das','Rani Das','Business','Artist','1991-12-05','Top Floor, Lake View, Kolkata');
/*!40000 ALTER TABLE `teachers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `timetable_schedule`
--

DROP TABLE IF EXISTS `timetable_schedule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `timetable_schedule` (
  `schedule_id` int NOT NULL AUTO_INCREMENT,
  `courseId` int NOT NULL,
  `semester` int NOT NULL,
  `section` char(2) NOT NULL DEFAULT 'A',
  `day_name` varchar(20) NOT NULL,
  `slot_1` varchar(100) DEFAULT NULL,
  `slot_2` varchar(100) DEFAULT NULL,
  `slot_3` varchar(100) DEFAULT NULL,
  `slot_4` varchar(100) DEFAULT NULL,
  `slot_5` varchar(100) DEFAULT NULL,
  `slot_6` varchar(100) DEFAULT NULL,
  `slot_7` varchar(100) DEFAULT NULL,
  `slot_8` varchar(100) DEFAULT NULL,
  `slot_9` varchar(100) DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`schedule_id`),
  KEY `courseId` (`courseId`),
  CONSTRAINT `timetable_schedule_ibfk_1` FOREIGN KEY (`courseId`) REFERENCES `courses` (`courseId`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `timetable_schedule`
--

LOCK TABLES `timetable_schedule` WRITE;
/*!40000 ALTER TABLE `timetable_schedule` DISABLE KEYS */;
INSERT INTO `timetable_schedule` VALUES (29,1,1,'A','Monday','OS','DBMS','T E A   B R E A K','JAVA','DS','L U N C H   B R E A K','OS (LAB)','OS (LAB)','','2025-11-30 09:50:42'),(30,1,1,'A','Tuesday','CN','JAVA','T E A   B R E A K','OS','DS','L U N C H   B R E A K','CN (LAB)','CN (LAB)','','2025-11-30 09:50:42'),(31,1,1,'A','Wednesday','CN','DBMS','T E A   B R E A K','DS','DBMS','L U N C H   B R E A K','DBMS (LAB)','DBMS (LAB)','','2025-11-30 09:50:42'),(32,1,1,'A','Thursday','JAVA','CN','T E A   B R E A K','OS','DBMS','L U N C H   B R E A K','JAVA (LAB)','JAVA (LAB)','','2025-11-30 09:50:42'),(33,1,1,'A','Friday','JAVA','CN','T E A   B R E A K','DS','OS','L U N C H   B R E A K','DS','OS','','2025-11-30 09:50:42'),(34,1,1,'A','Saturday','CN','DBMS','T E A   B R E A K','JAVA','DBMS','L U N C H   B R E A K','DS','Free','','2025-11-30 09:50:42'),(35,1,1,'A','Sunday','H O L I D A Y','H O L I D A Y','H O L I D A Y','H O L I D A Y','H O L I D A Y','H O L I D A Y','H O L I D A Y','H O L I D A Y','H O L I D A Y','2025-11-30 09:50:42');
/*!40000 ALTER TABLE `timetable_schedule` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `userId` int NOT NULL AUTO_INCREMENT,
  `userName` varchar(20) NOT NULL,
  `email` varchar(50) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` enum('Student','Faculty','Admin') DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `contact` bigint DEFAULT NULL,
  `status` enum('complete','default') DEFAULT 'default',
  PRIMARY KEY (`userId`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'Sayyam singhal','sayyam@gmail.com','$2a$12$N/kOlMKAn2srryKo0/BLq.46vV682useh5VhyZ9wyKOcQWNCVXbYS','Student','2025-11-24 14:27:52',9876543210,'default'),(2,'Parul Arora','parul@gmail.com','$2a$12$N/kOlMKAn2srryKo0/BLq.46vV682useh5VhyZ9wyKOcQWNCVXbYS','Faculty','2025-11-24 14:27:52',9123456780,'complete'),(3,'Sunil Pratap Singh','sunil@gmail.com','$2a$12$N/kOlMKAn2srryKo0/BLq.46vV682useh5VhyZ9wyKOcQWNCVXbYS','Faculty','2025-11-24 14:27:52',9988776655,'complete'),(4,'Shubham Joshi','shubhamjoshi@gmail.com','$2a$12$bYKkn3PG3wDnDp2dFPnBV.0tME1GdercT2KNvZTy1BPfct.KY0WMC','Student','2025-11-24 14:27:52',9090909090,'complete'),(5,'Rakhee Sharma','rakhee@gmail.com','$2a$12$N/kOlMKAn2srryKo0/BLq.46vV682useh5VhyZ9wyKOcQWNCVXbYS','Faculty','2025-11-24 14:27:52',9797979797,'complete'),(6,'Harshit Gupta','harshit@gmail.com','$2a$12$N/kOlMKAn2srryKo0/BLq.46vV682useh5VhyZ9wyKOcQWNCVXbYS','Student','2025-11-24 14:27:52',9665544332,'default'),(7,'Ritika Wason','ritika@gmail.com','$2a$12$oGJHRtAjK4a0qBGXsVStPeRtafKAWAMhoR/uvw8W6VINH2bCrWKOG','Faculty','2025-11-24 14:27:52',9001122334,'complete'),(8,'Arpita Nagpal','arpita@gmail.com','$2a$12$N/kOlMKAn2srryKo0/BLq.46vV682useh5VhyZ9wyKOcQWNCVXbYS','Faculty','2025-11-24 14:27:52',8776655443,'complete'),(9,'Archit Tanwar','archit@gmail.com','$2a$12$N/kOlMKAn2srryKo0/BLq.46vV682useh5VhyZ9wyKOcQWNCVXbYS','Faculty','2025-11-24 14:27:52',9443322110,'default'),(10,'Bhuvan Jain','bhuvan@gmail.com','$2a$12$N/kOlMKAn2srryKo0/BLq.46vV682useh5VhyZ9wyKOcQWNCVXbYS','Faculty','2025-11-24 14:27:52',9556677889,'default'),(11,'admin','admin@gmail.com','$2a$12$N/kOlMKAn2srryKo0/BLq.46vV682useh5VhyZ9wyKOcQWNCVXbYS','Admin','2025-11-24 14:27:52',9898989898,'complete'),(12,'Shubham Johsi','shubhamjoshi45@gmail.com','$2a$12$vV9IeQaRqgz34P3k0WiOcOLOOUg7uovCeiw4CN9E5stmlmRw2FLg.','Faculty','2025-11-25 05:26:32',9891971080,'complete'),(13,'Rajesh Kumar','rajesh.kumar@gmail.com','$2a$12$N/kOlMKAn2srryKo0/BLq.46vV682useh5VhyZ9wyKOcQWNCVXbYS','Faculty','2025-11-24 14:27:52',8585858585,'complete'),(14,'Sneha Gupta','sneha.gupta@gmail.com','$2a$12$N/kOlMKAn2srryKo0/BLq.46vV682useh5VhyZ9wyKOcQWNCVXbYS','Faculty','2025-11-24 14:27:52',8585858585,'complete'),(15,'Amit Verma','amit.verma@gmail.com','$2a$12$N/kOlMKAn2srryKo0/BLq.46vV682useh5VhyZ9wyKOcQWNCVXbYS','Faculty','2025-11-24 14:27:52',8585858585,'complete'),(16,'Pooja Singh','pooja.singh@gmail.com','$2a$12$N/kOlMKAn2srryKo0/BLq.46vV682useh5VhyZ9wyKOcQWNCVXbYS','Faculty','2025-11-24 14:27:52',8585858585,'complete'),(17,'Vikram Rao','vikram.rao@gmail.com','$2a$12$N/kOlMKAn2srryKo0/BLq.46vV682useh5VhyZ9wyKOcQWNCVXbYS','Faculty','2025-11-24 14:27:52',8585858585,'complete'),(18,'Anjali Nair','anjali.nair@gmail.com','$2a$12$N/kOlMKAn2srryKo0/BLq.46vV682useh5VhyZ9wyKOcQWNCVXbYS','Faculty','2025-11-24 14:27:52',8585858585,'complete'),(19,'Rohan Das','rohan.das@gmail.com','$2a$12$N/kOlMKAn2srryKo0/BLq.46vV682useh5VhyZ9wyKOcQWNCVXbYS','Faculty','2025-11-24 14:27:52',8585858585,'complete'),(20,'Rahul','Rahul@gmail.com','$2a$12$pgrtiBGFfIHWH7nm0X50Ne/huno/xSef6OY.h/VioX0vq.sVdDdQ.','Admin','2025-11-29 15:10:27',8787878787,'complete');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-12-09  1:00:52
