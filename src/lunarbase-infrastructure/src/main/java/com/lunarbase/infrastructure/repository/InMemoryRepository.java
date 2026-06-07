package com.lunarbase.infrastructure.repository;

import com.lunarbase.domain.entities.Entity;
import java.util.*;

public abstract class InMemoryRepository<T extends Entity> implements com.lunarbase.domain.interfaces.Repository<T> {
    protected final Map<Integer, T> database = new HashMap<>();
    protected int nextId = 1;

    @Override
    public Optional<T> findById(int id) {
        return Optional.ofNullable(database.get(id));
    }

    @Override
    public List<T> findAll() {
        return new ArrayList<>(database.values());
    }

    @Override
    public T save(T entity) {
        if (entity.getId() == null) {
            entity.setId(nextId++);
        }
        database.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public void delete(int id) {
        database.remove(id);
    }

    protected int getNextId() {
        return nextId++;
    }
}