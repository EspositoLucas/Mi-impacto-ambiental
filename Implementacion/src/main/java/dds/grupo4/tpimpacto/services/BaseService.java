package dds.grupo4.tpimpacto.services;

import java.util.List;

public interface BaseService<T> {
    void save(T obj);

    List<T> getAll();
}
