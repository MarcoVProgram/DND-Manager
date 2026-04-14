-- MySQL dump 10.13  Distrib 8.0.44, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: mydnd
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
-- Table structure for table `tag`
--

DROP TABLE IF EXISTS `tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tag` (
  `id` int NOT NULL AUTO_INCREMENT,
  `tag` varchar(20) NOT NULL,
  `lore` varchar(4100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `tag` (`tag`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tag`
--

LOCK TABLES `tag` WRITE;
/*!40000 ALTER TABLE `tag` DISABLE KEYS */;
INSERT INTO `tag` VALUES (1,'El Dorado','[Damocles]\nWeapon (longsword), artifact (requires attunement by Greed)\n\nYou gain a +3 bonus to attack and damage rolls made with this weapon, and deals an extra 3d2 auroric damage. You gain proficiency on Persuasion and Perception when your sword is attuned. If you already are proficient, you add the sword\'s +3 bonus to these proficiencies.\n\n[Equivalent Exchange]\nAs part of your attack action, you can trasmute gold pieces into power for a single strike. The conversion rate is 1 gold piece to 1d2 extra auroric damage, but you have a spending limit equal to your paladin level * charisma modifier, which does not refresh until you finish a long rest.\n\n[Gold Spellcasting]\nThis weapon has 9 charges. You can use a bonus action and expend 1 or more of its charges to cast one of the following spells (save DC 24): Bless (1 charge), Cure Wounds (1 charge), Suggestion (2 charges), Zone of Truth (2 charges), Slow (3 charges), Commune* (5 charges) and Word of Recall (6 charges).\n* You may only commune with Aurelis, sometimes she may not answer.\n\nThe weapon regains 1d2 expended charges daily at dawn, after performing a set of exercises (Dailies).\nAlternatively, as an action, you may spend 1 gold piece to forcibly regain 1d2 expended charges, which is limited by your spending limit stated above.\n\n[Golden Key]\nYou can use an action to tear open hole in the fabric of space for 1 minute to a target destination that you have seen before or you have been to. The portal is 10ft tall and 10 ft. wide and is appears to be molten gold.\n\nDeities and other planar rulers can prevent rifts created by this sword from opening in their presence or anywhere within their domains. Once used, it can\'t be used again until next dawn.\n\n[Wrath of the Golden God]\nEach time a creature is hit by this sword, it must succeed a Constitution saving throw. On a failure, it gains a stacking debuff. Each stack reduces the creature\'s speed by 5 ft. as their bodies are slowly transmuted into gold. A creature can remove the debuff entirely by using its action to scrape off all the gold skin, dealing unmitigatable 1d6 damage per stack removed.\n\nThe debuff can stack infinitely, but once it reaches 5 stacks, the creature must begin making Constitution saving throws at the end of each of its turns, upon failing, it is petrified into a golden statue untill freed by the greater restoration spell or similar magic.');
/*!40000 ALTER TABLE `tag` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-04-14  2:28:41
