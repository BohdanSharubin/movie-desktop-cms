package org.example.models;

import java.util.Objects;

/**
 * Represents a role entity that links an actor to a movie.
 * <p>
 * This class models a many-to-many relationship between {@link Movie} and {@link Actor}
 * with an additional attribute {@code name}, which represents the character played
 * by the actor in the movie.
 * </p>
 *
 * <p>
 * In database terms, this corresponds to the {@code roles} table,
 * acting as a junction table between movies and actors.
 * </p>
 */
public class Role {

    /**
     * Name of the character played by the actor in the movie.
     */
    private String name;

    /**
     * The movie in which the role is performed.
     */
    private Movie movie;

    /**
     * The actor who performs the role.
     */
    private Actor actor;

    /**
     * Default constructor.
     */
    public Role() {
    }

    /**
     * Constructs a role with all fields.
     *
     * @param name  character name
     * @param movie associated movie
     * @param actor associated actor
     */
    public Role(String name, Movie movie, Actor actor) {
        this.name = name;
        this.movie = movie;
        this.actor = actor;
    }

    /**
     * Returns the role (character) name.
     *
     * @return role name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the role (character) name.
     *
     * @param name role name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the associated movie.
     *
     * @return movie
     */
    public Movie getMovie() {
        return movie;
    }

    /**
     * Sets the associated movie.
     *
     * @param movie movie entity
     */
    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    /**
     * Returns the associated actor.
     *
     * @return actor
     */
    public Actor getActor() {
        return actor;
    }

    /**
     * Sets the associated actor.
     *
     * @param actor actor entity
     */
    public void setActor(Actor actor) {
        this.actor = actor;
    }

    /**
     * Compares this role with another object for equality.
     * Roles are considered equal if name, movie, and actor match.
     *
     * @param o object to compare
     * @return true if equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Role role)) return false;
        return Objects.equals(name, role.name) &&
                Objects.equals(movie, role.movie) &&
                Objects.equals(actor, role.actor);
    }

    /**
     * Returns hash code based on role fields.
     *
     * @return hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, movie, actor);
    }
}