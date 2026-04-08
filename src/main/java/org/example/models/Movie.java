package org.example.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Represents a movie entity in the system.
 * <p>
 * Contains basic information about a movie such as title, release year,
 * country of origin, and rating. Also maintains a list of roles that
 * associate actors with this movie.
 * </p>
 */
public class Movie {

    /**
     * Unique identifier of the movie.
     */
    private long id;

    /**
     * Title of the movie.
     */
    private String title;

    /**
     * Release year of the movie.
     */
    private LocalDate year;

    /**
     * Country where the movie was produced.
     */
    private String country;

    /**
     * Rating of the movie (e.g. from 0.0 to 10.0).
     */
    private float rating;

    /**
     * List of roles associated with this movie.
     * Each role links an actor to the movie with a specific character name.
     */
    private List<Role> roles = new ArrayList<>();

    /**
     * Default constructor.
     */
    public Movie() {
    }

    /**
     * Constructs a movie with all main fields.
     *
     * @param id      unique identifier
     * @param title   movie title
     * @param year    release year
     * @param country country of origin
     * @param rating  movie rating
     */
    public Movie(long id, String title, LocalDate year, String country, float rating) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.country = country;
        this.rating = rating;
    }

    /**
     * Returns the movie ID.
     *
     * @return movie ID
     */
    public long getId() {
        return id;
    }

    /**
     * Sets the movie ID.
     *
     * @param id movie ID
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Returns the movie title.
     *
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the movie title.
     *
     * @param title movie title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Returns the release year.
     *
     * @return release year
     */
    public LocalDate getYear() {
        return year;
    }

    /**
     * Sets the release year.
     *
     * @param year release year
     */
    public void setYear(LocalDate year) {
        this.year = year;
    }

    /**
     * Returns the country of origin.
     *
     * @return country
     */
    public String getCountry() {
        return country;
    }

    /**
     * Sets the country of origin.
     *
     * @param country country
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Returns the movie rating.
     *
     * @return rating value
     */
    public float getRating() {
        return rating;
    }

    /**
     * Sets the movie rating.
     *
     * @param rating rating value
     */
    public void setRating(float rating) {
        this.rating = rating;
    }

    /**
     * Returns an unmodifiable copy of roles associated with the movie.
     *
     * @return list of roles
     */
    public List<Role> getRoles() {
        return List.copyOf(roles);
    }

    /**
     * Sets the roles associated with the movie.
     *
     * @param roles list of roles
     */
    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    /**
     * Compares this movie with another object for equality.
     * Movies are considered equal if all main fields match.
     *
     * @param o object to compare
     * @return true if equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Movie movie)) return false;
        return id == movie.id &&
                Float.compare(rating, movie.rating) == 0 &&
                Objects.equals(title, movie.title) &&
                Objects.equals(year, movie.year) &&
                Objects.equals(country, movie.country);
    }

    /**
     * Returns hash code based on movie fields.
     *
     * @return hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, title, year, country, rating);
    }
}