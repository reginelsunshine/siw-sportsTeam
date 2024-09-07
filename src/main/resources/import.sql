-- Inserimento di President
INSERT INTO president (id, first_name, last_name, fiscal_code, date_of_birth, place_of_birth) VALUES
(1, 'Gabriele', 'Gravina', 'GRVGRL55T25H501X', '1953-10-05', 'Castellaneta'),
(2, 'Hany', 'Abo Rida', 'ABRHNY60S22Z336P', '1964-08-14', 'Port Said');


-- Inserimento di Team
INSERT INTO team (id, name, foundation_year, address, president_id) VALUES
(1, 'Italia', 1898, 'Via Allegri 14, Roma', 1),
(2, 'Egitto', 1921, 'Cairo International Stadium, Cairo', 2);


-- Inserimento di Player
INSERT INTO player (id, first_name, last_name, date_of_birth, place_of_birth, role, start_date_of_registration, end_date_of_registration, team_id) VALUES
(1, 'Gianluigi', 'Donnarumma', '1999-02-25', 'Castellammare di Stabia', 'Goalkeeper', '2016-09-01', NULL, 1),
(2, 'Francesco', 'Acerbi', '1988-02-10', 'Vizzolo Predabissi', 'Defender', '2018-11-20', NULL, 1),
(3, 'Mohamed', 'Salah', '1992-06-15', 'Nagrig', 'Forward', '2011-09-03', NULL, 2),
(4, 'Ahmed', 'Hegazi', '1991-01-25', 'Ismailia', 'Defender', '2011-09-03', NULL, 2);

