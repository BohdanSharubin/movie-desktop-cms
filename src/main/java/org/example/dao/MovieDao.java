package org.example.dao;

import org.example.models.Movie;

import java.util.List;

public interface MovieDao extends DAO<Movie> {
    List<Movie> findByTitle(String title);
    List<Movie> findByYear(int year);
}
