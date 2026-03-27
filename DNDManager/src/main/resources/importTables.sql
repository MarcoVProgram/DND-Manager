DROP DATABASE IF EXISTS myDND;
CREATE DATABASE IF NOT EXISTS myDND;
USE myDND;
                     
                     
CREATE TABLE tag (
	id 				INT 						AUTO_INCREMENT,
    tag 			VARCHAR(20),
    
    PRIMARY KEY (id),
    UNIQUE  KEY (tag)
);

CREATE TABLE player_character (
	id 				INT 				AUTO_INCREMENT,
    first_name 		CHAR(20) 			NOT NULL,
    full_name 		VARCHAR(50) 		NOT NULL,
    class		 	VARCHAR(80)			NOT NULL,
    subclass		VARCHAR(80),
    levels 			TINYINT				NOT NULL,
    multiclass		BOOL				NOT NULL,
    specie			VARCHAR(30) 		NOT NULL,
    gender			ENUM('M', 'F', 'O') NOT NULL,
    player_name		VARCHAR(30)			NOT NULL,
    current_status 	ENUM('ALIVE',
						'DEAD',
                        'MISSING',
                        'UNKNOWN') 		NOT NULL,
	notes 			VARCHAR(200) 		NOT NULL,
    
    PRIMARY KEY (id),
    UNIQUE 	KEY (full_name)
);

CREATE TABLE reward (
	id 				INT 				AUTO_INCREMENT,
    typeReward		VARCHAR(30)			NOT NULL,
    idPlayer		INT					NOT NULL,
    textDescription	VARCHAR(4000),
    linkRewards 	VARCHAR(200),
    
    PRIMARY KEY (id),
    FOREIGN KEY (idPlayer) REFERENCES player_character (id) ON DELETE CASCADE
)