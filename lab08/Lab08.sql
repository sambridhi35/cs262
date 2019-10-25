--Sambridhi Acharya and Sebrina Zeleke
--Exercise 8.1
--a
--SELECT * FROM Game ORDER BY Game.time  DESC;
--b
--SELECT * FROM Game WHERE Game.time >= date '10/13/2019' and Game.time < date '10/20/2019';
--c
--SELECT name FROM Player where Player.name is not NULL;
--d
--SELECT PlayerID FROM PlayerGame WHERE PlayerGame.score > 2000;
--e
--SELECT name FROM Player where Player.emailAddress LIKE '%gmail%';


--Exercise 8.2
--a
--SELECT PlayerGame.score 
--FROM Player, PlayerGame 
--WHERE Player.ID = PlayerGame.PlayerID
 --AND Player.name = 'The King' 
--ORDER BY score DESC;

--b
--SELECT Player.name
--FROM Player, Game, PlayerGame
--WHERE Player.ID = PlayerGame.PlayerID AND Game.ID = PlayerGame.GameID
--AND Game.time = '2006-06-28 13:20:00' 
--ORDER BY score DESC LIMIT 1; 

--c
--They are just making sure that the players just share the same name but are different people with distinct ID and characteristics. 

--d
--Yes. For example, if we have an employement table where there is hirearchial positions we might have to merge the table once to see who reports to whom. 

