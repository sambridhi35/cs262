--
-- This SQL script builds a monopoly database, deleting any pre-existing version.
-- @author Sebrina Zeleke and Sambridhi Acharya
-- @author kvlinden
-- @version Summer, 2015
--

-- Drop previous versions of the tables if they they exist, in reverse order of foreign keys.
DROP TABLE IF EXISTS GameInProgress;
DROP TABLE IF EXISTS PlayerGame;
DROP TABLE IF EXISTS Game;
DROP TABLE IF EXISTS Player;


-- Create the schema.
CREATE TABLE Game (
	ID integer PRIMARY KEY, 
	time timestamp
	);

CREATE TABLE Player (
	ID integer PRIMARY KEY, 
	emailAddress varchar(50) NOT NULL,
	name varchar(50)
	);

CREATE TABLE PlayerGame (
	gameID integer REFERENCES Game(ID), 
	playerID integer REFERENCES Player(ID),
	score integer
	);

CREATE TABLE GameInProgress(
	gameID integer REFERENCES Game(ID), 
	playerID integer REFERENCES Player(ID),
	cash money,
	houseNum integer,
	hotelNum integer,
	properties varchar,
	pieceLocation varchar(50)	
	);

-- Allow users to select data from the tables.
GRANT SELECT ON Game TO PUBLIC;
GRANT SELECT ON Player TO PUBLIC;
GRANT SELECT ON PlayerGame TO PUBLIC;
GRANT SELECT ON GameInProgress TO PUBLIC;

-- Add sample records.
--Insert to Game: ID, time
INSERT INTO Game VALUES (1, '2006-06-27 08:00:00');
INSERT INTO Game VALUES (2, '2006-06-28 13:20:00');
INSERT INTO Game VALUES (3, '2006-06-29 18:41:00');

--Insert to Player: ID, emailAddress, name
INSERT INTO Player(ID, emailAddress) VALUES (1, 'me@calvin.edu');
INSERT INTO Player VALUES (2, 'king@gmail.edu', 'The King');
INSERT INTO Player VALUES (3, 'dog@gmail.edu', 'Dogbreath');

--Insert to PlayerGame: gameID, playerID, score
INSERT INTO PlayerGame VALUES (1, 1, 0.00);
INSERT INTO PlayerGame VALUES (1, 2, 0.00);
INSERT INTO PlayerGame VALUES (1, 3, 2350.00);
INSERT INTO PlayerGame VALUES (2, 1, 1000.00);
INSERT INTO PlayerGame VALUES (2, 2, 0.00);
INSERT INTO PlayerGame VALUES (2, 3, 500.00);
INSERT INTO PlayerGame VALUES (3, 2, 0.00);
INSERT INTO PlayerGame VALUES (3, 3, 5500.00);

--Insert to GameInProgress: gameID, playerID, cash, houseNum, hotelNum, properties, pieceLocation
INSERT INTO GameInProgress VALUES(1, 1, 100.00, 5, 5, 'Michigan', 'Burton street');
INSERT INTO GameInProgress VALUES(1, 2, 200.00, 7, 20, 'DC', 'something street'); 
INSERT INTO GameInProgress VALUES(2, 3, 300.00, 4, 10, 'Texas', 'another street');
INSERT INTO GameInProgress VALUES(3, 1, 460.00, 3, 1, 'Florida', 'Lake Chicago drive'); 
INSERT INTO GameInProgress VALUES(1, 3, 550.00, 2, 0, 'Kansas', 'Grace dayssss street'); 
