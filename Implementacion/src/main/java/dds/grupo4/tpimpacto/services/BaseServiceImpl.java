package dds.grupo4.tpimpacto.services;

import dds.grupo4.tpimpacto.entities.BaseEntity;
import dds.grupo4.tpimpacto.repositories.BaseRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
public class BaseServiceImpl<TEntity extends BaseEntity, TRepo extends BaseRepository<TEntity>> implements BaseService<TEntity> {

    protected final TRepo repository;

    public BaseServiceImpl(TRepo repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public TEntity save(TEntity obj) {
        try {
            repository.save(obj);
        } catch (Exception e) {
            log.error("Error al llamar a save(), asi que se llama a merge()\nEl error es: " + e.getMessage());
            obj = repository.merge(obj);
        }
        return obj;
    }

    @Override
    @Transactional
    public List<TEntity> getAll() {
        return repository.getAll();
    }

    @Override
    @Transactional
    public TEntity getById(int id) {
        return repository.getById(id);
    }
}
