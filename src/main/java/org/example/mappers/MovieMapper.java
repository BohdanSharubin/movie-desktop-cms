package org.example.mappers;

import org.example.models.Movie;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MovieMapper implements RowMapper<Movie> {
    @Override
    public List<Movie> mapList(ResultSet resultSet) throws SQLException {
        List<Movie> movies = new ArrayList<>();
        while(resultSet.next()) {
            Movie movie = map(resultSet);
            movies.add(movie);
        }
        return movies;
    }

    @Override
    public Movie map(ResultSet resultSet) throws SQLException {
        Movie movie = new Movie();
        movie.setId(resultSet.getInt("id"));
        movie.setTitle(resultSet.getString("title"));
        movie.setCountry(resultSet.getString("country"));
        movie.setYear(resultSet.getDate("year").toLocalDate());
        movie.setRating(resultSet.getInt("rating"));
        return movie;
    }
}
