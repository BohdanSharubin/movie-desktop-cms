-- Movies
INSERT INTO movies (title, year, country, rating) VALUES
                                                          ( 'Inception', '2010-01-01', 'USA', 8.8),
                                                          ( 'Parasite', '2019-01-01', 'South Korea', 8.6),
                                                          ( 'Interstellar', '2014-01-01', 'USA', 8.7),
                                                          ( 'The Godfather', '1972-01-01', 'USA', 9.2),
                                                          ( 'Amélie', '2001-01-01', 'France', 8.3);

-- Actors
INSERT INTO actors ( fullname, birthdate) VALUES
                                                 ( 'Leonardo DiCaprio', '1974-11-11'),
                                                 ( 'Song Kang-ho', '1967-01-17'),
                                                 ( 'Matthew McConaughey', '1969-11-04'),
                                                 ( 'Marlon Brando', '1924-04-03'),
                                                 ( 'Audrey Tautou', '1976-08-09');

-- Roles
INSERT INTO roles (name, movie_id, actor_id) VALUES
                                                 ('Dom Cobb', 1, 1),
                                                 ('Kim Ki-taek', 2, 2),
                                                 ('Cooper', 3, 3),
                                                 ('Vito Corleone', 4, 4),
                                                 ('Amélie Poulain', 5, 5);