-- MySQL dump 10.13  Distrib 8.0.41, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: openfishjs
-- ------------------------------------------------------
-- Server version	8.0.41

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
-- Table structure for table `caughtfish`
--

DROP TABLE IF EXISTS `caughtfish`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `caughtfish` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `fish_id` int NOT NULL,
  `amount` int NOT NULL,
  `record` float NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `fish_id` (`fish_id`),
  CONSTRAINT `caughtfish_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE,
  CONSTRAINT `caughtfish_ibfk_2` FOREIGN KEY (`fish_id`) REFERENCES `fish` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `caughtfish`
--

LOCK TABLES `caughtfish` WRITE;
/*!40000 ALTER TABLE `caughtfish` DISABLE KEYS */;
INSERT INTO `caughtfish` VALUES (1,1,13,2,0.0043662),(2,1,9,10,229.48),(3,1,14,9,1.45),(4,1,10,8,1.98),(5,1,7,11,5.33),(7,1,17,5,0.24),(8,1,21,7,0.563394),(9,1,18,6,19.24),(10,1,5,12,2.35),(11,1,23,5,65.7763),(12,1,15,13,47.83),(13,1,4,5,980.75),(14,1,6,6,12.16),(15,1,2,8,247.85),(16,1,22,3,2.13);
/*!40000 ALTER TABLE `caughtfish` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fish`
--

DROP TABLE IF EXISTS `fish`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fish` (
  `id` int NOT NULL AUTO_INCREMENT,
  `species` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `wiki` varchar(255) DEFAULT NULL,
  `rarity` varchar(255) DEFAULT NULL,
  `distance` int DEFAULT NULL,
  `min_weight` float DEFAULT NULL,
  `max_weight` float DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fish`
--

LOCK TABLES `fish` WRITE;
/*!40000 ALTER TABLE `fish` DISABLE KEYS */;
INSERT INTO `fish` VALUES (1,'Atlantic Cod','A cold-water fish known for its flaky white flesh, historically significant in North Atlantic fisheries','https://en.wikipedia.org/wiki/Atlantic_cod','common',100,5,12),(2,'Bluefin Tuna','A large, fast-swimming pelagic fish prized for sushi and sashimi','https://en.wikipedia.org/wiki/Bluefin_tuna','common',20,225,250),(3,'Clownfish','A small, colorful reef fish famous for its symbiotic relationship with sea anemones','https://en.wikipedia.org/wiki/Clownfish','common',100,2,3),(4,'Great White Shark','A formidable apex predator found in coastal surface waters of all major oceans','https://en.wikipedia.org/wiki/Great_white_shark','rare',50,680,1100),(5,'Rainbow Trout','A freshwater species popular among anglers, known for its vibrant coloration','https://en.wikipedia.org/wiki/Rainbow_trout','common',30,0.5,2.5),(6,'Common Carp','A widespread freshwater fish, often found in ponds and lakes','https://en.wikipedia.org/wiki/Common_carp','common',10,2,14),(7,'Atlantic Salmon','An anadromous fish species prized for its rich, flavorful meat','https://en.wikipedia.org/wiki/Atlantic_salmon','rare',20,3.6,5.4),(8,'Coelacanth','The Coelacanth is a rare, ancient fish once thought extinct, known for its massive size and deep-sea habitat','https://en.wikipedia.org/wiki/Coelacanth','rare',30,80,100),(9,'Swordfish','A large, predatory fish recognizable by its long, flat bill','https://en.wikipedia.org/wiki/Swordfish','common',30,50,250),(10,'Pufferfish','Known for its ability to inflate and its potent toxins','https://en.wikipedia.org/wiki/Tetraodontidae','rare',50,0.5,2),(11,'Anglerfish','Deep-sea dwellers with a bioluminescent lure to attract prey','https://en.wikipedia.org/wiki/Anglerfish','epic',130,0.3,18),(12,'Barracuda','A sleek, predatory fish with a fearsome reputation','https://en.wikipedia.org/wiki/Barracuda','rare',115,1.5,23),(13,'Betta Fish','Also known as Siamese fighting fish, noted for their vivid colors and aggression','https://en.wikipedia.org/wiki/Betta','common',5,0.014,0.028),(14,'Bluegill','A common freshwater fish in North America, popular among anglers','https://en.wikipedia.org/wiki/Bluegill','common',5,0.1,2),(15,'Catfish','Named for their prominent barbels, resembling a cat\'s whiskers','https://en.wikipedia.org/wiki/Catfish','common',10,0.5,50),(16,'Chinook Salmon','The largest species of Pacific salmon, also known as king salmon','https://en.wikipedia.org/wiki/Chinook_salmon','rare',70,4.5,22),(17,'Discus Fish','A popular aquarium fish known for its distinctive shape and bright colors','https://en.wikipedia.org/wiki/Discus_(fish)','rare',0,0.1,0.25),(18,'Electric Eel','Capable of generating powerful electric shocks for hunting and self-defense','https://en.wikipedia.org/wiki/Electric_eel','epic',0,1.5,20),(19,'Flounder','A flatfish species known for its unique body shape and both eyes on one side','https://en.wikipedia.org/wiki/Flounder','rare',80,0.5,14),(20,'Giant Trevally','A large predatory fish found in tropical waters, known for its strength','https://en.wikipedia.org/wiki/Giant_trevally','rare',90,10,80),(21,'Goldfish','One of the most common aquarium fish, known for its golden-orange coloration','https://en.wikipedia.org/wiki/Goldfish','common',5,0.1,1.5),(22,'Haddock','A North Atlantic fish, important in commercial fisheries','https://en.wikipedia.org/wiki/Haddock','rare',60,0.5,3.6),(23,'Halibut','A large flatfish species, valued for its meat','https://en.wikipedia.org/wiki/Halibut','rare',40,4,200),(24,'Herring','A small, oily fish found in large schools, significant in global fisheries','https://en.wikipedia.org/wiki/Herring','common',110,0.1,1.5),(25,'Lionfish','Known for its venomous spines and striking appearance','https://en.wikipedia.org/wiki/Lionfish','epic',85,0.5,1.5);
/*!40000 ALTER TABLE `fish` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `points` int NOT NULL,
  `role` varchar(255) DEFAULT NULL,
  `username` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'ensil@testing.com','$2a$12$agzGR3Zca8ZVakLouI8MLu6NWDwnmPJVEmNuqHToXox1nTXvyf5Z2',91519,NULL,'Ensil');
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

-- Dump completed on 2025-04-26 22:05:56
