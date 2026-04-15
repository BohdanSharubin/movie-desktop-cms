CREATE TABLE IF NOT EXISTS movies (
                                      id SERIAL PRIMARY KEY,
                                      title TEXT NOT NULL,
                                      year DATE NOT NULL,
                                      country TEXT NOT NULL,
                                      rating DECIMAL NOT NULL
);

CREATE TABLE IF NOT EXISTS actors (
                                      id SERIAL PRIMARY KEY,
                                      fullname TEXT NOT NULL,
                                      birthdate DATE
);

CREATE TABLE IF NOT EXISTS roles (
                                     name TEXT NOT NULL,
                                     movie_id INT NOT NULL,
                                     actor_id INT NOT NULL,
                                     PRIMARY KEY (movie_id, actor_id),
                                     FOREIGN KEY (movie_id) REFERENCES movies(id),
                                     FOREIGN KEY (actor_id) REFERENCES actors(id)
);