-- Inserimento di President
INSERT INTO president (id, first_name, last_name, fiscal_code, president_code, date_of_birth, place_of_birth) 
VALUES 
(1, 'Gabriele', 'Gravina', 'GRVGRL55T25H501X', 'PRES123', '1953-10-05', 'Castellaneta'),
(2, 'Hany', 'Abo Rida', 'ABRHNY60S22Z336P', 'PRES456', '1964-08-14', 'Port Said');


-- Inserimento di Team
INSERT INTO team (id, name, foundation_year, address, president_id) VALUES
(1, 'Italia', 1898, 'Via Allegri 14, Roma', 1),
(2, 'Egitto', 1921, 'Cairo International Stadium, Cairo', 2);

-- Inserimento di Player (Italia)
INSERT INTO player (id, first_name, last_name, date_of_birth, place_of_birth, role, start_date_of_registration, end_date_of_registration, team_id) VALUES
(1, 'Gianluigi', 'Donnarumma', '1999-02-25', 'Castellammare di Stabia', 'Portiere', '2016-09-01', NULL, 1),
(2, 'Francesco', 'Acerbi', '1988-02-10', 'Vizzolo Predabissi', 'Difensore', '2018-11-20', NULL, 1),
(3, 'Leonardo', 'Bonucci', '1987-05-01', 'Viterbo', 'Difensore', '2010-03-03', NULL, 1),
(4, 'Alessandro', 'Bastoni', '1999-04-13', 'Casalmaggiore', 'Difensore', '2020-11-11', NULL, 1),
(5, 'Giovanni', 'Di Lorenzo', '1993-08-04', 'Castelnuovo di Garfagnana', 'Difensore', '2019-10-15', NULL, 1),
(6, 'Marco', 'Verratti', '1992-11-05', 'Pescara', 'Centrocampista', '2012-08-15', NULL, 1),
(7, 'Nicolò', 'Barella', '1997-02-07', 'Cagliari', 'Centrocampista', '2018-10-10', NULL, 1),
(8, 'Jorginho', 'Frello', '1991-12-20', 'Imbituba', 'Centrocampista', '2016-03-24', NULL, 1),
(9, 'Federico', 'Chiesa', '1997-10-25', 'Genova', 'Attaccante', '2018-03-23', NULL, 1),
(10, 'Ciro', 'Immobile', '1990-02-20', 'Torre Annunziata', 'Attaccante', '2014-03-05', NULL, 1),
(11, 'Lorenzo', 'Insigne', '1991-06-04', 'Napoli', 'Attaccante', '2012-09-11', NULL, 1);

-- Inserimento di Player (Egitto)
INSERT INTO player (id, first_name, last_name, date_of_birth, place_of_birth, role, start_date_of_registration, end_date_of_registration, team_id) VALUES
(12, 'Mohamed', 'El Shenawy', '1988-12-18', 'El-Hamoul', 'Portiere', '2018-03-23', NULL, 2),
(13, 'Ahmed', 'Hegazi', '1991-01-25', 'Ismailia', 'Difensore', '2011-09-03', NULL, 2),
(14, 'Mahmoud', 'Alaa', '1991-01-01', 'Cairo', 'Difensore', '2018-09-07', NULL, 2),
(15, 'Omar', 'Gaber', '1992-01-30', 'Cairo', 'Difensore', '2011-03-26', NULL, 2),
(16, 'Mohamed', 'Elneny', '1992-07-11', 'El-Mahalla El-Kubra', 'Centrocampista', '2011-09-03', NULL, 2),
(17, 'Tarek', 'Hamed', '1988-10-24', 'Cairo', 'Centrocampista', '2013-11-15', NULL, 2),
(18, 'Mahmoud', 'Trezeguet', '1994-10-01', 'Kafr el-Sheikh', 'Centrocampista', '2014-09-01', NULL, 2),
(19, 'Amr', 'El Solia', '1989-04-02', 'Mansoura', 'Centrocampista', '2018-09-08', NULL, 2),
(20, 'Mohamed', 'Salah', '1992-06-15', 'Nagrig', 'Attaccante', '2011-09-03', NULL, 2),
(21, 'Mostafa', 'Mohamed', '1997-11-28', 'Giza', 'Attaccante', '2020-10-14', NULL, 2),
(22, 'Ahmed', 'Fattouh', '1998-03-22', 'Cairo', 'Difensore', '2019-03-26', NULL, 2);
