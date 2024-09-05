-- Inserimento di Player
INSERT INTO player (id, first_name, last_name, date_of_birth, place_of_birth, role, start_date_of_registration, end_date_of_registration, team_id) VALUES
(1, 'Michael', 'Jordan', '1980-02-17', 'Chicago', 'Forward', '2000-05-01', '2005-06-30', 1),
(2, 'LeBron', 'James', '1984-12-30', 'Akron', 'Guard', '2003-07-01', NULL, 1),
(3, 'Kobe', 'Bryant', '1978-08-23', 'Philadelphia', 'Shooting Guard', '1996-11-03', '2016-04-13', 2);

-- Inserimento di President
INSERT INTO president (id, first_name, last_name, fiscal_code, date_of_birth, place_of_birth) VALUES
(1, 'John', 'Doe', 'JD1234567', '1975-04-15', 'New York'),
(2, 'Jane', 'Smith', 'JS7654321', '1980-11-23', 'Los Angeles');

-- Inserimento di Team
INSERT INTO team (id, name, foundation_year, address, president_id) VALUES
(1, 'Red Dragons', 1995, '123 Elm Street', 1),
(2, 'Blue Eagles', 2000, '456 Oak Avenue', 2);