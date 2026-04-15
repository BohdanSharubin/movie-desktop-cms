package org.example.dao;

import org.example.dbhelper.DatabaseConnection;
import org.example.mappers.MovieMapper;
import org.example.mappers.RowMapper;
import org.example.models.Movie;

import java.sql.*;
import java.util.List;
import java.util.Optional;

public class JDBCMovieDao implements MovieDao {
    private final RowMapper<Movie> rowMapper = new MovieMapper();

    @Override
    public List<Movie> findAll() {
        final String sql = "SELECT * FROM movies";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            return rowMapper.mapList(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Movie> findById(int id) {
        final String sql = "SELECT * FROM movies WHERE id = ?";
        try(Connection connection = DatabaseConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            Movie movie = rowMapper.map(resultSet);
            return Optional.of(movie);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean save(Movie movie) {
        final String sql = "INSERT INTO movies(title, year, country, rating) VALUES (?, ?, ?, ?)";
        try(Connection connection = DatabaseConnection.getConnection();
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

    @Override
    public boolean update(Movie movie) {
        final String sql = "UPDATE movies SET title=?, year=?, country=?, rating=? WHERE id=?";
        try(Connection connection = DatabaseConnection.getConnection();
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

    @Override
    public boolean delete(int id) {
        final String sql = "DELETE FROM movies WHERE id=?";
        try(Connection connection = DatabaseConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
