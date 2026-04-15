package org.example.dao;

import org.example.models.Movie;

import java.util.List;
import java.util.Optional;

public interface MovieDao extends DAO<Movie> {
    List<Movie> findAll();
    Optional<Movie> findById(int id);
    boolean save(Movie movie);
    boolean update(Movie movie);
    boolean delete(int id);
}
