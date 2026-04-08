package org.example.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Represents an actor entity in the system.
 * <p>
 * Stores personal information about an actor such as full name and birth date.
 * Also maintains a list of roles that link this actor to movies.
 * </p>
 */
public class Actor {

    /**
     * Unique identifier of the actor.
     */
    private long id;

    /**
     * Full name of the actor.
     */
    private String fullName;

    /**
     * Birth date of the actor.
     */
    private LocalDate birthdate;

    /**
     * List of roles associated with this actor.
     * Each role connects the actor to a movie with a specific character name.
     */
    private List<Role> roles = new ArrayList<>();

    /**
     * Default constructor.
     */
    public Actor() {
    }

    /**
     * Constructs an actor with all main fields.
     *
     * @param id         unique identifier
     * @param fullName   actor's full name
     * @param birthdate  actor's birth date
     */
    public Actor(long id, String fullName, LocalDate birthdate) {
        this.id = id;
        this.fullName = fullName;
        this.birthdate = birthdate;
    }

    /**
     * Returns the actor ID.
     *
     * @return actor ID
     */
    public long getId() {
        return id;
    }

    /**
     * Sets the actor ID.
     *
     * @param id actor ID
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Returns the full name of the actor.
     *
     * @return full name
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * Sets the full name of the actor.
     *
     * @param fullName actor's full name
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * Returns the birth date of the actor.
     *
     * @return birth date
     */
    public LocalDate getBirthdate() {
        return birthdate;
    }

    /**
     * Sets the birth date of the actor.
     *
     * @param birthdate actor's birth date
     */
    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    /**
     * Returns an unmodifiable copy of roles associated with the actor.
     *
     * @return list of roles
     */
    public List<Role> getRoles() {
        return List.copyOf(roles);
    }

    /**
     * Sets the roles associated with the actor.
     *
     * @param roles list of roles
     */
    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    /**
     * Compares this actor with another object for equality.
     * Actors are considered equal if all main fields match.
     *
     * @param o object to compare
     * @return true if equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Actor actor)) return false;
        return id == actor.id &&
                Objects.equals(fullName, actor.fullName) &&
                Objects.equals(birthdate, actor.birthdate);
    }

    /**
     * Returns hash code based on actor fields.
     *
     * @return hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, fullName, birthdate);
    }
}