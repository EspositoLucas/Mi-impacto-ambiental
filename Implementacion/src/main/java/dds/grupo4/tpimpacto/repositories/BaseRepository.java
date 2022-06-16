package dds.grupo4.tpimpacto.repositories;

import java.util.List;

public interface BaseRepository<T> {
    void save(T obj);

    void update(T obj);

    List<T> getAll();
}
