package org.example.dao;

import org.example.models.Movie;

import java.util.List;

/**
 * Data Access Object (DAO) interface for {@link Movie} entity.
 * <p>
 * Extends the generic {@link DAO} interface and provides additional
 * query methods specific to movie search operations.
 * </p>
 */
public interface MovieDao extends DAO<Movie> {

    /**
     * Finds movies by title.
     * <p>
     * The search behavior (exact match or partial match using LIKE)
     * depends on the implementation.
     * </p>
     *
     * @param title movie title or part of it
     * @return list of movies matching the given title
     */
    List<Movie> findByTitle(String title);

    /**
     * Finds movies by release year.
     * <p>
     * Note: since the movie entity stores the year as {@code LocalDate},
     * the implementation may extract the year part from the date.
     * </p>
     *
     * @param year release year (e.g. 2020)
     * @return list of movies released in the specified year
     */
    List<Movie> findByYear(int year);
}