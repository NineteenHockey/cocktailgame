INSERT INTO gamedata (id, name, val)
SELECT 1, 'highscore', 0
WHERE NOT EXISTS (SELECT 1 FROM gamedata WHERE name = 'highscore');