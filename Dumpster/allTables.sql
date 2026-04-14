CREATE DATABASE  IF NOT EXISTS `mydnd` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `mydnd`;
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
-- Table structure for table `class`
--

DROP TABLE IF EXISTS `class`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `class` (
  `className` varchar(20) NOT NULL,
  PRIMARY KEY (`className`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `class`
--

LOCK TABLES `class` WRITE;
/*!40000 ALTER TABLE `class` DISABLE KEYS */;
INSERT INTO `class` VALUES ('Alchemist'),('Artificer'),('Barbarian'),('Bard'),('Captain'),('Cleric'),('Druid'),('Fighter'),('Gunslinger'),('Monk'),('Paladin'),('Ranger'),('Rogue'),('Sorcerer'),('Warden'),('Warlock'),('Wizard');
/*!40000 ALTER TABLE `class` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `class_instance`
--

DROP TABLE IF EXISTS `class_instance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `class_instance` (
  `className` varchar(20) NOT NULL,
  `idPC` int NOT NULL,
  `levels` tinyint NOT NULL,
  `subclass` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`className`,`idPC`),
  KEY `idPC` (`idPC`),
  CONSTRAINT `class_instance_ibfk_1` FOREIGN KEY (`idPC`) REFERENCES `player_character` (`id`) ON DELETE CASCADE,
  CONSTRAINT `class_instance_ibfk_2` FOREIGN KEY (`className`) REFERENCES `class` (`className`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `class_instance`
--

LOCK TABLES `class_instance` WRITE;
/*!40000 ALTER TABLE `class_instance` DISABLE KEYS */;
INSERT INTO `class_instance` VALUES ('Paladin',2,23,'Oath of Conquest'),('Sorcerer',1,25,'Phoenix Sorcerer'),('Wizard',3,22,'School of Evocation');
/*!40000 ALTER TABLE `class_instance` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `non_player_character`
--

DROP TABLE IF EXISTS `non_player_character`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `non_player_character` (
  `id` int NOT NULL AUTO_INCREMENT,
  `first_name` char(20) NOT NULL,
  `full_name` varchar(50) NOT NULL,
  `challenge_rating` tinyint NOT NULL,
  `specie` varchar(30) NOT NULL,
  `gender` enum('M','F','O') NOT NULL,
  `alignment` enum('UA','LG','LN','LE','NG','TN','NE','CG','CN','CE') NOT NULL,
  `dm_name` varchar(30) NOT NULL,
  `current_status` enum('ALIVE','DEAD','MISSING','UNKNOWN') NOT NULL,
  `notes` varchar(200) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `full_name` (`full_name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `non_player_character`
--

LOCK TABLES `non_player_character` WRITE;
/*!40000 ALTER TABLE `non_player_character` DISABLE KEYS */;
INSERT INTO `non_player_character` VALUES (1,'The Queen','The Witch of Chaos',29,'Archfey','O','CN','markoop','ALIVE',''),(2,'Vecna','Vecna the Archlich',26,'God','M','NE','none','DEAD','Vecna is the evil villain that everyone has to stop from returning to its full power');
/*!40000 ALTER TABLE `non_player_character` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `player_character`
--

DROP TABLE IF EXISTS `player_character`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `player_character` (
  `id` int NOT NULL AUTO_INCREMENT,
  `first_name` char(20) NOT NULL,
  `full_name` varchar(50) NOT NULL,
  `specie` varchar(30) NOT NULL,
  `gender` enum('M','F','O') NOT NULL,
  `alignment` enum('UA','LG','LN','LE','NG','TN','NE','CG','CN','CE') NOT NULL,
  `player_name` varchar(30) NOT NULL,
  `current_status` enum('ALIVE','DEAD','MISSING','UNKNOWN') NOT NULL,
  `notes` varchar(200) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `full_name` (`full_name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `player_character`
--

LOCK TABLES `player_character` WRITE;
/*!40000 ALTER TABLE `player_character` DISABLE KEYS */;
INSERT INTO `player_character` VALUES (1,'Xins','Xins Fenghuang','Phoenix','F','NG','markoop','ALIVE','The first phoenix.'),(2,'Abaddon','Abaddon','Variant Human','M','NG','chatblanc.','ALIVE','The king of El Dorado'),(3,'Agnea','Agnea Credieu','Variant Human','F','TN','devaexmachina','ALIVE','');
/*!40000 ALTER TABLE `player_character` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tag`
--

LOCK TABLES `tag` WRITE;
/*!40000 ALTER TABLE `tag` DISABLE KEYS */;
INSERT INTO `tag` VALUES (1,'El Dorado','[Damocles]\nWeapon (longsword), artifact (requires attunement by Greed)\n\nYou gain a +3 bonus to attack and damage rolls made with this weapon, and deals an extra 3d2 auroric damage. You gain proficiency on Persuasion and Perception when your sword is attuned. If you already are proficient, you add the sword\'s +3 bonus to these proficiencies.\n\n[Equivalent Exchange]\nAs part of your attack action, you can trasmute gold pieces into power for a single strike. The conversion rate is 1 gold piece to 1d2 extra auroric damage, but you have a spending limit equal to your paladin level * charisma modifier, which does not refresh until you finish a long rest.\n\n[Gold Spellcasting]\nThis weapon has 9 charges. You can use a bonus action and expend 1 or more of its charges to cast one of the following spells (save DC 24): Bless (1 charge), Cure Wounds (1 charge), Suggestion (2 charges), Zone of Truth (2 charges), Slow (3 charges), Commune* (5 charges) and Word of Recall (6 charges).\n* You may only commune with Aurelis, sometimes she may not answer.\n\nThe weapon regains 1d2 expended charges daily at dawn, after performing a set of exercises (Dailies).\nAlternatively, as an action, you may spend 1 gold piece to forcibly regain 1d2 expended charges, which is limited by your spending limit stated above.\n\n[Golden Key]\nYou can use an action to tear open hole in the fabric of space for 1 minute to a target destination that you have seen before or you have been to. The portal is 10ft tall and 10 ft. wide and is appears to be molten gold.\n\nDeities and other planar rulers can prevent rifts created by this sword from opening in their presence or anywhere within their domains. Once used, it can\'t be used again until next dawn.\n\n[Wrath of the Golden God]\nEach time a creature is hit by this sword, it must succeed a Constitution saving throw. On a failure, it gains a stacking debuff. Each stack reduces the creature\'s speed by 5 ft. as their bodies are slowly transmuted into gold. A creature can remove the debuff entirely by using its action to scrape off all the gold skin, dealing unmitigatable 1d6 damage per stack removed.\n\nThe debuff can stack infinitely, but once it reaches 5 stacks, the creature must begin making Constitution saving throws at the end of each of its turns, upon failing, it is petrified into a golden statue untill freed by the greater restoration spell or similar magic.'),(2,'Vecna','Vecna is a very bad evil Lich\n\nHe ascended to Godhood.');
/*!40000 ALTER TABLE `tag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tag_character`
--

DROP TABLE IF EXISTS `tag_character`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tag_character` (
  `idTag` int NOT NULL,
  `idPC` int NOT NULL,
  PRIMARY KEY (`idTag`,`idPC`),
  KEY `idPC` (`idPC`),
  CONSTRAINT `tag_character_ibfk_1` FOREIGN KEY (`idPC`) REFERENCES `player_character` (`id`) ON DELETE CASCADE,
  CONSTRAINT `tag_character_ibfk_2` FOREIGN KEY (`idTag`) REFERENCES `tag` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tag_character`
--

LOCK TABLES `tag_character` WRITE;
/*!40000 ALTER TABLE `tag_character` DISABLE KEYS */;
INSERT INTO `tag_character` VALUES (1,2);
/*!40000 ALTER TABLE `tag_character` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tag_npc`
--

DROP TABLE IF EXISTS `tag_npc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tag_npc` (
  `idTag` int NOT NULL,
  `idNPC` int NOT NULL,
  PRIMARY KEY (`idTag`,`idNPC`),
  KEY `idNPC` (`idNPC`),
  CONSTRAINT `tag_npc_ibfk_1` FOREIGN KEY (`idNPC`) REFERENCES `non_player_character` (`id`) ON DELETE CASCADE,
  CONSTRAINT `tag_npc_ibfk_2` FOREIGN KEY (`idTag`) REFERENCES `tag` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tag_npc`
--

LOCK TABLES `tag_npc` WRITE;
/*!40000 ALTER TABLE `tag_npc` DISABLE KEYS */;
INSERT INTO `tag_npc` VALUES (2,2);
/*!40000 ALTER TABLE `tag_npc` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tag_reward`
--

DROP TABLE IF EXISTS `tag_reward`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tag_reward` (
  `idTag` int NOT NULL,
  `idReward` int NOT NULL,
  PRIMARY KEY (`idTag`,`idReward`),
  KEY `idReward` (`idReward`),
  CONSTRAINT `tag_reward_ibfk_1` FOREIGN KEY (`idReward`) REFERENCES `reward` (`id`) ON DELETE CASCADE,
  CONSTRAINT `tag_reward_ibfk_2` FOREIGN KEY (`idTag`) REFERENCES `tag` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tag_reward`
--

LOCK TABLES `tag_reward` WRITE;
/*!40000 ALTER TABLE `tag_reward` DISABLE KEYS */;
INSERT INTO `tag_reward` VALUES (1,1);
/*!40000 ALTER TABLE `tag_reward` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tag_treasure`
--

DROP TABLE IF EXISTS `tag_treasure`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tag_treasure` (
  `idTag` int NOT NULL,
  `idTreasure` int NOT NULL,
  PRIMARY KEY (`idTag`,`idTreasure`),
  KEY `idTreasure` (`idTreasure`),
  CONSTRAINT `tag_treasure_ibfk_1` FOREIGN KEY (`idTreasure`) REFERENCES `treasure` (`id`) ON DELETE CASCADE,
  CONSTRAINT `tag_treasure_ibfk_2` FOREIGN KEY (`idTag`) REFERENCES `tag` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tag_treasure`
--

LOCK TABLES `tag_treasure` WRITE;
/*!40000 ALTER TABLE `tag_treasure` DISABLE KEYS */;
INSERT INTO `tag_treasure` VALUES (2,3),(2,4);
/*!40000 ALTER TABLE `tag_treasure` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `treasure`
--

DROP TABLE IF EXISTS `treasure`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `treasure` (
  `nameTreasure` varchar(30) NOT NULL,
  `id` int NOT NULL AUTO_INCREMENT,
  `typeTreasure` varchar(30) NOT NULL,
  `idNPC` int NOT NULL,
  `textDescription` varchar(4100) DEFAULT NULL,
  `hasBeenFound` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nameTreasure` (`nameTreasure`),
  KEY `idNPC` (`idNPC`),
  CONSTRAINT `treasure_ibfk_1` FOREIGN KEY (`idNPC`) REFERENCES `non_player_character` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `treasure`
--

LOCK TABLES `treasure` WRITE;
/*!40000 ALTER TABLE `treasure` DISABLE KEYS */;
INSERT INTO `treasure` VALUES ('Lenses of Chaos',1,'Wondrous item',1,'',0),('Chaotic Feywild Shard',2,'Wondrous item, very rare',1,'These shards adapt to the user.',1),('Eye of Vecna',3,'Wondrous item',2,'Seldom is the name of Vecna spoken except in a hushed voice. Vecna was, in his time, one of the mightiest of all wizards. Through dark magic and conquest, he forged a terrible empire. For all his power, Vecna couldn\'t escape his own mortality. He began to fear death and take steps to prevent his end from ever coming about.\n\nOrcus, the demon prince of undeath, taught Vecna a ritual that would allow him to live on as a lich. Beyond death, he became the greatest of all liches. Even though his body gradually withered and decayed, Vecna continued to expand his evil dominion. So formidable and hideous was his temper that his subjects feared to speak his name. He was the Whispered One, the Master of the Spider Throne, the Undying King, and the Lord of the Rotted Tower.\n\nSome say that Vecna\'s lieutenant Kas coveted the Spider Throne for himself, or that the sword his lord made for him seduced him into rebellion. Whatever the reason, Kas brought the Undying King\'s rule to an end in a terrible battle that left Vecna\'s tower a heap of ash. Of Vecna, all that remained were one hand and one eye, grisly artifacts that still seek to work the Whispered One\'s will in the world.\n\nThe Eye of Vecna and the Hand of Vecna might be found together or separately. The eye looks like a bloodshot organ torn free from the socket. The hand is a mummified and shriveled left extremity.\n\nTo attune to the eye, you must gouge out your own eye and press the artifact into the empty socket. The eye grafts itself to your head and remains there until you die. Once in place, the eye transforms into a golden eye with a slit for a pupil, much like that of a cat. If the eye is ever removed, you die.',0),('Hand of Vecna',4,'Wondrous item',2,'',0);
/*!40000 ALTER TABLE `treasure` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-04-14 22:53:49
