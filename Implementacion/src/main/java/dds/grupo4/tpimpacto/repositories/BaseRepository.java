package dds.grupo4.tpimpacto.repositories;

import dds.grupo4.tpimpacto.entities.BaseEntity;

import java.util.List;

public interface BaseRepository<T extends BaseEntity> {
    void save(T obj);

    T merge(T obj);

    List<T> getAll();
}
