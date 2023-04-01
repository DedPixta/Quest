package dev.makos.game.tombofmagic.mapper;

public interface Mapper<T,V> {

    T toDto(V entity);
}
