package dds.grupo4.tpimpacto.services;

import dds.grupo4.tpimpacto.entities.BaseEntity;

import java.util.List;

public interface BaseService<T extends BaseEntity> {
    T save(T obj);

    List<T> getAll();

    T getById(int id);
}
