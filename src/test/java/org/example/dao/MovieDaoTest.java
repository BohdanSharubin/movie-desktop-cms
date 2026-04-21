package org.example.dao;

import org.example.dbhelper.ConnectionFactory;
import org.example.models.Movie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class MovieDaoTest extends AbstractDaoTest {
    MovieDao movieDao = new JDBCMovieDao();

    private List<Movie> createMovies() {
        return Arrays.asList(
                new Movie(1, "Inception", LocalDate.of(2010, 1, 1), "USA", 8.8f),
                new Movie(2, "Parasite", LocalDate.of(2019, 1, 1), "South Korea", 8.6f),
                new Movie(3, "Interstellar", LocalDate.of(2014, 1, 1), "USA", 8.7f),
                new Movie(4, "The Godfather", LocalDate.of(1972, 1, 1), "USA", 9.2f),
                new Movie(5, "Amélie", LocalDate.of(2001, 1, 1), "France", 8.3f)
        );
    }

    @BeforeEach
    void setupData() throws Exception {
        try (Connection conn = ConnectionFactory.getConnection()) {
            executeSqlFromFile(conn, "clean_tables.sql");
            executeSqlFromFile(conn, "test_data.sql");
        }
    }

    @DisplayName("Given movies exist when findAll is called then return full movies list")
    @Test
    void givenMoviesExists_whenFindAll_returnMoviesList() {
        List<Movie> expectedMovies = createMovies();

        List<Movie> actualMovies = movieDao.findAll();

        assertEquals(expectedMovies.size(), actualMovies.size());
        assertEquals(expectedMovies, actualMovies);
    }

    @DisplayName("Given existing movie id when findById is called then return Optional with movie")
    @ParameterizedTest
    @ValueSource(ints = {1, 3, 4})
    void givenIdExists_whenFindById_returnOptionalMovieWithGivenId(int id) {
        int correctedId = id - 1;

        List<Movie> movieList = createMovies();
        Movie expectedMovie = movieList.get(correctedId);

        Optional<Movie> actualMovie = movieDao.findById(id);

        assertTrue(actualMovie.isPresent());
        assertEquals(expectedMovie, actualMovie.get());
    }

    @DisplayName("Given non-existing movie id when findById is called then return empty Optional")
    @Test
    void givenIdNotExist_whenFindById_returnEmptyOptionalMovie() {
        List<Movie> movieList = createMovies();

        Optional<Movie> actualMovie = movieDao.findById(movieList.size() + 10);

        assertTrue(actualMovie.isEmpty());
    }

    @DisplayName("Given exact title match when findByTitle is called then return matching movie list")
    @Test
    void givenTitleFullMatched_whenFindByTitle_returnListWithMovieWithGivenTitle() {
        List<Movie> movieList = createMovies();
        Movie randomMovie = movieList.get(0);
        List<Movie> expectedMovies = Collections.singletonList(randomMovie);

        List<Movie> actualMovies = movieDao.findByTitle(randomMovie.getTitle());

        assertFalse(actualMovies.isEmpty());
        assertEquals(expectedMovies.size(), actualMovies.size());
        assertEquals(expectedMovies, actualMovies);
    }

    @DisplayName("Given non-matching title when findByTitle is called then return empty list")
    @Test
    void givenTitleNotMatch_whenFindByTitle_returnEmptyList() {
        String randomTitle = "Empty title";
        List<Movie> expectedMovies = new ArrayList<>();

        List<Movie> actualMovies = movieDao.findByTitle(randomTitle);

        assertTrue(actualMovies.isEmpty());
        assertEquals(expectedMovies, actualMovies);
    }

    @DisplayName("Given null title when findByTitle is called then return empty list")
    @ParameterizedTest
    @NullSource
    void givenTitleIsNull_whenFindByTitle_returnEmptyList(String title) {
        List<Movie> expectedMovies = new ArrayList<>();
        List<Movie> actualMovies = movieDao.findByTitle(title);

        assertTrue(actualMovies.isEmpty());
        assertEquals(expectedMovies, actualMovies);
    }

    @DisplayName("Given matching year when findByYear is called then return movies released that year")
    @ParameterizedTest
    @ValueSource(ints = {2010, 2014})
    void givenYearMatched_whenFindByYear_returnMovieListByYear(int year) {
        List<Movie> expectedMovies = createMovies().stream()
                .filter(movie -> movie.getYear().getYear() == year)
                .toList();

        List<Movie> actualMovies = movieDao.findByYear(year);

        assertFalse(actualMovies.isEmpty());
        assertEquals(expectedMovies, actualMovies);
    }

    @DisplayName("Given non-matching year when findByYear is called then return empty list")
    @ParameterizedTest
    @ValueSource(ints = {3010, 1014})
    void givenYearNotMatched_whenFindByYear_returnEmptyList(int year) {
        List<Movie> actualMovies = movieDao.findByYear(year);

        assertTrue(actualMovies.isEmpty());
    }

    @DisplayName("Given valid movie when save is called then return true and persist movie")
    @Test
    void givenMovie_whenSave_returnTrue() throws Exception {
        Movie expectedMovie = new Movie("John Wick",
                LocalDate.of(2014, 1, 1),
                "USA",
                9.9f);

        boolean isSaved = movieDao.save(expectedMovie);
        assertTrue(isSaved);

        try(Connection conn = ConnectionFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT id, title, year, country, rating FROM movies WHERE title = ?")) {
            ps.setString(1, expectedMovie.getTitle());

            try (ResultSet rs = ps.executeQuery()) {
                assertTrue(rs.next());

                assertEquals(expectedMovie.getTitle(), rs.getString("title"));
                assertEquals(expectedMovie.getYear(), rs.getDate("year").toLocalDate());
                assertEquals(expectedMovie.getCountry(), rs.getString("country"));
                assertEquals(expectedMovie.getRating(), rs.getFloat("rating"));
            }
        }
    }

    @DisplayName("Given existing movie when update is called then return true and update movie data")
    @Test
    void givenMovie_whenUpdate_returnTrue() throws Exception {
        Movie expectedMovie = createMovies().get(0);
        expectedMovie.setTitle("John Wick");
        expectedMovie.setCountry("Germany");
        expectedMovie.setRating(2.2f);
        expectedMovie.setYear(LocalDate.of(2001, 1, 1));

        boolean isUpdated = movieDao.update(expectedMovie);
        assertTrue(isUpdated);

        try(Connection conn = ConnectionFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT id, title, year, country, rating FROM movies WHERE id = ?")) {
            ps.setLong(1, expectedMovie.getId());

            try (ResultSet rs = ps.executeQuery()) {
                assertTrue(rs.next());

                assertEquals(expectedMovie.getTitle(), rs.getString("title"));
                assertEquals(expectedMovie.getYear(), rs.getDate("year").toLocalDate());
                assertEquals(expectedMovie.getCountry(), rs.getString("country"));
                assertEquals(expectedMovie.getRating(), rs.getFloat("rating"));
            }
        }
    }

    @DisplayName("Given existing movie id when delete is called then return true and remove movie")
    @Test
    void givenIdExist_whenDelete_returnTrue() throws Exception {
        Movie expectedMovie = createMovies().get(0);

        boolean isDeleted = movieDao.delete(expectedMovie.getId());
        assertTrue(isDeleted);

        try(Connection conn = ConnectionFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT id, title, year, country, rating FROM movies WHERE id = ?")) {
            ps.setLong(1, expectedMovie.getId());

            try (ResultSet rs = ps.executeQuery()) {
                assertFalse(rs.next());
            }
        }
    }

    @DisplayName("Given non-existing movie id when delete is called then return false")
    @Test
    void givenIdNotExist_whenDelete_returnFalse() {
        long randomId = 6666;

        boolean isDeleted = movieDao.delete(randomId);
        assertFalse(isDeleted);
    }

}
