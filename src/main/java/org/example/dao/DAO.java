package org.example.dao;

import java.util.List;
import java.util.Optional;

public interface DAO<T> {
    List<T> findAll();
    Optional<T> findById(int id);
    boolean save(T movie);
    boolean update(T movie);
    boolean delete(int id);
}
