package dev.makos.quest.mapper;

public interface Mapper<T,V> {

    T toDto(V entity);
}
