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
-- Table structure for table `reward`
--

DROP TABLE IF EXISTS `reward`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reward` (
  `nameReward` varchar(30) NOT NULL,
  `id` int NOT NULL AUTO_INCREMENT,
  `typeReward` varchar(30) NOT NULL,
  `idPC` int NOT NULL,
  `textDescription` varchar(4100) DEFAULT NULL,
  `linkRewards` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nameReward` (`nameReward`),
  KEY `idPC` (`idPC`),
  CONSTRAINT `reward_ibfk_1` FOREIGN KEY (`idPC`) REFERENCES `player_character` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reward`
--

LOCK TABLES `reward` WRITE;
/*!40000 ALTER TABLE `reward` DISABLE KEYS */;
INSERT INTO `reward` VALUES ('Damocles',1,'Sword (Artifact)',2,'','https://discord.com/channels/1043068908715900989/1292558081632239840/1292564873384820747'),('Everlasting Immortality',2,'Epic Destiny',1,'','https://discord.com/channels/1043068908715900989/1287466127768948831/1287472608476135580'),('Loomheart Amulet (Awakened)',3,'Wondrous Item, relic',1,'```Epic Destiny\n[Everlasting Immortality]\n\nThe Phoenix is more than just a creature of myth. It is a symbol of eternal life, a living embodiment of flame, destruction, and rebirth. As a Phoenix Incarnate, you are destined to ascend into the immortal cycle of death and resurrection, kindling a soul of undying fire. The flame within you is pure, cleansing, and unyielding. With each trial, you burn brighter, ready to reduce all obstacles to ash before rising renewed from the embers.\n\nYour journey in this destiny is not simply a pursuit of raw power; it is a spiritual metamorphosis. You will come to embody the Phoenix itself, balancing the forces of destruction and creation, death and life, as you stride toward your final apotheosis. A being that defies mortality, shrouded in flames that purify, heal, and destroy.\n\nEternal Life\n- The very soul of the Immortal dwells within you. For every 10 years that pass, your body ages only 1 year.\nIn addition, You gain the Find Familiar spell, and conjure a Mini Phoenix as your companion. (Using the Owl statblock)\n\nAncient Resurgence\n- You increase Intelligence & Dexterity by 2. This score increase, further increases your ability score from 20 -> 22, or 30 -> 32.\n\nFlame’s Command\n- When Mantle of Flame is active at anytime, Instead of dealing your charisma modifier to damage, You instead deal 1 point of fire damage per dice rolled.\n\nAwe of the Eternal Flame\n- You gain a +2 bonus to all saving throws against charm, fear, and stun effects. Additionally, you can ignore the first instance of death saving throws in an encounter.\n\nPurifying Flames [ Unique Metamagic ]\n- Whenever you cast a spell that deals fire or radiant damage, you spend a number of sorcery points equal to the spell\'s level to target a number of creatures within 30ft of the target of choice equal to your charisma modifier [minimum of 1] and heal them equal to you [sorcerer level / 2 + proficiency bonus + charisma modifier]\n- In addition, You may double the amount of sorcery points, and instead the multiplier for the healing are instead [sorcerer level + proficiency bonus + charisma modifier]\n\nCleansing Flames [ Unique Metamagic ]\n- Whenever you cast a spell that deals fire or radiant damage, you spend a number of sorcery points equal to the spell\'s level to apply the effects of Dispel Magic within the region. Additionally, you do not roll a spellcasting check with this metamagic.```','https://discord.com/channels/1043068908715900989/1479247112972664935/1479299150792364225');
/*!40000 ALTER TABLE `reward` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-04-14  2:28:40
