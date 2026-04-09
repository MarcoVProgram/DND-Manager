DROP DATABASE IF EXISTS myDND;
CREATE DATABASE IF NOT EXISTS myDND;
USE myDND;
                     
                     
CREATE TABLE tag (
	id 				INT 						AUTO_INCREMENT,
    tag 			VARCHAR(20)                 NOT NULL,
    lore            VARCHAR(4000),
    
    PRIMARY KEY (id),
    UNIQUE  KEY (tag)
);

CREATE TABLE player_character (
	id 				INT                     AUTO_INCREMENT,
    first_name 		CHAR(20) 			    NOT NULL,
    full_name 		VARCHAR(50) 		    NOT NULL,
    specie			VARCHAR(30) 		    NOT NULL,
    gender			ENUM('M', 'F', 'O')     NOT NULL,
    alignment       ENUM('UA',
                        'LG', 'LN', 'LE',
                        'NG', 'TN', 'NE',
                        'CG', 'CN', 'CE')   NOT NULL,
    player_name		VARCHAR(30)			    NOT NULL,
    current_status 	ENUM('ALIVE',
						'DEAD',
                        'MISSING',
                        'UNKNOWN') 		    NOT NULL,
	notes 			VARCHAR(200) 		    NOT NULL,
    
    PRIMARY KEY (id),
    UNIQUE 	KEY (full_name)
);

CREATE TABLE class (
    className       VARCHAR(20),

    PRIMARY KEY (className)
);

CREATE TABLE class_instance (
	className		VARCHAR(20),
    idPC			INT,
    levels			TINYINT,
    subclass		VARCHAR(30),
    
    FOREIGN KEY (idPC) REFERENCES player_character (id) ON DELETE CASCADE,
	FOREIGN KEY (className) REFERENCES class (className) ON DELETE CASCADE,
	PRIMARY KEY (className, idPC)
);

CREATE TABLE reward (
    nameReward      VARCHAR(30)         NOT NULL,
	id 				INT 				AUTO_INCREMENT,
    typeReward		VARCHAR(30)			NOT NULL,
    idPC		    INT					NOT NULL,
    textDescription	VARCHAR(4000),
    linkRewards 	VARCHAR(200),
    
    PRIMARY KEY (id),
    UNIQUE KEY (nameReward),
    FOREIGN KEY (idPC) REFERENCES player_character (id) ON DELETE CASCADE
);

CREATE TABLE non_player_character (
	id 				    INT 				    AUTO_INCREMENT,
    first_name 		    CHAR(20) 			    NOT NULL,
    full_name 		    VARCHAR(50) 		    NOT NULL,
    challenge_rating 	TINYINT				    NOT NULL,
    specie			    VARCHAR(30) 		    NOT NULL,
    gender			    ENUM('M', 'F', 'O')     NOT NULL,
    alignment           ENUM('UA',
                            'LG', 'LN', 'LE',
                            'NG', 'TN', 'NE',
                            'CG', 'CN', 'CE')   NOT NULL,
    dm_name 		    VARCHAR(30)			    NOT NULL,
    current_status 	    ENUM('ALIVE',
						    'DEAD',
                            'MISSING',
                            'UNKNOWN') 		    NOT NULL,
	notes 			    VARCHAR(200) 		    NOT NULL,

    PRIMARY KEY (id),
    UNIQUE 	KEY (full_name)
);

CREATE TABLE treasure (
    nameTreasure    VARCHAR(30)         NOT NULL,
	id 				INT 				AUTO_INCREMENT,
    typeTreasure	VARCHAR(30)			NOT NULL,
    idNPC      		INT					NOT NULL,
    textDescription	VARCHAR(4000),
    hasBeenFound    BOOL                NOT NULL,

    PRIMARY KEY (id),
    UNIQUE KEY (nameTreasure),
    FOREIGN KEY (idNPC) REFERENCES non_player_character (id) ON DELETE CASCADE
);

CREATE TABLE tag_character (
    idTag 			INT,
    idPC        INT,

    FOREIGN KEY (idPC) REFERENCES player_character (id) ON DELETE CASCADE,
    FOREIGN KEY (idTag) REFERENCES tag (id) ON DELETE CASCADE,
    PRIMARY KEY (idTag, idPC)
);

CREATE TABLE tag_npc (
    idTag 			INT,
    idNPC        INT,

    FOREIGN KEY (idNPC) REFERENCES non_player_character (id) ON DELETE CASCADE,
    FOREIGN KEY (idTag) REFERENCES tag (id) ON DELETE CASCADE,
    PRIMARY KEY (idTag, idNPC)
);

CREATE TABLE tag_reward (
    idTag 			INT,
    idReward        INT,

    FOREIGN KEY (idReward) REFERENCES reward (id) ON DELETE CASCADE,
    FOREIGN KEY (idTag) REFERENCES tag (id) ON DELETE CASCADE,
    PRIMARY KEY (idTag, idReward)
);

CREATE TABLE tag_treasure (
    idTag 			INT,
    idTreasure      INT,

    FOREIGN KEY (idTreasure) REFERENCES treasure (id) ON DELETE CASCADE,
    FOREIGN KEY (idTag) REFERENCES tag (id) ON DELETE CASCADE,
    PRIMARY KEY (idTag, idTreasure)
);