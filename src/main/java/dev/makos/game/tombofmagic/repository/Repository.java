package dev.makos.game.tombofmagic.repository;

import java.util.stream.Stream;

public interface Repository<T> {

    T getById(long id);

    void create(T entity);

    void update(T entity);

    void deleteById(long id);

    Stream<T> getAll();

    Stream<T> find(T entity);
}
