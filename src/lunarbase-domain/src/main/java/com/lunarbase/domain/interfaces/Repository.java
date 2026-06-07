package com.lunarbase.domain.interfaces;

import com.lunarbase.domain.entities.Entity;
import java.util.List;
import java.util.Optional;

public interface Repository<T extends Entity> {
    Optional<T> findById(int id);
    List<T> findAll();
    T save(T entity);
    void delete(int id);
}