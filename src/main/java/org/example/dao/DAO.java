package org.example.dao;

import java.util.List;
import java.util.Optional;

/**
 * Generic Data Access Object (DAO) interface.
 * <p>
 * Defines common CRUD (Create, Read, Update, Delete) operations
 * for working with persistent entities.
 * </p>
 *
 * @param <T> the type of entity managed by this DAO
 */
public interface DAO<T> {

    /**
     * Retrieves all entities from the data source.
     *
     * @return list of all entities
     */
    List<T> findAll();

    /**
     * Retrieves an entity by its identifier.
     *
     * @param id unique identifier of the entity
     * @return optional containing the entity if found, or empty if not found
     */
    Optional<T> findById(int id);

    /**
     * Persists a new entity in the data source.
     *
     * @param entity entity to be saved
     * @return true if the operation was successful, false otherwise
     */
    boolean save(T entity);

    /**
     * Updates an existing entity in the data source.
     *
     * @param entity entity with updated data
     * @return true if the update was successful, false otherwise
     */
    boolean update(T entity);

    /**
     * Deletes an entity by its identifier.
     *
     * @param id unique identifier of the entity to delete
     * @return true if the deletion was successful, false otherwise
     */
    boolean delete(int id);
}