package org.example.dao;

import org.example.dbhelper.ConnectionFactory;
import org.example.mappers.MovieMapper;
import org.example.mappers.RowMapper;
import org.example.models.Movie;

import java.sql.*;
import java.util.List;
import java.util.Optional;

/**
 * JDBC implementation of {@link MovieDao}.
 * <p>
 * Provides CRUD operations and search queries for {@link Movie} entities
 * using plain JDBC and SQL queries.
 *
 * <p>
 * This class uses:
 * <ul>
 *     <li>{@link ConnectionFactory} to obtain database connections</li>
 *     <li>{@link RowMapper} ({@link MovieMapper}) to map {@link ResultSet} rows to {@link Movie} objects</li>
 * </ul>
 *
 */
public class JDBCMovieDao implements MovieDao {

    /**
     * List of columns used in SELECT queries to avoid repetition.
     */
    private static final String SELECT_COLUMNS = "id, title, year, country, rating";

    /**
     * Mapper responsible for converting {@link ResultSet} into {@link Movie} objects.
     */
    private final RowMapper<Movie> rowMapper = new MovieMapper();

    /**
     * Retrieves all movies from the database.
     *
     * @return list of all movies
     * @throws RuntimeException if a database access error occurs
     */
    @Override
    public List<Movie> findAll() {
        final String sql = "SELECT " + SELECT_COLUMNS + " FROM movies";
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            ResultSet resultSet = preparedStatement.executeQuery();
            return rowMapper.mapList(resultSet);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Retrieves a movie by its unique identifier.
     *
     * @param id movie identifier
     * @return optional containing movie if found, otherwise empty
     * @throws RuntimeException if a database access error occurs
     */
    @Override
    public Optional<Movie> findById(long id) {
        final String sql = "SELECT " + SELECT_COLUMNS + " FROM movies WHERE id = ?";
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            Movie movie = null;
            if (resultSet.next()) {
                movie = rowMapper.map(resultSet);

            }
            return Optional.ofNullable(movie);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Finds movies by title using case-insensitive partial matching.
     *
     * @param title movie title or part of it
     * @return list of matching movies
     * @throws RuntimeException if a database access error occurs
     */
    @Override
    public List<Movie> findByTitle(String title) {
        final String sql = "SELECT " + SELECT_COLUMNS + " FROM movies WHERE title ILIKE ?";
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, "%" + title + "%");
            ResultSet resultSet = preparedStatement.executeQuery();

            return rowMapper.mapList(resultSet);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Finds movies by release year.
     * <p>
     * Uses SQL {@code EXTRACT(YEAR FROM ...)} to compare only the year part
     * of the date column.
     *
     *
     * @param year release year
     * @return list of movies released in the specified year
     * @throws RuntimeException if a database access error occurs
     */
    @Override
    public List<Movie> findByYear(int year) {
        final String sql = "SELECT " + SELECT_COLUMNS + " FROM movies m WHERE extract(year FROM m.year) = ?";
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, year);
            ResultSet resultSet = preparedStatement.executeQuery();

            return rowMapper.mapList(resultSet);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Saves a new movie into the database.
     *
     * @param movie movie to persist
     * @return true if exactly one row was inserted
     * @throws RuntimeException if a database access error occurs
     */
    @Override
    public boolean save(Movie movie) {
        final String sql = "INSERT INTO movies(title, year, country, rating) VALUES (?, ?, ?, ?)";
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, movie.getTitle());
            preparedStatement.setDate(2, Date.valueOf(movie.getYear()));
            preparedStatement.setString(3, movie.getCountry());
            preparedStatement.setDouble(4, movie.getRating());

            return preparedStatement.executeUpdate() == 1;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Updates an existing movie in the database.
     *
     * @param movie movie with updated data
     * @return true if exactly one row was updated
     * @throws RuntimeException if a database access error occurs
     */
    @Override
    public boolean update(Movie movie) {
        final String sql = "UPDATE movies SET title=?, year=?, country=?, rating=? WHERE id=?";
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, movie.getTitle());
            preparedStatement.setDate(2, Date.valueOf(movie.getYear()));
            preparedStatement.setString(3, movie.getCountry());
            preparedStatement.setDouble(4, movie.getRating());
            preparedStatement.setLong(5, movie.getId());

            return preparedStatement.executeUpdate() == 1;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Deletes a movie by its identifier.
     *
     * @param id movie identifier
     * @return true if exactly one row was deleted
     * @throws RuntimeException if a database access error occurs
     */
    @Override
    public boolean delete(long id) {
        final String sql = "DELETE FROM movies WHERE id=?";
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setLong(1, id);
            return preparedStatement.executeUpdate() == 1;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}